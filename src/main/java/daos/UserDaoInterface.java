package daos;

import business.User;
//@author carlson
public interface UserDaoInterface {
    /**
     * Method adds a new user in to the system
     * @param userName, user's name a maximum of 24 characters
     *  @param password, user's password a maximum of 128 characters
     * @param email, user's email a maximum of 128 character
     *   @param phoneNumber, user's phone number maximum of 24 characters
     * @param userType, user type (1 for normal user 2 for admin)
     * @return the id of the newly registered user or returns -1 if the email is already registered or the dateOfBirth is after present day
     * **/
    public int register(String userName, String password, String email, String phoneNumber,int userType);
   /**
    * Method logs in an existing user in to the system
    *
    * @param email,    user's email
    * @param password, user's password
    * @return the userId if details are correct or returns -1 if details are wrong
    **/
    public User logIn(String email, String password);
    /**Disables a particular user
     * @param userId, the userId of the person who is about to disable a user
     * @param disableId , the userId of user to be disabled
     * @return a boolean, true if the use was disabled and false for otherwise
     * **/
    public boolean disAbleMember(int userId, int disableId);
}
