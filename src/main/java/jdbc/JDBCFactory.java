package jdbc;

public class JDBCFactory {

    public static GenericDao daoFactory(Class c){
        switch(c.getName()){
            case "model.User":
                return UserJDBC.getInstance();
            case"model.Car":
                return CarJDBC.getInstance();
            case"model.Offer":
                return OfferJDBC.getInstance();
            default:
                throw new IllegalArgumentException("The class provided does not have a corresponding dao object");
        }
    }
}
