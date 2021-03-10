package jdbc;

import config.ConnectionUtil;
import model.AccountType;
import model.User;
import util.MyCollection;
import util.MyList;

import java.sql.*;

public class UserJDBC implements GenericDao<User,String>{

    private static UserJDBC instance;

    private UserJDBC(){}

    static UserJDBC getInstance(){
        if(instance == null){
            instance = new UserJDBC();
        }
        return instance;
    }

    @Override
    public int save(User u) {
        try {

            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(
                  "insert into app_user values (?,?,?)");
            ps.setString(1,u.getUserName());
            ps.setString(2,u.getPassword());
            ps.setInt (3,u.getType().ordinal ());

            int i = ps.executeUpdate();
            ps.close ();
            System.out.println("The number of added user were "+ i);
            return i;


        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean registerToBe(User u, int x){
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "update app_user" + "\n" + "set account_type = (?)" + "\n" +
                            "where username = (?)");
            //can use u.getType().ordinal () to access the index of enum
            ps.setInt (1,x);
            ps.setString (2, u.getUserName ());
            ps.executeUpdate ();
            ps.close ();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
            return false;
        }
    }

    @Override
    public int checkAccountType(String s) {
        User u = getByString (s);
        return u.getType ().ordinal ();
    }

    @Override
    public User getByString(String s) {
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from app_user where username = (?)");
            ps.setString (1,s);
            ResultSet rs = ps.executeQuery ();
            while(rs.next ()){
                String username = rs.getString("username");
                String password = rs.getString ("password");
                int typeNum = rs.getInt ("account_type");
                AccountType accountType = null;
                switch(typeNum){
                    case(0):
                        accountType = AccountType.USER;
                        break;
                    case(1):
                        accountType = AccountType.CUSTOMER;
                        break;
                    case(2):
                        accountType = AccountType.EMPLOYEE;
                        break;
                    default:
                        System.out.println ("Unknown account type");
                }
                if(username.equalsIgnoreCase (s)){
                    return new User (username, password,accountType);
                }
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return null;
    }

    @Override
    public User getByInt(int id) {
        return null;
    }

    @Override
    public MyList<User> getAll() {
        MyList<User> userList = new MyList<User> ();
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from app_user");
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                String username = rs.getString ("username");
                String password = rs.getString ("password");
                int typeNum = rs.getInt ("account_type");
                AccountType accountType = null;
                switch (typeNum) {
                    case (0):
                        accountType = AccountType.USER;
                        break;
                    case (1):
                        accountType = AccountType.CUSTOMER;
                        break;
                    case (2):
                        accountType = AccountType.EMPLOYEE;
                        break;
                    default:
                        System.out.println ("Unknown account type");
                }
                userList.add (new User (username, password, accountType));
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return userList;
    }

        @Override
    public boolean remove(String s) {
            int i = 0;
                try {
                    PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(
                            "delete from app_user where username = (?)");
                    ps.setString(1,s);
                    i = ps.executeUpdate();
                    ps.close ();
                    System.out.println("The number of deleted rows were "+ i);
                    ps.close ();
                } catch (SQLException throwables) {
                    throwables.printStackTrace ();
                }
            return i>0;
    }

    @Override
    public MyList<User> getOwn(String s) {
        return null;
    }

    @Override
    public String getPayment(String s) {
        return null;
    }

    @Override
    public int rejectAllOtherOffer(int id) {
        return 0;
    }

    @Override
    public int accept(int s) {
        return 0;
    }

    @Override
    public int reject(int s) {
        return 0;
    }

    @Override
    public int chageCarFields(int s) {
        return 0;
    }

    @Override
    public boolean hasAOwner(String id) {
        return false;
    }

    @Override
    public String getAllSoldCars() {
        return null;
    }
}
