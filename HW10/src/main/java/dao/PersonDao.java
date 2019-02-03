package main.java.dao;

import main.java.Person;
import main.java.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface PersonDao {

    Collection<Person> getAllPersonsBySubject(Subject subject) throws SQLException;

    Collection<Person> getAllPersons();

    void updatePerson(Person person) throws SQLException;

    void deletePerson(Person person) throws SQLException;

    void createPerson(Person person) throws SQLException;

    Person getPerson(int id) throws SQLException;
}
