package dao.daoImpl;

import model.Person;
import model.Subject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PersonDaoImplTest {


    @Mock
    private DBUtil ds;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;

    private Person p;

    private Subject sub;

    @Before
    public void setUp() throws Exception {
        assertNotNull(ds);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getDBConnection()).thenReturn(c);
        p = new Person();
        p.setId(1);
        p.setName("Johannes");
        when(rs.first()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn(p.getName());
        when(stmt.executeQuery()).thenReturn(rs);
    }

    @Test
    public void nullCreateThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new PersonDaoImpl(ds.getDBConnection()).createPerson(null), "PERSON MUST BE NOT NULL");
    }

    @Test
    public void createPerson() {
        new PersonDaoImpl(ds.getDBConnection()).createPerson(p);
    }

    @Test
    public void createAndRetrievePerson() {
        PersonDaoImpl dao = new PersonDaoImpl(ds.getDBConnection());
        dao.createPerson(p);
        Person r = dao.getPerson(1);
        assertEquals(p, r);
    }

    @Test
    void getAllPersonsBySubject() {
    }

    @Test
    void getAllPersons() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }


    @Test
    void getPerson() {
    }
}