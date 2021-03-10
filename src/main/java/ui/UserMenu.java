package ui;

import jdbc.JDBCFactory;
import model.User;
import service.UserService;

import java.util.Scanner;

public class UserMenu{
    public void showMenu(Scanner scan, User u) {
        UserService us = new UserService(JDBCFactory.daoFactory(User.class));
        EmployeeMenu em = new EmployeeMenu ();
        CustomerMenu cm = new CustomerMenu ();

        //first we check account type, if the user already has an account type that isn't reguar user,
        // there is no need to make the user go through this again.
        int res = us.checkAccount (u.getUserName ());
        switch(res){
            case(0):
                System.out.println ("Welcome to User menu");
                System.out.println ("Do you want to register for a Customer or Employee account)? ");
                System.out.println ("Type c for customer, e for employee, n for no thanks");
                String answer = scan.nextLine();
                if (answer.equalsIgnoreCase("c")){
                    boolean result = us.registerAs(u.getUserName (),1);
                    System.out.println(result ? "Successfully register " + u.getUserName ()+" into a customer account": "Failed to register as customer");
                    if (result){
                        cm.showMenu (scan,u);
                    }
                } else if((answer.equalsIgnoreCase("e"))){
                    boolean result = us.registerAs(u.getUserName (),2);
                    System.out.println(result ? "Successfully register " + u.getUserName ()+" into a employee account": "Failed to register as employee");
                    if (result){
                        em.showMenu (scan,u);
                    }
                } else if ((answer.equalsIgnoreCase ("n"))){
                    System.out.println ("Returning to main menu...");
                }
                break;
            case(1):
                cm.showMenu (scan,u);
                break;
            case(2):
                em.showMenu (scan,u);
                break;
            default:
                System.out.println ("Not a valid account ID");
        }
    }
}
