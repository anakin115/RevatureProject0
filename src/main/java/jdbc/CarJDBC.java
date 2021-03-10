package jdbc;

import config.ConnectionUtil;
import model.AccountType;
import model.Car;
import model.User;
import util.MyCollection;
import util.MyList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarJDBC implements GenericDao<Car,String>{

    private static CarJDBC instance;

    static CarJDBC getInstance(){
        if(instance == null){
            instance = new CarJDBC ();
        }
        return instance;
    }

    @Override
    public int save(Car c) {
        try {

            PreparedStatement ps = ConnectionUtil.getInstance().getConnection().prepareStatement(
                    "insert into cars values (?,?,?,?,?,?,?,?)");
            ps.setString(1,c.getCarid ());
            ps.setString(2,c.getMake ());
            ps.setString (3,c.getModel ());
            ps.setInt (4,c.getYear ());
            ps.setFloat (5,c.getPrice ());
            ps.setFloat (6,c.getRemainingPayment ());
            ps.setInt (7, c.getPaymentType ());
            ps.setString (8,c.getOwnership ());

            int i = ps.executeUpdate();
            ps.close ();
            System.out.println("The number of added car were "+ i);
            return i;


        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean registerToBe(Car u, int x) {
        return false;
    }

    @Override
    public int checkAccountType(String s) {
        return 0;
    }

    @Override
    public Car getByString(String s) {
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from cars where carid = (?)");
            ps.setString (1,s);
            ResultSet rs = ps.executeQuery ();
            while(rs.next ()){
                String carid = rs.getString("carid");
                String make = rs.getString ("make");
                String model = rs.getString ("model");
                int year = rs.getInt ("year");
                float price = rs.getFloat ("price");
                float remainingpayment = rs.getFloat ("remainingpayment");
                int paymenttype = rs.getInt ("paymenttype");
                String ownership = rs.getString ("ownership");

                if(carid.equalsIgnoreCase (s)){
                    return new Car (carid,make,model,year,price,remainingpayment,paymenttype,ownership);
                }
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return null;
    }

    @Override
    public Car getByInt(int id) {
        return null;
    }

    @Override
    public MyList<Car> getAll() {
        MyList<Car> carList = new MyList<Car> ();
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from cars where ownership = (?)");
            ps.setString (1,"dealer");
            ResultSet rs = ps.executeQuery ();
            while(rs.next ()){
                String carid = rs.getString("carid");
                String make = rs.getString ("make");
                String model = rs.getString ("model");
                int year = rs.getInt ("year");
                float price = rs.getFloat ("price");
                float remainingpayment = rs.getFloat ("remainingpayment");
                int paymenttype = rs.getInt("paymenttype");
                String ownership = rs.getString ("ownership");

                carList.add (new Car (carid,make,model,year,price,remainingpayment,paymenttype,ownership));
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return carList;
    }

    @Override
    public boolean remove(String id) {
        int i = 0;
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "delete from cars where carid = (?)");
            ps.setString(1,id);
            i = ps.executeUpdate();
            ps.close ();
            System.out.println("The number of deleted rows were "+ i);
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i>0;
    }

    public boolean hasAOwner(String id){
        PreparedStatement ps = null;
        try {
            ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from cars where carid = (?)");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                String ownership = rs.getString ("ownership");
                if (ownership.equalsIgnoreCase ("dealer")){
                    return false;
                }
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return true;
    }

    public String getAllSoldCars(){
        float f = 0;
        StringBuilder res = new StringBuilder ();
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from cars where ownership <> (?)");
            ps.setString(1,"dealer");
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                float price = rs.getFloat ("price");
                int paymenttype = rs.getInt ("paymenttype");
                String carid = rs.getString ("carid");
                PreparedStatement ps2 = ConnectionUtil.getInstance ().getConnection ().prepareStatement(
                        "select divide_payment(?,?)");
                ps2.setFloat (1,price);
                ps2.setInt (2,paymenttype);
                ResultSet rs2 = ps2.executeQuery ();
                while (rs2.next ()){
                    f = rs2.getFloat ("divide_payment");
                    res.append ("Owner of "+carid + " has to pay " +f + " for " + paymenttype + " more months" +"\n");
                }
                ps2.close ();
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return res.toString ();
    }

    @Override
    public MyList<Car> getOwn(String s) {
        MyList<Car> carList = new MyList<Car> ();
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from cars where ownership = (?)");
            ps.setString(1,s);
            ResultSet rs = ps.executeQuery ();
            while(rs.next ()){
                String carid = rs.getString("carid");
                String make = rs.getString ("make");
                String model = rs.getString ("model");
                int year = rs.getInt ("year");
                float price = rs.getFloat ("price");
                float remainingpayment = rs.getFloat ("remainingpayment");
                int paymenttype = rs.getInt ("paymenttype");
                String ownership = rs.getString ("ownership");

                carList.add (new Car (carid,make,model,year,price,remainingpayment,paymenttype,ownership));
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return carList;
    }

    @Override
    public String getPayment(String s) {
        float f = 0;
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from cars where carid = (?)");
            ps.setString (1, s);
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                float price = rs.getFloat ("price");
                int paymenttype = rs.getInt ("paymenttype");
                PreparedStatement ps2 = ConnectionUtil.getInstance ().getConnection ().prepareStatement(
                        "select divide_payment(?,?)");
                ps2.setFloat (1,price);
                ps2.setInt (2,paymenttype);
                ResultSet rs2 = ps2.executeQuery ();
                while (rs2.next ()){
                    f = rs2.getFloat ("divide_payment");
                    return f + " for " + paymenttype + " more months";
                }
                ps2.close ();
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return null;
    }

    public int chageCarFields(int id){
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from offer where offerid = (?)");
            ps.setInt (1,id);
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                String carid = rs.getString ("carid");
                int paymenttype = rs.getInt ("paymenttype");
                float price = rs.getFloat ("price");
                String username = rs.getString ("username");
                PreparedStatement ps2 = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                        "update cars set ownership = (?)," +
                                "paymenttype = (?)," +
                                "price = (?), " +
                                "remainingpayment = (?) " +
                                "where carid = (?)");
                ps2.setString (1,username);
                ps2.setInt (2,paymenttype);
                ps2.setFloat (3,price);
                ps2.setFloat (4,price);
                ps2.setString (5,carid);
                int i = ps2.executeUpdate();
                ps2.close ();
                System.out.println("The number of updated fields were "+ i);
                return i;
            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return -1;
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
}
