package Agence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainApp
{
    private static List<Integer> turn(String ligne){
        List<Integer> integers=new ArrayList<>();
        StringTokenizer tokenizer=new StringTokenizer(ligne," ");
        while (tokenizer.hasMoreElements()){
            try {
                integers.add(Integer.parseInt(tokenizer.nextToken()));
            } catch (NumberFormatException ignored) {}
        }
        return integers;
    }
    private static boolean AppComercial(User user,Scanner snScanner){
        System.out.println("Bienvenue "+user.getLogin());
        int choix;
        do {
            choix = 5000;
            System.out.println("1 - Ajouter une Reservation");
            System.out.println("2 - logout ");
            System.out.println("3 - Exit ");
            System.out.println("donner votre choix :");
            try {
                choix = Integer.parseInt(snScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
            switch (choix){
                case 1:
                    Client client=new Client();
                    System.out.println("Donner le nom");
                    client.setNom(snScanner.nextLine());
                    System.out.println("Donner le prenom");
                    client.setPrenom(snScanner.nextLine());
                    System.out.println("Donner le numero de passport");
                    client.setNumPass(Integer.parseInt(snScanner.nextLine()));
                    AgenceVoyage.getInstance().PrintDestinations();
                    System.out.println("Donner les numero des destination souhitees separee par un espace sur une ligne");
                    List<Integer> ids=turn(snScanner.nextLine());
                    for (int id: ids) {
                       Destination destination=  AgenceVoyage.getInstance().getDestinations().get(id);
                       new Reservation(client,destination);
                    }
                    System.out.println(client.facture());
                    System.out.println("vous pouvez trouver le format fichier dans "+Client.facturePlace);
                    try {
                        AgenceVoyage.getInstance().Save();
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                    break;
                case 2:
                    return false;
                case 3:
                    return true;
                default:
                    break;
            }
        }while (true);
    }
    private static boolean AppDirecteur(User user,Scanner snScanner){
        System.out.println("Bienvenue "+user.getLogin());
        int choix;
        do {
            choix = 5000;
            System.out.println("1 - afficher les reservations");
            System.out.println("2 - logout ");
            System.out.println("3 - Exit ");
            System.out.println("donner votre choix :");
            try {
                choix = Integer.parseInt(snScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
            switch (choix){
                case 1:
                    AgenceVoyage.getInstance().PrintDestinations();
                    System.out.println("Donner le numero du destination");
                    int id = Integer.parseInt(snScanner.nextLine());
                    Destination destination= AgenceVoyage.getInstance().getDestinations().get(id);
                    for (Reservation res: destination.getReservations()) {
                        System.out.println(res.getClient());
                    }
                    break;
                case 2:
                    return false;
                case 3:
                    return true;
                default:
                    break;
            }
        }while (true);
    }


    public static void main(String[] args) {

        Scanner snScanner = new Scanner(System.in);
        AgenceVoyage agenceVoyage = AgenceVoyage.getInstance();
        boolean exit=false;
        do{
            User user=null;
            do {
                System.out.println("Login :");
                String login=snScanner.nextLine();
                System.out.println("password :");
                String pass=snScanner.nextLine();
                user=agenceVoyage.getUser(login,pass);
                if(user==null){
                    System.out.println("Wrong!");
                }
                else
                {
                    break;
                }

            }while (true);
            if(user.getRole().equals(Role.AgentCommercial))
            {
               exit=AppComercial(user,snScanner);
            }
            else if(user.getRole().equals(Role.Directeur))
            {
                exit=AppDirecteur(user,snScanner);
            }
        }while (!exit);
    }
}
