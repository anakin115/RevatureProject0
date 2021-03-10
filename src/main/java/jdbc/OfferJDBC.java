package jdbc;

import config.ConnectionUtil;
import model.Car;
import model.Offer;
import util.MyList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferJDBC  implements GenericDao<Offer,String>{

    private static OfferJDBC instance;

    static OfferJDBC getInstance(){
        if(instance == null){
            instance = new OfferJDBC ();
        }
        return instance;
    }

    @Override
    public int save(Offer offer) {
        PreparedStatement ps = null;
        try {
            ps = ConnectionUtil.getInstance().getConnection().prepareStatement(
                    "insert into offer (username,carid,price,status,paymenttype)" +
                            "values (?,?,?,?,?)");
            ps.setString(1,offer.getUsername ());
            ps.setString(2,offer.getCarid ());
            ps.setFloat (3,offer.getPrice ());
            ps.setString (4,"Pending");
            ps.setInt (5,offer.getPaymenttype ());

            int i = ps.executeUpdate();
            ps.close ();
            System.out.println("The number of added offer were "+ i);
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
            return -1;
        }
    }

    @Override
    public boolean registerToBe(Offer u, int x) {
        return false;
    }

    @Override
    public int checkAccountType(String s) {
        return 0;
    }

    @Override
    public Offer getByString(String s) {
        return null;
    }

    @Override
    public Offer getByInt(int s) {
        try {
            PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "select * from offer where offerid = (?)");
            ps.setInt (1, s);
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
                String username = rs.getString ("username");
                String carid = rs.getString ("carid");
                float price = rs.getFloat ("price");
                String status = rs.getString ("status");
                int paymenttype = rs.getInt ("paymenttype");

                return new Offer (s, username, carid,status,price,paymenttype);

            }
            ps.close ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return null;
    }

        @Override
    public MyList<Offer> getAll() {
            MyList<Offer> OfferList = new MyList<Offer> ();
            try {
                PreparedStatement ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                        "select * from offer");
                ResultSet rs = ps.executeQuery ();
                while(rs.next ()){
                    int offerid = rs.getInt ("offerid");
                    String username = rs.getString ("username");
                    String carid = rs.getString ("carid");
                    float price = rs.getFloat ("price");
                    String status = rs.getString ("status");
                    int paymenttype = rs.getInt ("paymenttype");

                    OfferList.add (new Offer (offerid, username, carid,status,price,paymenttype));
                }
                ps.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
            return OfferList;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public MyList<Offer> getOwn(String s) {
        return null;
    }

    @Override
    public String getPayment(String s) {
        return null;
    }

    public int rejectAllOtherOffer(int id) {
        int i = 0;
        PreparedStatement ps = null;
            try {
                ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                        "select * from offer where offerid = (?)");
                ps.setInt (1,id);
                ResultSet rs = ps.executeQuery ();
                while(rs.next ()) {
                    String carid = rs.getString ("carid");
                    PreparedStatement ps2 = ConnectionUtil.getInstance ().getConnection ().prepareStatement(
                            "update offer set status = 'Rejected' where carid = (?) and status <> 'Accepted' ");
                    ps2.setString (1,carid);
                    i = ps2.executeUpdate();
                    ps2.close ();
                    System.out.println("The number of rejected offer were "+ i);
                    return i;
                }
                ps.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
        }
            return -1;
    }

    public int accept(int id){
        PreparedStatement ps = null;
        try {
            ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "update offer set status = 'Accepted' where offerid = (?)");
            ps.setInt (1, id);
            int i = ps.executeUpdate();
            ps.close ();
            System.out.println("The number of accepted offer were "+ i);
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return -1;
    }

    public int reject(int id){
        PreparedStatement ps = null;
        try {
            ps = ConnectionUtil.getInstance ().getConnection ().prepareStatement (
                    "update offer set status = 'Rejected' where offerid = (?)");
            ps.setInt (1, id);
            int i = ps.executeUpdate();
            ps.close ();
            System.out.println("The number of rejected offer were "+ i);
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return -1;
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
