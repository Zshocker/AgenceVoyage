package Agence;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AgenceVoyage implements Serializable
{
    private static AgenceVoyage agence=null;
    private static String FichierDeAgence= Paths.get("").toAbsolutePath().toString()+"/Agence";
    public static AgenceVoyage getInstance(){
        if(agence==null)agence=readAgence(FichierDeAgence);
        return agence;
    }
    private static AgenceVoyage readAgence(String fichier) {
        ObjectInputStream ne = null;
        FileInputStream fileInputStream = null;
        AgenceVoyage B = null;
        try {
            fileInputStream = new FileInputStream(fichier);
            ne = new ObjectInputStream(fileInputStream);
            B = (AgenceVoyage) ne.readObject();
        } catch (Exception ignored) {
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                    if(ne != null) ne.close();
                }
            } catch (IOException ignored) {}
        }
        if (B == null) {
            System.out.println("aucune instance est trouver dans le fichier");
            B = new AgenceVoyage();
        }
        return B;
    }


    private List<User> users=new ArrayList<>();
    private List<Destination> destinations=new ArrayList<>();
    private AgenceVoyage()
    {
        //users
        users.add(new User("Yassine","boss",Role.Directeur));
        users.add(new User("Zakaria","zack",Role.AgentCommercial));
        users.add(new User("Sohaib","sohaib",Role.AgentCommercial));
        users.add(new User("Hicham","hich",Role.AgentCommercial));

        //destinations
        destinations.add(new Destination("France",3000));
        destinations.add(new Destination("Bresil",4000));
        destinations.add(new Destination("Espagne",2000));
        destinations.add(new Destination("Etas-Unis",4000));
        destinations.add(new Destination("Japan",6000));
        destinations.add(new Destination("Turquie",3000));
    }

    public User getUser(String login, String password){
        for (User user: users) {
            if(user.auth(login,password))return user;
        }
        return null;
    }
    public void Save() {
        ObjectOutputStream OS = null;
        try {
            OS = new ObjectOutputStream(new FileOutputStream(FichierDeAgence));
            OS.writeObject(this);
            OS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (OS != null) {
                    OS.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }
    public void PrintDestinations()
    {
        int i=0;
        for (Destination dest: destinations)
        {
            System.out.println("("+i+") => "+dest);
            i++;
        }
    }

}
