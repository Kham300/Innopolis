package dao.daoImpl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DatasourceTest {

    @InjectMocks
    PersonDaoImpl datasource;
    @Mock
    Connection connection;
    @Mock
    PreparedStatement stmt;
    @Before
    public void setUp() throws SQLException {
        when(connection.prepareStatement(eq("INSERT INTO artists(name) VALUES(?)"))).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);
    }

    @Test
    public void testInsertTable() {
        assertEquals(datasource.getAllPersons().size(), 2);

    }
}