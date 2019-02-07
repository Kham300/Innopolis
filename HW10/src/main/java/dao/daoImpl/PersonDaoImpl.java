package dao.daoImpl;

import dao.PersonDao;
import model.Person;
import model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type model.Person dao.
 */
public class PersonDaoImpl implements PersonDao {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(PersonDaoImpl.class);

    /**
     * The Prepared statement.
     */
    PreparedStatement preparedStatement = null;
    private Connection connection;

    /**
     * Instantiates a new model.Person dao.
     *
     * @param connection the connection
     */
    public PersonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Person> getAllPersonsBySubject(Subject subject) {
        List<Person> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select b.person_id, b.name, birth_date from postgres.testschema.course a, postgres.testschema.person b where subject_id = ? and a.person_id = b.person_id");
            preparedStatement.setInt(1, subject.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Person(rs.getInt(1), rs.getString(2), rs.getTimestamp(3)));
            }

        } catch (SQLException e) {
            LOGGER.error("Ошибка при при получении всех пользователей по subject", e.getMessage());
        } finally {
            if (preparedStatement != null) {
                closeStatement();
            }
            if (connection != null) {
                closeConnection();
            }
        }
        return null;
    }

    @Override
    public Collection<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from postgres.testschema.person");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Person(rs.getInt(1), rs.getString(2), rs.getTimestamp(3)));
            }

        } catch (SQLException e) {
            LOGGER.error("Ошибка при при получении всех пользователей", e.getMessage());

        } finally {
            if (preparedStatement != null) {
                closeStatement();
            }
            if (connection != null) {
                closeConnection();
            }
        }
        return null;
    }

    @Override
    public void updatePerson(Person person) {
        String sql = "UPDATE postgres.testschema.person SET name=? WHERE person_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                LOGGER.info("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка при при обновлении пользователя по id - {}", person.getId() ,e.getMessage());
        } finally {
            if (preparedStatement != null) {
                closeStatement();
            }
            if (connection != null) {
                closeConnection();
            }
        }

    }

    @Override
    public void deletePerson(Person person) {
        String sql = "DELETE FROM postgres.testschema.person WHERE person_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {

                LOGGER.info("A user {} was deleted successfully!", person.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                closeStatement();
            }
            if (connection != null) {
                closeConnection();
            }

        }
    }

    @Override
    public void createPerson(Person person) {
        String sql = "INSERT INTO postgres.testschema.person (name) VALUES (?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {

                LOGGER.info("A new user was inserted successfully!\n {}", person.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                closeStatement();
            }
            if (connection != null) {
                closeConnection();
            }

        }

    }

    @Override
    public Person getPerson(int id) {
        Person person = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM postgres.testschema.person WHERE person_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                person = new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                closeStatement();
            }
            if (connection != null) {
                closeConnection();
            }
        }
        return person;
    }


    private void closeStatement() {
        try {
            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            LOGGER.error("Ошибка при закрытии коннекта: ", e.getMessage());
        }
    }
}
