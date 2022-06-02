package Agence;

import java.util.Scanner;

public class MainApp
{
    private static boolean AppComercial(User user,Scanner snScanner){
        System.out.println("Bienvenue "+user.getLogin());
        return true;
    }
    private static boolean AppDirecteur(User user,Scanner snScanner){
        System.out.println("Bienvenue "+user.getLogin());
        return true;
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
