package dao.daoImpl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PersonDaoImpl.class, DriverManager.class, Connection.class})
public class PowerMockJdbcTest {

    @Mock
    Connection con;

    @Mock
    PreparedStatement ps;

    @Test
    public void insertTableTest() throws Exception {

        PowerMockito.mockStatic(DriverManager.class);
        PowerMockito.when(DriverManager.getConnection(
                Mockito.eq("jdbc:mysql://127.0.0.1:3306/java"),
                Mockito.eq("root"),
                Mockito.eq(""))).thenReturn(con);

        PersonDaoImpl personDao = Mockito.mock(PersonDaoImpl.class);

        Mockito.when(con.prepareStatement(Mockito.eq("INSERT INTO artists(name) VALUES(?)"))).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenReturn(1);


        Mockito.verify(ps).setString(Mockito.eq(1), Mockito.eq("Test"));
        Mockito.verify(ps).executeUpdate();
        Mockito.verify(con).prepareStatement(Mockito.eq("INSERT INTO artists(name) VALUES(?)"));

    }
}
