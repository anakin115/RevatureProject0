package service;

import jdbc.GenericDao;
import model.Car;
import model.User;
import util.MyList;

public class EmployeeService {

    GenericDao<Car,String> jdbc;


    public EmployeeService(GenericDao<Car, String> jdbc) {
        this.jdbc = jdbc;
    }

    public void viewCars(){
        MyList<Car> cars = new MyList<Car> ();
        cars = jdbc.getAll ();
        System.out.println (cars.toString ());
    }

    public String getAllSoldCars(){
        return jdbc.getAllSoldCars ();
    }

    public boolean findCarByCarID(String carid){
        return jdbc.getByString (carid) != null;
    }

    public Car getCar(String carid){
        return jdbc.getByString (carid);
    }

    public MyList<Car> viewCarsOwn(String s){
        MyList<Car> cars = new MyList<Car> ();
        cars = jdbc.getOwn(s);
        System.out.println (cars.toString ());
        return cars;
    }

    public boolean addCar(String carid, String make, String model, int year, float price){
        return jdbc.save (new Car (carid,make,model,year,price))>0;
    }

    public String getRemainingPayment(String carid){
        String s = "";
        s = jdbc.getPayment(carid);
        return s;
    }

    public void changeOwnership(int id){
        jdbc.chageCarFields(id);
    }

    public boolean deleteCar(String id){
        return jdbc.remove(id);
    }

    public boolean hasAOwner(String carid){
        return jdbc.hasAOwner(carid);
    }

}
