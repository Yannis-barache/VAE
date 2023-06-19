import java.sql.*;
import java.util.List;

public class ExecutableTest{
    public static void main (String[] args){
        try{
            ConnexionMySQL laConnexion= new ConnexionMySQL();
            try{
                laConnexion.connecter("servinfo-mariadb", "DBsimon", "simon", "Rugby28*@");
                Utilisateur u1 = new Utilisateur(1, "martin", "martin.martin@gmail.com", "martinou", true, false);
                Utilisateur u2 = new Utilisateur(2, "connard", "martin.pute@gmail.com", "salope", true, false);
                UtilisateurBD uBD = new UtilisateurBD(laConnexion);
                uBD.insererUtilisateur(u2);
                // u.setPseudo("gros caca");
                // uBD.modifierUtilisateur(u);
                // uBD.supprimerUtilisateur(u);
                // Utilisateur u2 = uBD.rechercherUtilisateurParNum(1);
                // System.out.println(u2);
                List<Utilisateur> liste = uBD.listeUtilisateurs();
                System.out.println(liste);
                laConnexion.close();
            } catch (SQLException ex){
                System.out.println("Erreur SQL : " + ex.getMessage());
            }


        }
        catch(ClassNotFoundException ex){

        }






    }
}