package daos;

import business.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MockTest {

    public MockTest(){

    }
    @Test
    public void testLogin() throws SQLException
    {
        // Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        User u= new User(1,"Carlson","carl","carl@gmail.com","0895666431",1,1);

        when(dbConn.prepareStatement("Select * from users where email =? and password=? and disable=1 ")).thenReturn(ps);

        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("userId")).thenReturn(u.getUserId());
        when(rs.getString("userName")).thenReturn(u.getUserName());
        when(rs.getString("password")).thenReturn(u.getPassword());
        when(rs.getString("email")).thenReturn(u.getEmail());
        when(rs.getString("phoneNumber")).thenReturn(u.getPhoneNumber());
        when(rs.getInt("userType")).thenReturn(u.getUserType());
        when(rs.getInt("disable")).thenReturn(u.getDisable());
        /*ps.setString(1,u.getEmail());
        ps.setString(2,u.getPassword());*/
      /* verify(ps).executeQuery();*/
        UserDao dao= new UserDao(dbConn);
        User u2=dao.logIn("carl@gmail.com",u.getPassword());
        verify(ps).setString(1,u.getEmail());
        verify(ps).setString(2,u.getPassword());

        assertEquals(u,u2);
    }


}
