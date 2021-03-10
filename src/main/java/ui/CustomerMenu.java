package ui;

import com.enterprise.annotations.TestClass;
import com.enterprise.annotations.TestMethod;
import jdbc.JDBCFactory;
import model.Car;
import model.Offer;
import model.User;
import service.EmployeeService;
import service.OfferService;
import service.UserService;
import util.MyList;

import java.util.Scanner;

@TestClass
public class CustomerMenu{

        EmployeeService es = new EmployeeService (JDBCFactory.daoFactory(Car.class));

        @TestMethod(name = "Testing if prius001 is here",expected = "Here")
        public String testIfPrius001IsHere(){
            if (es.findCarByCarID ("prius001")){
                return "Here";
            }
            return "Sold";
        }


    public void showMenu(Scanner scan, User u){
        EmployeeService es = new EmployeeService (JDBCFactory.daoFactory(Car.class));
        UserService us = new UserService (JDBCFactory.daoFactory(User.class));
        OfferService os = new OfferService (JDBCFactory.daoFactory(Offer.class));
        System.out.println ("Welcome to Customer Menu");
        boolean keepLooping = true;
        int misclick = 0;
        do {
            System.out.println ("To view the cars on the lot, type (a)");
            System.out.println ("To make an offer for a car, type (b)");
            System.out.println ("To view the cars that you own, type (c)");
            System.out.println ("To view the remaining payments for a car, type (d)");
            System.out.println ("To exit, type (e)");
            String answer = scan.nextLine ();
            if (answer.equalsIgnoreCase ("a")) {
                es.viewCars ();
            } else if (answer.equalsIgnoreCase ("b")) {
                System.out.println ("Enter the carid of the car you want to make an offer for");
                String carid = scan.nextLine ();
                //made it so that you can't get a car that already has a owner
                if (es.findCarByCarID (carid) && !es.hasAOwner (carid)) {
                    System.out.println ("How much is the offer");
                    try {
                        float price = scan.nextFloat ();
                        scan.nextLine ();
                        System.out.println ("How many months");
                        int paymenttype = scan.nextInt ();
                        scan.nextLine ();
                        boolean offerHasBeenMade = os.makeOffer (u.getUserName (), carid, price,paymenttype);
                        if (offerHasBeenMade){
                            System.out.println ("Your offer has been made and its waiting to be view");
                        }else {
                            System.out.println ("Offer didn't go through");
                        }
                    } catch (Exception e) {
                        System.out.println ("Exception thrown: Invalid input received " + e);
                        break;
                    }
                } else {
                    System.out.println ("Not a valid carid");
                }
            } else if (answer.equalsIgnoreCase ("c")) {
                es.viewCarsOwn(u.getUserName ());
            } else if (answer.equalsIgnoreCase ("d")) {
                System.out.println ("Enter the carid of the car you own and want to view the remaining payments for");
                String carid = scan.nextLine ();
                //get a list of cars that the customer own
                MyList<Car> list = es.viewCarsOwn(u.getUserName ());
                if (es.findCarByCarID (carid)) {
                    //if the list above contain the car this customer enter, then we can show the payment,
                    // else we don't show the info
                    if (list.contain (es.getCar (carid))) {
                        String s = es.getRemainingPayment (carid);
                        System.out.println ("Remaining payments = " + s);
                    } else {
                        System.out.println ("This isn't a car you own");
                    }
                } else {
                    System.out.println ("Not a valid carid");
                }

            } else if (answer.equalsIgnoreCase ("e")) {
                keepLooping = false;
                System.out.println ("Returning to main menu...");
            } else {
                misclick++;
                if (misclick > 2) {
                    keepLooping = false;
                }
            }
        }while (keepLooping);
    }


}
