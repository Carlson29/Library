package daos;

import business.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

   /* @Before
    void setUp() {
    }

    @After
    void tearDown() {
    }*/


    /**When there is a user with the email already registered**/

    @Test
    void register_whenUserExist() {
        UserDao dao= new UserDao("library");
        int actual= dao.register("Carlson","carl", "carl@gmail.com","0895666431",1);
        int expected=-1;
        assertEquals(actual,expected);
    }

    @BeforeEach
    void delete_NewUser()  {
        UserDao dao= new UserDao("library");
        int id= dao.getUserId("caleb@gmail.com");
        dao.deleteUser(id);
    }


    /**When a user with a distinct email tries to register**/
    @Test
    void register_NewUser() {
        UserDao dao= new UserDao("library");
        int actual= dao.register("Caleb","Njpe", "caleb@gmail.com","0895666431",1);
        int expected=1;
        assertEquals(actual,expected);
    }

    @AfterEach
    void deleteUser() {
        UserDao dao= new UserDao("library");
        int id= dao.getUserId("caleb@gmail.com");
        dao.deleteUser(id);
    }


    /**When login details are correct*/
    @Test
    void logIn_WhenDetailsMatch() {
        UserDao dao= new UserDao("library");
        User actual= dao.logIn( "sam@gmail.com","sam");
        User expected= new User(2,"Samuel","sam", "sam@gmail.com","0895666581",2,1);
        assertEquals(actual,expected);
    }
    /**When details do not match**/
    @Test
    void logIn_WhenDetailsDoNotMatch() {
        UserDao dao= new UserDao("library");
        User actual= dao.logIn( "carlson@gmail.com","carl123");
        User expected= null;
        assertEquals(actual,expected);
    }

    /**When disabled user tries logging in**/
    @Test
    void logIn_WhenDisabledUserTriesLoggingIn() {
        UserDao dao= new UserDao("library");
        User actual= dao.logIn( "tom@gmail.com","tom");
        User expected= null;
        assertEquals(actual,expected);
    }


    /**When an admin tries to disable a normal user**/
    @Test
    void disAbleMember_WhenAdminDisablesNormalUser() {
        UserDao dao= new UserDao("library");
        boolean actual= dao.disAbleMember(2,1);
        boolean expected= true;
        assertEquals(actual,expected);
    }
    @AfterEach
    void unSuspendUserAfterSuspending() {
        UserDao dao= new UserDao("library");
        dao.unsuspendUser(1);
    }

    /**When a normal user tries to disable another user**/
    @Test
    void disAbleMember_WhenNormalUserTriesToDisableAUser() {
        UserDao dao= new UserDao("library");
        boolean actual= dao.disAbleMember(1,1);
        boolean expected= false;
        assertEquals(actual,expected);
    }

    /**When an admin tries to disable another admin**/
    @Test
    void disAbleMember_WhenAdminTriesToDisableAdmin() {
        UserDao dao= new UserDao("library");
        boolean actual= dao.disAbleMember(2,2);
        boolean expected= false;
        assertEquals(actual,expected);
    }
/**Deleting a user**/
   @Test
    void delete_User() {
        UserDao dao= new UserDao("library");
        int actual= dao.deleteUser(dao.getUserId("naomi@gmail.com"));
        int expected  =1;
        assertEquals(actual,expected);
    }
/**registering deleted user*/
   @AfterEach
    void registerDeletedUser() {
        UserDao dao= new UserDao("library");
        dao.register("Naomi","nao", "naomi@gmail.com","895576248",1);
    }
/***unsuspending a user**/
    @Test
    void unsuspendUser() {
        UserDao dao= new UserDao("library");
        int expected= dao.unsuspendUser(3);
        int actual =1;
        assertEquals(actual,expected);
    }
    /**suspending the unsuspended user above in unsuspendUser() method**/
    @AfterEach
    void suspend_unsuspendedUser() {
        UserDao dao= new UserDao("library");
        dao.disAbleMember(2,3);
    }
/**getting user Id when given a user email**/
    @Test
    void getUserId() {
        UserDao dao= new UserDao("library");
        int expected= dao.getUserId("carl@gmail.com");
        int actual =1;
        assertEquals(actual,expected);
    }
/**When the email doesn't match any email in the database**/
    @Test
    void getUserId_WhenNoIdIsFound() {
        UserDao dao= new UserDao("library");
        int expected= dao.getUserId("paul@gmail.com");
        int actual =-1;
        assertEquals(actual,expected);
    }

}