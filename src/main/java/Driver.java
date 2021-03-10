import com.enterprise.annotations.TestClass;
import com.enterprise.EnterpriseNoAppropriateConstructorFoundException;
import com.enterprise.annotations.TestMethod;
import com.enterprise.model.MetaTestData;
import com.enterprise.util.HashMap;
import com.enterprise.util.TestDiscovery;
import model.Car;
import ui.AbstractMenu;
import ui.LoginMenu;
import ui.MenuFactory;
import ui.SignUpMenu;
import util.MyList;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Driver {

    public static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args){

        boolean loop = true;

        AbstractMenu sm = MenuFactory.menuFactory ("Sign Up");
        AbstractMenu lm = MenuFactory.menuFactory ("Login");
        System.out.println("Welcome to this Car Dealership app");
        do{
            System.out.println("How may we help you today");
            System.out.println("Sign up, Login or Exit");

            String answer = scan.nextLine();
            if(answer.equalsIgnoreCase("exit")){
                loop = false;
            } else if (answer.equalsIgnoreCase("sign up")){
                sm.showMenu(scan);
            } else if (answer.equalsIgnoreCase("Login")){
                lm.showMenu(scan);
            }
        } while (loop);

    }
}
