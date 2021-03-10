package ui;

import jdbc.JDBCFactory;
import jdbc.UserJDBC;
import model.User;
import service.UserService;

import java.util.Scanner;

public class LoginMenu extends AbstractMenu{

    private static LoginMenu instance;
    @Override
    public void showMenu(Scanner scan) {

        System.out.println("Welcome to Login Menu");
        System.out.println("Please enter your User Name");
        String userName = scan.nextLine();
        System.out.println("Please enter your Password");
        String password = scan.nextLine();
        User u = new UserService(JDBCFactory.daoFactory(User.class)).findUserByUserName(userName);
        if(u == null|| !u.getPassword().equals(password)){
            System.out.println("login failed");
        } else {
            System.out.println("You have successfully logged in");
            UserMenu um = new UserMenu ();
            um.showMenu (scan,u);
        }

    }

    static LoginMenu getInstance(){
        if(instance == null){
            instance = new LoginMenu ();
        }
        return instance;
    }
}
