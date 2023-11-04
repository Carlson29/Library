package daos;

import business.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
//@author carlson
class UserDaoTest {



    /**When there is a user with the email already registered**/

    @Test
    void register_whenUserExist() {
        UserDao dao= new UserDao("library");
        int actual= dao.register("Carlson","carl", "carl@gmail.com","0895666431",1);
        int expected=-1;
        assertEquals(actual,expected);
    }
/**delete the user before registering if the user is present*/
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
        if(actual==expected){
            int id=dao.getUserId("caleb@gmail.com");
            User u1= new User(id,"Caleb","Njpe","caleb@gmail.com","0895666431",1,1);
            User u2=dao.getUser(id);
            assertEquals(u1,u2);
        }
    }
/**delete the new user created above after registering **/
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



    /**When an admin tries to disable a normal user**/
    @Test
    void disAbleMember_WhenAdminDisablesNormalUser() {
        UserDao dao= new UserDao("library");
        boolean actual= dao.disAbleMember(2,1);
        boolean expected= true;
        assertEquals(actual,expected);
        if(actual==expected){
            User u1= new User(1,"Carlson","carl","carl@gmail.com","0895666431",1,2);
            User u2=dao.getUser(1);
            assertEquals(u1,u2);
        }

    }
    /**unsuspend the User**/
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
/** test Deleting a user**/
   @Test
    void delete_User() {
        UserDao dao= new UserDao("library");
        int actual= dao.deleteUser(dao.getUserId("naomi@gmail.com"));
        int expected  =1;
        assertEquals(actual,expected);
       if(actual==expected){
           int exp= dao.getUserId("naomi@gmail.com");
           int act=-1;
           assertEquals(exp,act);
       }
    }
/**registering deleted user */
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
        if(actual==expected){
            User u1= dao.getUser(3);
            User u2= new User(3,"Tom","tom", "tom@gmail.com","0892966581",1,1);
            assertEquals(u1,u2);
        }

    }
    /**suspending the unsuspended user above in unsuspendUser() method**/
    @AfterEach
    void suspend_unsuspendedUser() {
        UserDao dao= new UserDao("library");
        dao.disAbleMember(2,3);
    }
/** test getting user Id when given a user email**/
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
/*test getting user when User is available*/
    @Test
    void getUserById_WhenUserIsAvailable() {
        UserDao dao= new UserDao("library");
        User actual= dao.getUser(1);
        User expected= new User(1,"Carlson","carl","carl@gmail.com","0895666431",1,1);
        assertEquals(actual,expected);
    }
    /*test getting user when User is not available*/
    @Test
    void getUserById_WhenUserIsNotAvailable() {
        UserDao dao= new UserDao("library");
        User actual= dao.getUser(6);
        User expected= null;
        assertEquals(actual,expected);
    }


    /**when getting all users**/
    @Test
    void getUsers() {
        UserDao dao= new UserDao("library");
        ArrayList<User> actual= dao.getAllUsers();
        ArrayList<User> expected= new ArrayList();
          expected.add(dao.getUser(1));
          expected.add(dao.getUser(2));
          expected.add(dao.getUser(3));
        expected.add(dao.getUser(dao.getUserId("naomi@gmail.com")));
        assertEquals(actual,expected);
    }

}