package Agence;

import java.util.LinkedList;
import java.util.List;

public class Destination
{
    private String destination;
    private double prix;
    private final List<Reservation> reservations=new LinkedList<>();
    public Destination(String destination, double prix) {
        this.destination = destination;
        this.prix = prix;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "{" +
                "destination : " + destination + ' ' +
                ", prix : " + prix + " $ " +
                '}';
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
