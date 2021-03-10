package service;

import com.enterprise.annotations.TestClass;
import com.enterprise.annotations.TestMethod;
import jdbc.GenericDao;
import model.AccountType;
import model.Offer;
import model.User;
import util.MyList;

public class UserService {

    GenericDao<User,String> jdbc;

    public UserService(GenericDao<User, String> jdbc) {
        this.jdbc = jdbc;
    }

    public boolean doesUsernameExist(String username){

        return findUserByUserName(username) != null;
    }

    public User findUserByUserName(String username){

        return jdbc.getByString (username);
    }

    public boolean makeUser(String username, String password){
        if (!doesUsernameExist (username)) {
            jdbc.save (new User (username, password));
                return true;
            }
            return false;
    }

    public boolean registerAs(String userName, int x){
        if (findUserByUserName (userName) != null) {
            return jdbc.registerToBe (findUserByUserName (userName),x);
        }
        return false;
    }

    public int checkAccount(String username){
        return jdbc.checkAccountType (username);
    }
}
