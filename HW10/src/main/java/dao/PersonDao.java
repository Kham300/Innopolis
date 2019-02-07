package dao;


import model.Person;
import model.Subject;

import java.util.Collection;

/**
 * The interface model.Person dao.
 */
public interface PersonDao {

    /**
     * Gets all persons by subject.
     *
     * @param subject the subject
     * @return the all persons by subject
     */
    Collection<Person> getAllPersonsBySubject(Subject subject);

    /**
     * Gets all persons.
     *
     * @return the all persons
     */
    Collection<Person> getAllPersons();

    /**
     * Update person.
     *
     * @param person the person
     */
    void updatePerson(Person person);

    /**
     * Delete person.
     *
     * @param person the person
     */
    void deletePerson(Person person);

    /**
     * Create person.
     *
     * @param person the person
     */
    void createPerson(Person person);

    /**
     * Gets person.
     *
     * @param id the id
     * @return the person
     */
    Person getPerson(int id);
}
