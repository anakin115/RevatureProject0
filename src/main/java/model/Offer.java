package model;

import java.util.Objects;

public class Offer {

    private int offerid;
    private String username;
    private String carid;
    private String status;
    private float price;
    private int paymenttype;

    public Offer(String username, String carid, float price, int paymenttype) {
        this.username = username;
        this.carid = carid;
        this.price = price;
        this.paymenttype = paymenttype;
    }

    public Offer(int offerid, String username, String carid, String status, float price, int paymenttype) {
        this.offerid = offerid;
        this.username = username;
        this.carid = carid;
        this.status = status;
        this.price = price;
        this.paymenttype = paymenttype;
    }

    public int getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(int paymenttype) {
        this.paymenttype = paymenttype;
    }

    public int getOfferid() {
        return offerid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerid=" + offerid +
                ", username='" + username + '\'' +
                ", carid='" + carid + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", paymenttype=" + paymenttype +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Offer offer = (Offer) o;
        return Float.compare (offer.price, price) == 0 && Objects.equals (offerid, offer.offerid) && Objects.equals (username, offer.username) && Objects.equals (carid, offer.carid) && Objects.equals (status, offer.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash (offerid, username, carid, status, price);
    }
}
