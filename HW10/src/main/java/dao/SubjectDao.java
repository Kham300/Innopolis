package main.java.dao;

import main.java.Person;
import main.java.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface SubjectDao {

    Collection<Subject> getAllSubjects() throws SQLException;

    Collection<Subject> getSubjectsByPerson(Person person) throws SQLException;
}
