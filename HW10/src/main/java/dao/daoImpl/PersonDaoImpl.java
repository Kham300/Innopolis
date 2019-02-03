package main.java.dao.daoImpl;

import main.java.Person;
import main.java.Subject;
import main.java.dao.PersonDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    PreparedStatement preparedStatement = null;
    private Connection connection;

    public PersonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Person> getAllPersonsBySubject(Subject subject) throws SQLException {
        List<Person> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select b.person_id, b.name, birth_date from postgres.testschema.course a, postgres.testschema.person b where subject_id = ? and a.person_id = b.person_id");
            preparedStatement.setInt(1, subject.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Person(rs.getInt(1), rs.getString(2), rs.getTimestamp(3)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
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
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void updatePerson(Person person) throws SQLException {
        String sql = "UPDATE postgres.testschema.person SET name=? WHERE person_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }

    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        String sql = "DELETE FROM postgres.testschema.person WHERE person_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }

    @Override
    public void createPerson(Person person) throws SQLException {
        String sql = "INSERT INTO postgres.testschema.person (name) VALUES (?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }

    }

    @Override
    public Person getPerson(int id) throws SQLException {
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
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return person;
    }
}
