package ui;

import com.enterprise.annotations.TestClass;
import com.enterprise.annotations.TestMethod;
import jdbc.JDBCFactory;
import model.User;
import service.UserService;

import java.util.Scanner;

public class SignUpMenu extends AbstractMenu{

    private static SignUpMenu instance;

    @Override
    public void showMenu(Scanner scan) {
        boolean keepLooping = true;
        int misclick = 0;
        String userName = "";
        UserService us = new UserService(JDBCFactory.daoFactory(User.class));
        do {
            System.out.println("Would you like to sign up? y/n");
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("y")){
                do {
                    System.out.println("Enter a user name you would like to use.");
                    userName = scan.nextLine();
                } while (us.doesUsernameExist(userName));
                System.out.println("Enter a password you would like to use.");
                String passWord = scan.nextLine();
                System.out.println(us.makeUser(userName,passWord) ? "Successfully made " + userName : "Failed to create an account");
                break;
            }else if (answer.equalsIgnoreCase("n")){
                System.out.println("Returning to main menu...");
                keepLooping = false;
            }else {
                misclick++;
                if(misclick > 2){
                    keepLooping = false;
                }
            }


        }while (keepLooping);
    }
    static SignUpMenu getInstance(){
        if(instance == null){
            instance = new SignUpMenu ();
        }
        return instance;
    }
}
