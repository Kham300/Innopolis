package dao;

import main.java.Person;
import main.java.Subject;

import java.sql.SQLException;
import java.util.Collection;

/**
 * The interface Subject dao.
 */
public interface SubjectDao {

    /**
     * Gets all subjects.
     *
     * @return the all subjects
     * @throws SQLException the sql exception
     */
    Collection<Subject> getAllSubjects();

    /**
     * Gets subjects by person.
     *
     * @param person the person
     * @return the subjects by person
     * @throws SQLException the sql exception
     */
    Collection<Subject> getSubjectsByPerson(Person person);
}
