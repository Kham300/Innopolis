package main.java.dao.daoImpl;

import main.java.Person;
import main.java.Subject;
import main.java.dao.SubjectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    private Connection connection;
    PreparedStatement preparedStatement = null;

    public SubjectDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Subject> getAllSubjects() throws SQLException {
        List<Subject> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from postgres.testschema.subject");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
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
    public Collection<Subject> getSubjectsByPerson(Person person) throws SQLException {
        List<Subject> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from postgres.testschema.course a, postgres.testschema.subject b where person_id = ? and a.subject_id = b.subject_id");
            preparedStatement.setInt(1, person.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2)));
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
}
