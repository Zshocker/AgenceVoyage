package Agence;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Client implements Serializable
{
    public static final String facturePlace= Paths.get("").toAbsolutePath().toString()+"/";
    private String nom;
    private String prenom;
    private int numPass;
    private final List<Reservation> reservations=new LinkedList<>();
    public Client(){}
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
    public String Retfacture()
    {
        StringBuilder stringBuffer=new StringBuilder();
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
    public String facture(){
        String uniqueName=facturePlace+"Facture_"+nom+"_"+numPass+"_"+UUID.randomUUID().toString()+".txt";
        File file=new File(uniqueName);
        String fact=Retfacture();
        FileWriter fileWriter=null;
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter=new FileWriter(file);
            fileWriter.write(fact);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fact;
    }

    @Override
    public String toString() {
        return "" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numPass=" + numPass
                ;
    }
}
