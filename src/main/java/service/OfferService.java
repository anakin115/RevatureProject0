package service;

import jdbc.GenericDao;
import model.Offer;
import model.User;
import util.MyList;

public class OfferService {

    GenericDao<Offer,String> jdbc;

    public OfferService(GenericDao<Offer, String> jdbc) {
        this.jdbc = jdbc;
    }

    public boolean makeOffer(String username, String carid,float price,int paymenttype){
        int i = jdbc.save (new Offer (username,carid, price,paymenttype));
        return (i>0);
    }

    public void viewAllOffer(){
        System.out.println (jdbc.getAll ().toString ());
    }

    public void findOffer(int s){
        System.out.println (jdbc.getByInt (s).toString ());
    }

    public boolean acceptOffer(int s){
        int a = jdbc.accept(s);
        int r = 0;
        if (a > 0) {
            r = jdbc.rejectAllOtherOffer (s);
        }
        return (a+r>0);
    }

    public boolean rejectOffer(int s){
        int a = jdbc.reject(s);
        return (a>0);
    }
}
