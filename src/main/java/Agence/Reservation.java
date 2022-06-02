package Agence;

import java.util.Date;

public class Reservation
{
    private Client client;
    private Date date=new Date();
    private Destination destination;
    public Reservation(Client client,Destination destination) {
        this.client = client;
        if(client!=null) {
            client.getReservations().add(this);
        }
        this.destination=destination;
        if(destination!=null){
            destination.getReservations().add(this);
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (this.client!=null) this.client.getReservations().remove(this);
        this.client=client;
        if(client!=null){
            client.getReservations().add(this);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination)
    {
        if (this.destination!=null) this.destination.getReservations().remove(this);
        this.destination=destination;
        if(destination!=null){
            destination.getReservations().add(this);
        }
    }


}
