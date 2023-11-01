package App;

import business.User;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        ArrayList <String> choices1= new ArrayList();
        ArrayList <String> choices2= new ArrayList();
        boolean loggedIn=false;
        User u1;
        choices1.add("0- Register In library");
        choices1.add("1- Log into the library");
        choose(choices1);
        choices2.add("0- view Details of all Books");
        choices2.add("1- view Details on active Loans");
        choices2.add("2- view Details on all your loans");
        choices2.add("3- Borrow a book");
        choices2.add("4- Return a book on Loan");
        choices2.add("5- View current late fees ");
        choices2.add("6- Pay late fee");

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
