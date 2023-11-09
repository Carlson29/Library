package App;

import java.util.Scanner;
import business.Book;
import business.Loan;
import business.User;
import daos.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> choices1 = new ArrayList();
        ArrayList<String> choices2 = new ArrayList();
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
        while (prompt == true) {
            int num = choose(choices1);
            if (num == 0) {
                register();
            } else if (num == 1) {
                //log in
                u1 = login();
                if (u1 != null) {
                    if (u1.getDisable() == 1) {
                        System.out.println("logged in*******");
                        loggedIn = true;

                        //if it's an admin add the options
                        if (u1.getUserType() == 2) {
                            choices2.add("8- add a book to library");
                            choices2.add("9- Increase a copy of a book");
                            choices2.add("10- Disable a member");
                        }
                        while (loggedIn == true) {
                            int choice2 = choose(choices2);
                            //view details on all books
                            if (choice2 == 0) {
                                BookDaoInterface bookDao = new BookDao("mainlibrary");
                                System.out.println("The current books available:");
                                Book displayedBook = bookDao.DisplayAllBook();
                                System.out.println(displayedBook);
                            }
                            //view details on active loans
                            else if (choice2 == 1) {
                                System.out.println("Your current loan detail is:");
                                LoanDaoInterface loanDao = new LoanDao("mainlibrary");
                                List<Loan> loans = loanDao.getLoansCurrent(u1.getUserId());
                                showDetailsLoans(loans);
                            }
                            //view details on all loans
                            else if (choice2 == 2) {
                                System.out.println("Your former loan detail is:");
                                LoanDaoInterface loanDao = new LoanDao("mainlibrary");
                                List<Loan> loans = loanDao.getLoansFormer(u1.getUserId());
                                showDetailsLoans(loans);
                            }
                            //borrow a book
                            else if (choice2 == 3) {
                                BookDaoInterface bookDao = new BookDao("mainlibrary");
                                System.out.println("\nEnter Book ID to Borrow:");
                                int bookIdToBorrow = sc.nextInt();
                                System.out.println("Enter User ID:");
                                int userIdBorrow = sc.nextInt();
                                System.out.println("\nBorrowing Book (Book ID: " + bookIdToBorrow + ", User ID: " + userIdBorrow + "):");
                                int borrowRowsAffected = bookDao.borrowBook(bookIdToBorrow, userIdBorrow);
                                System.out.println("Rows Affected: " + borrowRowsAffected);
                            }
                            //return a book
                            else if (choice2 == 4) {
                                BookDaoInterface bookDao = new BookDao("mainlibrary");
                                System.out.print("\nEnter Book ID to Return: ");
                                int bookIdToReturn = sc.nextInt();
                                System.out.print("Enter User ID: ");
                                int userIdForReturn = sc.nextInt();
                                System.out.println("\nReturning Book (Book ID: " + bookIdToReturn + ", User ID: " + userIdForReturn + "):");
                                int returnRowsAffected = bookDao.returnBook(bookIdToReturn, userIdForReturn);
                                System.out.println("Rows Affected: " + returnRowsAffected);
                            }
                            //view current late fee
                            else if (choice2 == 5) {
                                viewLatefee(u1);
                            }
                            //pay late fee and return book
                            else if (choice2 == 6) {
                                payLateFee(u1);
                            }
                            //log out
                            else if (choice2 == 7) {
                                loggedIn = false;
                            }
                            //view details on all books as an admin
                            else if (choice2 == 8) {
                                BookDaoInterface bookDao = new BookDao("mainlibrary");
                                Book displayedBook = bookDao.DisplayAllBook();
                                System.out.println(displayedBook);
                            }
                            //view details on all active loans as an admin
                            else if (choice2 == 9) {
                                LoanDaoInterface loanDao = new LoanDao("mainlibrary");
                                List<Loan> loans = loanDao.getLoanAsAdmin(u1.getUserType());
                                showDetailsLoans(loans);
                            }
                            //disable a member
                            else if (choice2 == 10) {
                                disableUser(u1);
                            }

                        }
                    } else {
                        System.out.println("You are suspended for the moment");
                    }
                } else {
                    System.out.println("Details don't match");
                }

            }
        }


    }

    /**
     * method to loop through ArrayList of strings and validate user choice
     *
     * @param choices, Arraylist of Strings
     * @return a valid int (user choice)
     **/
    public static int choose(ArrayList<String> choices) {
        Scanner sc = new Scanner(System.in);
        boolean state = false;
        int num = -1;
        while (state == false) {
            System.out.println("Please enter valid choice");
            for (String choice : choices) {
                System.out.println(choice);
            }

            if (sc.hasNextInt()) {
                num = sc.nextInt();
                /// sc.nextLine();
                if (num < 0 || num >= choices.size()) {
                    System.out.println("please enter valid number");
                    //choose(choices);
                } else {
                    return num;
                }
            } else {
                sc.nextLine();
                System.out.println("please enter number");
                //choose(choices);
            }

        }
        return num;
    }

    /**
     * method to loop through ArrayList of Users and validate user choice
     *
     * @param choices, Arraylist of Users
     * @return a valid int (user choice)
     */

    public static int chooseUser(ArrayList<User> choices) {
        Scanner sc = new Scanner(System.in);
        boolean state = false;
        int num = -1;
        while (state == false) {
            System.out.println("Please enter valid choice");
            int i = 0;
            for (User choice : choices) {
                System.out.println("- " + i + " " + choice.toString());
                i++;
            }

            if (sc.hasNextInt()) {
                num = sc.nextInt();
                sc.nextLine();
                if (num < 0 || num >= choices.size()) {
                    System.out.println("please enter valid number");

                } else {
                    return num;
                }
            } else {
                sc.nextLine();
                System.out.println("please enter number");

            }
        }
        return num;
    }

    /**
     * method to validate a credit card number
     *
     * @param number, credit card number
     * @return true if valid or false if invalid
     * reference https://www.geeksforgeeks.org/program-credit-card-number-validation/
     */
    public static boolean validateCard(long number) {
        boolean state = false;
        String num = number + "";
        int odd = 0;
        int even = 0;
        if (num.length() >= 13 && num.length() <= 16) {
            if (Integer.parseInt(num.substring(0, 1)) == 4 || Integer.parseInt(num.substring(0, 1)) == 5 || Integer.parseInt(num.substring(0, 2)) == 37 || Integer.parseInt(num.substring(0, 1)) == 6) {
                //adding numbers in odd position
                for (int i = num.length() - 1; i >= 0; i -= 2) {
                    odd += Integer.parseInt(num.charAt(i) + "");
                }
                //adding numbers in double number at even position
                for (int i = num.length() - 2; i >= 0; i -= 2) {
                    int check = Integer.parseInt(num.charAt(i) + "") * 2;
                    if (check <= 9) {
                        even += check;
                    } else {
                        even += check / 10 + check % 10;
                    }
                }
                return (even + odd) % 10 == 0;

            }
        }
        return state;
    }

    /**
     * Pays a User's late fee and returns the book
     *
     * @u1 a User
     **/
    public static void payLateFee(User u1) {
        UserDao dao = new UserDao("mainlibrary");
        LoanFeeDao dao2 = new LoanFeeDao("mainlibrary");
        Scanner sc = new Scanner(System.in);
        ArrayList<Loan> lateLoans = dao2.getOverDueLoans(u1.getUserId());
        ArrayList<String> lateBooks = new ArrayList();
        ArrayList<Double> lateAmount = new ArrayList();
        for (int i = 0; i < lateLoans.size(); i++) {
            if (lateLoans.get(i).getReturnDate() == null) {
                //calculating late fee €0.5 a day
                double amount = calculateFee(lateLoans.get(i).getDueDate().toString());
                lateAmount.add(amount);
                lateBooks.add("-" + i + " " + dao2.getBookName(lateLoans.get(i).getBookId()) + " price: €" + amount);
            }
        }
        if (lateBooks.size() > 0) {
            //choose book to pay
            int choosen = choose(lateBooks);
            int loanId = lateLoans.get(choosen).getLoanId();
            double pay = lateAmount.get(choosen);
            System.out.println("Enter card number details example:379354508162306");
            if (sc.hasNextLong()) {
                long cardNum = sc.nextLong();
                //validate card number
                if (validateCard(cardNum) == true) {
                    System.out.println("Enter security code");
                    if (sc.hasNextInt()) {
                        int securityCode = sc.nextInt();
                        if (validateSecurtiyCode(securityCode) == true) {
                            System.out.println("invalid security code");
                        } else {
                            System.out.println("enter expiry month in number format (1-12)");
                            if (sc.hasNextInt()) {
                                int month = sc.nextInt();
                                if (validateMonth(month) == true) {
                                    LocalDateTime now = LocalDateTime.now();
                                    System.out.println("enter year");
                                    if (sc.hasNextInt()) {
                                        int year = sc.nextInt();
                                        if (ifExpired(year, month) == true) {
                                            dao2.insertReturnDate(loanId);
                                            dao2.payLateFee(loanId, pay);
                                            System.out.println("Paid  ");
                                        } else {
                                            System.out.println("sorry Expired card");
                                        }
                                    } else {
                                        System.out.println("Enter year in figures");
                                    }
                                } else {
                                    System.out.println("invalid month");
                                }
                            } else {
                                System.out.println("enter number");
                            }

                        }
                    }
                } else {
                    System.out.println("invalid card number");
                }
            } else {
                System.out.println("Please enter number");
            }
        } else {
            System.out.println("no late fees available");
        }

    }

    /**
     * disables a user
     *
     * @param u1, a User
     **/
    public static void disableUser(User u1) {
        UserDao dao = new UserDao("mainlibrary");
        LoanFeeDao dao2 = new LoanFeeDao("mainlibrary");
        ArrayList<User> users = dao.getAllUsers();
        //get users
        ArrayList<User> normalUsers = new ArrayList();
        for (User u : users) {
            //if it's a normal user or if the user isn't already disabled
            if (u.getUserType() == 1 && u.getDisable() == 1) {
                normalUsers.add(u);
            }
        }
        if (users.size() > 0) {
            int selectedUser = chooseUser(normalUsers);
            int userId = normalUsers.get(selectedUser).getUserId();
            dao.disAbleMember(u1.getUserType(), userId);
            System.out.println("user disabled");
        } else {
            System.out.println("No users available");
        }
    }

    /**
     * view all late fees for a User
     *
     * @param u1, a User
     **/
    public static void viewLatefee(User u1) {
        UserDao dao = new UserDao("mainlibrary");
        LoanFeeDao dao2 = new LoanFeeDao("mainlibrary");
        System.out.println("current late fees are");
        ArrayList<Loan> lateLoans = dao2.getOverDueLoans(u1.getUserId());
        ArrayList<String> lateBooks = new ArrayList();
        ArrayList<Double> lateAmount = new ArrayList();
        //loop through overdue loans
        for (int i = 0; i < lateLoans.size(); i++) {
            //making sure the loan wasn't returned
            if (lateLoans.get(i).getReturnDate() == null) {
                //calculating late fee €0.5 a day
                double amount = calculateFee(lateLoans.get(i).getDueDate().toString());
                lateAmount.add(amount);
                lateBooks.add("-" + i + " " + dao2.getBookName(lateLoans.get(i).getBookId()) + " price: €" + amount);
            }
        }
        for (String late : lateBooks) {
            System.out.println(late);
        }
        if (lateBooks.size() == 0) {
            System.out.println("No late fees");
        }
    }

    /**
     * logs in a user, returns a User if the User logs in or null if the User doesn't
     *
     * @return User
     **/
    public static User login() {
        UserDao dao = new UserDao("mainlibrary");
        LoanFeeDao dao2 = new LoanFeeDao("mainlibrary");
        Scanner sc = new Scanner(System.in);
        User u1 = null;
        System.out.println("Enter Email");
        String email = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        //log in
        u1 = dao.logIn(email, password);
        return u1;
    }

    /**
     * Registers a User to the system
     **/
    public static void register() {
        UserDao dao = new UserDao("mainlibrary");
        LoanFeeDao dao2 = new LoanFeeDao("mainlibrary");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> userType = new ArrayList();
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
        //for admins
        if (type == 0) {
            //register
            int state = dao.register(userName, password, email, phoneNumber, 2);
            if (state == -1) {
                System.out.println("Email is in use");
            } else if (state == 1) {
                System.out.println("Registered");
            }
        }
        // for normal users
        else if (type == 1) {
            int state = dao.register(userName, password, email, phoneNumber, 1);
            if (state == -1) {
                System.out.println("Email is in use");
            } else if (state == 1) {
                System.out.println("Registered");
            }
        }

    }

    /**
     * Valid date a card's security Code. The code must contain 3 numbers
     *
     * @param securityCode, returns true if it's valid and false otherwise
     **/
    public static boolean validateSecurtiyCode(int securityCode) {
        String securityCode2 = securityCode + "";
        if (securityCode2.length() != 3) {
            return true;
        }
        return false;
    }

    /**
     * calculates the late fee €0.5 per day
     *
     * @param date, in a date format
     * @return a double, the late fee
     */
    public static double calculateFee(String date) {
        LocalDate start = LocalDate.parse(date);
        LocalDateTime end = LocalDateTime.now();
        //get number of days
        long diffInDays = ChronoUnit.DAYS.between(start, end);
        String diffInDays2 = diffInDays + "";
        //calculating late fee €0.5 a day
        double amount = Integer.parseInt(diffInDays2) * 0.5;
        return amount;
    }

    /**
     * validates the month must from 1-12 inclusive
     *
     * @param month,
     * @return boolean, true for valid and false otherwise
     **/
    public static boolean validateMonth(int month) {
        if (month > 0 && month < 12) {
            return true;
        }
        return false;
    }

    /**
     * checks if card is expired
     *
     * @param month,
     * @param year,
     * @return true, if valid and false if otherwise
     **/
    public static boolean ifExpired(int year, int month) {
        LocalDateTime now = LocalDateTime.now();
        if (month < now.getMonthValue() && year >= now.getYear()) {
            return true;
        }
        return false;
    }

    public static void showDetailsLoans(List<Loan> loans) {
        for (Loan l : loans) {
            System.out.println("Loan ID: " + l.getLoanId());
            System.out.println("Book ID: " + l.getBookId());
            System.out.println("User ID: " + l.getUserId());
            System.out.println("Date of loan: " + l.getDateOfLoan());
            System.out.println("Due date: " + l.getDueDate());
            System.out.println("Return date: " + l.getReturnDate());
        }
    }
}
