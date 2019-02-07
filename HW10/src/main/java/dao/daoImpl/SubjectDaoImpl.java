package dao.daoImpl;

import dao.SubjectDao;
import model.Person;
import main.java.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(SubjectDaoImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement = null;

    public SubjectDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private void closeStatement() {
        try {
            this.preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Ошибка при закрытии preparedStatement: ", e.getMessage());

        }
    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            LOGGER.error("Ошибка при закрытии коннекта: ", e.getMessage());
        }
    }


    @Override
    public Collection<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from postgres.testschema.subject");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException e) {

            LOGGER.error("Ошибка при получении всех subject: \n", e.getMessage());

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
    public Collection<Subject> getSubjectsByPerson(Person person) {
        List<Subject> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from postgres.testschema.course a, postgres.testschema.subject b where person_id = ? and a.subject_id = b.subject_id");
            preparedStatement.setInt(1, person.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException e) {
            LOGGER.error("Ошибка при получении subject: \n", e.getMessage());

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

}
