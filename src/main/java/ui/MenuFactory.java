package ui;

public class MenuFactory {

    public static AbstractMenu menuFactory(String menuType){
        switch(menuType){
            case "Login":
                return LoginMenu.getInstance();
            case "Sign Up":
                return SignUpMenu.getInstance();
            default:
                throw new IllegalArgumentException("The menu has not been developed for that yet.");
        }
    }
}
