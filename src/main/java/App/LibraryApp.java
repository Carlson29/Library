package App;

import business.Loan;
import business.User;
import daos.Dao;
import daos.LoanFeeDao;
import daos.UserDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> choices1 = new ArrayList();
        ArrayList<String> choices2 = new ArrayList();
        ArrayList<String> userType = new ArrayList();
        UserDao dao = new UserDao("library");
        LoanFeeDao dao2 = new LoanFeeDao("library");
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
                if (u1.getDisable() == 1){
                    System.out.println("logged in*******");
                loggedIn = true;

                //if it's an admin
                if (u1.getUserType() == 2) {
                    choices2.add("8- view Details of all Books");
                    choices2.add("9- view Details on active Loans");
                    choices2.add("10- view Details on all your loans");
                }
                while (loggedIn == true) {
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
                    //pay late fee and return book
                    else if (choice2 == 6) {
                        //creditCardValidator = new CreditCardValidator();
                        ArrayList <Loan> lateLoans = dao2.getOverDueLoans(u1.getUserId());
                        ArrayList <String> lateBooks = new ArrayList();
                        ArrayList <Double> lateAmount = new ArrayList();
                        for(int i=0; i<lateLoans.size(); i++){
                            LocalDate start = LocalDate.parse(lateLoans.get(i).getDueDate().toString());
                            LocalDateTime end = LocalDateTime.now();

                            long diffInDays = ChronoUnit.DAYS.between(start, end);
                            String diffInDays2= diffInDays+"";
                            double amount = Integer.parseInt(diffInDays2)*0.5;
                            lateAmount.add(amount);
                            lateBooks.add("-"+ i +" "+dao2.getBookName(lateLoans.get(i).getBookId()) + " price: "+ amount);
                        }
                       int choosen =choose(lateBooks);
                        int loanId=lateLoans.get(choosen).getLoanId();
                         double pay=lateAmount.get(choosen);
                            System.out.println("Enter card number details");
                            long cardNum=sc.nextLong();
                            if(validateCard(cardNum)==true){
                                System.out.println("Enter security code");
                                if(sc.hasNextInt()){
                                    int securityCode=sc.nextInt();
                                    String securityCode2= securityCode+"";
                                    if(securityCode2.length()!=2){
                                        System.out.println("invalid security code");
                                    }
                                    else{
                                        dao2.p
                                    }
                                }
                            }
                            else{
                                System.out.println("invalid card number");
                            }
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
                     ArrayList <User> users=dao.getAllUsers();
                        ArrayList <User> normalUsers=new ArrayList();
                     for(User u:users){
                         if(u.getUserType()==1){
                            normalUsers.add(u);
                         }
                     }
                   int selectedUser =chooseUser(normalUsers);
                   int userId  = normalUsers.get(selectedUser).getUserId();
                   dao.disAbleMember(2,userId);
                   System.out.println("The user disabled");
                    }

                }
            }
                else {
                    System.out.println("You are suspended for the moment");
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


    public static int chooseUser(ArrayList<User> choices){
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter valid choice");
        for(User choice: choices){
            System.out.println(choice.toString());
        }

        int num=-1;
        if(sc.hasNextInt()) {
            num=sc.nextInt();
            if (num < 0 || num >= choices.size()) {
                System.out.println("please enter valid number");
                chooseUser(choices);
            }
            else{
                return num;
            }
        }
        else {
            System.out.println("please enter number");
            chooseUser(choices);
        }
        return num;
    }

    public static boolean validateCard(long number){
       boolean state=false;
       String num=number +"";
       int odd=0;
        int even=0;
       if(num.length()>= 13 && num.length() <= 16) {
         if(num.substring(0,1).equalsIgnoreCase("4") || num.substring(0,1).equalsIgnoreCase("5") || num.substring(0,2).equalsIgnoreCase("37") || num.substring(0,1).equalsIgnoreCase("6")){
             //adding numbers in odd position
             for (int i =num.length() - 1; i >= 0; i -= 2) {
                 odd += Integer.parseInt(num.charAt(i) + "");
             }
             //adding numbers in double number at even position
             for (int i = num.length()  - 2; i >= 0; i -= 2 ) {
                 int check = Integer.parseInt(num.charAt(i) + "") * 2;
                 if(check>9){
                     even += check / 10 + check % 10;
                 }
             }
             return even+odd %10==0;

         }
       }
       return state;
    }

    public static boolean securityCode(int number){
        boolean state=false;
        String num=number +"";
       if(num.length()==2){
           return true;
       }

        return state;
    }


}
