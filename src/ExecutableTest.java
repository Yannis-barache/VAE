import java.sql.*;
import java.util.List;

public class ExecutableTest{
    public static void main (String[] args){
        try{
            ConnexionMySQL laConnexion= new ConnexionMySQL();
            try{
                laConnexion.connecter("servinfo-mariadb", "DBsimon", "simon", "Rugby28*@");
                Statut s = new Statut(7, "testS");
                System.out.println(s);
                Categorie c1 = new Categorie(5, "testCat");
                System.out.println(c1);
                Utilisateur u1 = new Utilisateur(1, "martin", "martin.martin@gmail.com", "martinou", true, false);
                System.out.println(u1);
                Objet o = new Objet(1, "test", "test", c1, u1);
                System.out.println(o);
                Vente v = new Vente(1, 1, 2, "12/12/2023:10:10:10", "16/12/2023:10:10:10", s, o);
                System.out.println(v);
                Enchere e = new Enchere(v, u1, 123, "12/12/2023:10:10:10");
                System.out.println(e);
                
                laConnexion.close();
            } catch (SQLException ex){
                System.out.println("Erreur SQL : " + ex.getMessage());
            }


        }
        catch(ClassNotFoundException ex){

        }






    }
}
// test de l'orm utilisateur
                // Utilisateur u1 = new Utilisateur(1, "martin", "martin.martin@gmail.com", "martinou", true, false);
                // Utilisateur u2 = new Utilisateur(2, "test1", "martin.test@gmail.com", "testeur", true, false);
                // UtilisateurBD uBD = new UtilisateurBD(laConnexion);
                // uBD.insererUtilisateur(u2);
                // u.setPseudo("route");
                // uBD.modifierUtilisateur(u);
                // uBD.supprimerUtilisateur(u);
                // Utilisateur u2 = uBD.rechercherUtilisateurParNum(1);
                // System.out.println(u2);
                // List<Utilisateur> liste = uBD.listeUtilisateurs();
                // System.out.println(liste);

// test de l'orm categorie
                // Categorie c1 = new Categorie(5, "testCat");
                // CategorieBD cBD = new CategorieBD(laConnexion);
                // List<Categorie> liste = cBD.listeCategories();
                // System.out.println(liste);



// test de l'orm statut 
                // Statut s = new Statut(7, "testS");
                // System.out.println(sBD.rechercherStatutParNum());
                // StatutBD sBD = new StatutBD(laConnexion);
                // s.setNom("testSmodif");
                // sBD.modifierStatut(s);
                // while(sBD.numStatutMax()>6){
                //     Statut statut = sBD.rechercherStatutParNum(sBD.numStatutMax());
                //     sBD.supprimerStatut(statut);
                // }

// test de l'orm  