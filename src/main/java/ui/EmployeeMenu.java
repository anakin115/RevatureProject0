package ui;

import jdbc.JDBCFactory;
import model.Car;
import model.Offer;
import model.User;
import service.EmployeeService;
import service.OfferService;
import service.UserService;

import java.util.Scanner;

public class EmployeeMenu{

    public void showMenu(Scanner scan, User u) {
        System.out.println ("Welcome to Employee Menu");
        boolean keepLooping = true;
        EmployeeService es = new EmployeeService (JDBCFactory.daoFactory(Car.class));
        UserService us = new UserService (JDBCFactory.daoFactory(User.class));
        OfferService os = new OfferService (JDBCFactory.daoFactory(Offer.class));
        int misclick = 0;
        do {
            System.out.println ("Add a car to the lot, type (a)");
            System.out.println ("Accept or reject an offer for a car, type (b)");
            System.out.println ("Remove a car from the lot, type (c)");
            System.out.println ("View all payment, type (d)");
            System.out.println ("To exit, type (e)");
            String answer = scan.nextLine ();
            if (answer.equalsIgnoreCase ("a")) {
                System.out.println ("Enter the carid for the car");
                String id = scan.nextLine();
                if (!es.findCarByCarID (id)) {
                    System.out.println ("Enter the make for the car");
                    String make = scan.nextLine ();
                    System.out.println ("Enter the model for the car");
                    String model = scan.nextLine ();
                    System.out.println ("Enter the year for the car");
                    int year = scan.nextInt ();
                    System.out.println ("Enter the price for the car");
                    float price = scan.nextFloat ();
                    System.out.println (es.addCar (id, make, model, year, price)  ? "Successfully added " + id : "Failed to add this car");
                    scan.nextLine ();
                } else {
                    System.out.println ("Seems like we already have the car here");
                }
            } else if (answer.equalsIgnoreCase ("b")) {
                //first list all the offers in the system
                os.viewAllOffer ();
                System.out.println ("Enter the offerid of the offer you want to accept/reject");
                int offerid = scan.nextInt ();
                //pull up the offer the employee wants to take a look at
                os.findOffer (offerid);
                scan.nextLine ();
                System.out.println ("Do you want to accept or reject this offer");
                String result = scan.nextLine ();
                if (result.equalsIgnoreCase ("accept")){
                    //accept the offer here and change ownership of the car, also change a few field include
                    // rejecting all the other offer made on the same car(in acceptOffer)
                    if(os.acceptOffer (offerid)){
                        es.changeOwnership(offerid);
                        System.out.println ("Done!");
                    } else {
                        System.out.println ("Failed to accept the offer");
                    }
                } else if (result.equalsIgnoreCase ("reject")){
                    if(os.rejectOffer (offerid)){
                        System.out.println ("Done!");
                    } else {
                        System.out.println ("Failed to reject the offer");
                    }
                }

            } else if (answer.equalsIgnoreCase ("c")) {
                System.out.println ("Enter the carid for the car you want to remove");
                String id = scan.nextLine();
                if (es.findCarByCarID (id)) {
                    if(es.deleteCar(id)){
                        System.out.println ("Deleted the car with carid " + id);
                    } else {
                        System.out.println ("Failed to delete the car with carid "+ id);
                    }
                }
            } else if (answer.equalsIgnoreCase ("d")) {
                String s = "";
                //get all the cars that were sold, but if s == "" that mean we haven't sold any
                s = es.getAllSoldCars ();
                if (s.equals ("")){
                    System.out.println ("This dealership isn't maing any money at the moment :(");
                }else {
                    System.out.println (s);
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
