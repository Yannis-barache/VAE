import java.sql.*;
public class ExecutableTest{
    public static void main (String[] args){
        try{
            ConnexionMySQL laConnexion= new ConnexionMySQL();
            try{

                laConnexion.connecter("servinfo-mariadb", "DBbarache", "barache", "barache");
                CategorieBD cBd = new CategorieBD(laConnexion);
                EnchereBD eBd = new EnchereBD(laConnexion);
                ObjetBD oBd = new ObjetBD(laConnexion);
                PhotoBD pBd = new PhotoBD(laConnexion);
                StatutBD sBd = new StatutBD(laConnexion);
                UtilisateurBD uBd = new UtilisateurBD(laConnexion);
                VenteBD vBd = new VenteBD(laConnexion);

                System.out.println(vBd.listeVentesEnCours());
                

                // Utilisateur u = uBd.rechercherUtilisateurParNum(10);
                // uBd.ventesUtilisateur(u);
                // uBd.encheresUtilisateur(u);
                // System.out.println(u);

                // System.out.println("------------------------");

                // Utilisateur u2 = uBd.rechercherUtilisateurParNum(45);
                // uBd.encheresUtilisateur(u2);
                // uBd.encheresUtilisateur(u);
                // System.out.println(u2);

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