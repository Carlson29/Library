package App;

import business.User;
import daos.UserDao;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> choices1 = new ArrayList();
        ArrayList<String> choices2 = new ArrayList();
        ArrayList<String> userType = new ArrayList();
        UserDao dao = new UserDao("library");
        boolean loggedIn = false;
        boolean prompt = true;
        User u1;
        choices1.add("0- Register In library");
        choices1.add("1- Log into the library");
        choices2.add("0- view Details of all Books");
        choices2.add("1- view Details on active Loans");
        choices2.add("2- view Details on all your loans");
        choices2.add("3- Borrow a book");
        choices2.add("4- Return a book on Loan");
        choices2.add("5- View current late fees ");
        choices2.add("6- Pay late fee");
        choices2.add("7- Log out");
        while (prompt ==true){
            int num = choose(choices1);
        if (num == 0) {
            System.out.println("Enter UserName");
            String userName = sc.nextLine();
            System.out.println("Enter password");
            String password = sc.nextLine();
            System.out.println("Enter Email");
            String email = sc.nextLine();
            System.out.println("Enter phone number");
            String phoneNumber = sc.nextLine();
            userType.add("0- Admin");
            userType.add("1- Normal user");
            System.out.println("Enter userType");
            int type = choose(userType);
            if (type == 0) {
                int state = dao.register(userName, password, email, phoneNumber, 2);
                if (state == -1) {
                    System.out.println("Email is in use");
                }
                else if(state ==1){
                    System.out.println("Registered");
                }
            } else if (type == 1) {
                int state = dao.register(userName, password, email, phoneNumber, 1);
                if (state == -1) {
                    System.out.println("Email is in use");
                }
                else if(state ==1){
                    System.out.println("Registered");
                }
            }

        } else if (num == 1) {
            System.out.println("Enter Email");
            String email = sc.nextLine();
            System.out.println("Enter password");
            String password = sc.nextLine();
            u1=dao.logIn(email,password);
            if(u1!=null) {
                System.out.println("logged in*******");
                loggedIn = true;
                //if it's an admin
                if (u1.getUserType() == 2) {
                    choices2.add("8- view Details of all Books");
                    choices2.add("9- view Details on active Loans");
                    choices2.add("10- view Details on all your loans");
                }
                while (loggedIn == true){
                    int choice2 = choose(choices2);
                //view details on all books
                if (choice2 == 0) {

                }
                //view details on active loans
                else if (choice2 == 1) {

                }
                //view details on all loans
                else if (choice2 == 2) {

                }
                //borrow a book
                else if (choice2 == 3) {

                }
                //return a book
                else if (choice2 == 4) {

                }
                //view current late fee
                else if (choice2 == 5) {

                }
                //pay late fee
                else if (choice2 == 6) {
                    //creditCardValidator = new CreditCardValidator();
                }
                //log out
                else if (choice2 == 7) {
                    loggedIn = false;
                }
                //view details on all books as an admin
                else if (choice2 == 8) {

                }
                //view details on all active loans as an admin
                else if (choice2 == 9) {

                }
                //disable a member
                else if (choice2 == 10) {

                }

            }
            }
            else{
                System.out.println("Details don't match");
            }

        }
    }


    }
    public static int choose(ArrayList<String> choices){
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter valid choice");
        for(String choice: choices){
            System.out.println(choice);
        }

        int num=-1;
        if(sc.hasNextInt()) {
            num=sc.nextInt();
            if (num < 0 || num >= choices.size()) {
                System.out.println("please enter valid number");
                choose(choices);
            }
            else{
                return num;
            }
        }
        else {
            System.out.println("please enter number");
            choose(choices);
        }
        return num;
    }


}
