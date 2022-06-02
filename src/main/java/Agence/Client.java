package Agence;

import java.util.*;

public class Client
{
    private String nom;
    private String prenom;
    private int numPass;
    private final List<Reservation> reservations=new LinkedList<>();
    public Client(String nom, String prenom, int numPass) {
        this.nom = nom;
        this.prenom = prenom;
        this.numPass = numPass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumPass() {
        return numPass;
    }

    public void setNumPass(int numPass) {
        this.numPass = numPass;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    public String facture()
    {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("nom : ").append(this.nom).append("\n");
        stringBuffer.append("prenom : ").append(this.prenom).append("\n");
        stringBuffer.append("num de passport : ").append(this.numPass).append("\n");
        double totale =0;
        for (Reservation dest : reservations) {
            totale+=dest.getDestination().getPrix();
        }
        String type=null;
        if(totale>10000){
            type="GOLD";
        }else if(totale>5000){
            type="SILVER";
        }
        if(type!=null) stringBuffer.append("type : ").append(type).append("\n");
        return stringBuffer.toString();
    }
}
