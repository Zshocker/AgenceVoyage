package Agence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgenceVoyage implements Serializable
{
    private static AgenceVoyage agence=null;
    private static String FichierDeAgence="Agence";
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
    private AgenceVoyage()
    {
        users.add(new User("Yassine","boss",Role.Directeur));
        users.add(new User("Zakaria","zack",Role.AgentCommercial));
        users.add(new User("Sohaib","sohaib",Role.AgentCommercial));
        users.add(new User("Hicham","hich",Role.AgentCommercial));
    }
    public User getUser(String login, String password){
        for (User user: users) {
            if(user.auth(login,password))return user;
        }
        return null;
    }
}
