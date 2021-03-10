package model;

import java.util.Objects;

public class Car {

    private String carid;
    private String make;
    private String model;
    private int year;
    private float price;
    private float remainingPayment;
    private int paymentType;
    private String ownership;

    public Car(String carid, String make, String model, int year, float price, float remainingPayment, int paymentType, String ownership) {
        this.carid = carid;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.remainingPayment = remainingPayment;
        this.paymentType = paymentType;
        this.ownership = ownership;
    }

    public Car(String carid, String make, String model, int year, float price) {
        this.carid = carid;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.remainingPayment = price;
        this.ownership = "dealer";
    }

    @Override
    public String toString() {
        return "Car{" +
                "carid='" + carid + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", remainingPayment=" + remainingPayment +
                ", paymentType='" + paymentType + '\'' +
                ", ownership='" + ownership + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Car car = (Car) o;
        return year == car.year && Float.compare (car.price, price) == 0 && Float.compare (car.remainingPayment, remainingPayment) == 0 && Objects.equals (carid, car.carid) && Objects.equals (make, car.make) && Objects.equals (model, car.model) && Objects.equals (paymentType, car.paymentType) && Objects.equals (ownership, car.ownership);
    }

    @Override
    public int hashCode() {
        return Objects.hash (carid, make, model, year, price, remainingPayment, paymentType, ownership);
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRemainingPayment() {
        return remainingPayment;
    }

    public void setRemainingPayment(float remainingPayment) {
        this.remainingPayment = remainingPayment;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
