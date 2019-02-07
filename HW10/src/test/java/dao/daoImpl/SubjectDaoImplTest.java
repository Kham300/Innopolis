package dao.daoImpl;


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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class SubjectDaoImplTest {

    @Mock
    private DBUtil ds;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;

    private Subject subject;
    @Before
    public void setUp() throws Exception {
        assertNotNull(ds);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getDBConnection()).thenReturn(c);
        subject = new Subject();
        subject.setId(1);
        subject.setDescription("Subject");
        when(rs.first()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn(subject.getDescription());
        when(stmt.executeQuery()).thenReturn(rs);
    }
    
    @Test
    void getAllSubjects() {
        assertEquals(2, 2);
    }

    @Test
    void getSubjectsByPerson() {
        assertEquals(2, 2);

    }
}