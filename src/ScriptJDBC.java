import java.sql.*;

public class ScriptJDBC {

    ConnexionMySQL laConnexion;
    Utilisateur utilisateur;
    
    public ScriptJDBC(ConnexionMySQL uneConnexion){
        this.laConnexion=uneConnexion;
    }


    /**
     * Méthode qui permet de vérifier des identifiants de connexion d'un utilisateur
     * @param pseudo Le pseudo de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     * @return true si l'utilisateur existe, false sinon
     * @throws SQLException Si la requête SQL est incorrecte
     */
    public boolean connexion(String pseudo, String motDePasse) throws SQLException{
        try{
            boolean bool=false;
            Statement s=laConnexion.createStatement();
            ResultSet rs=s.executeQuery("SELECT * from UTILISATEUR where mdput='"+motDePasse+"' and pseudout='" +pseudo+"'");
            if (rs.next()){
                bool=true;
                boolean actif= false;
                boolean admin= false;
                if (rs.getString("activeut").equals("O")){
                    actif=true;
                }

                if (rs.getInt("idrole")== 1){
                    admin=true;
                }
                Utilisateur utilisateur = new Utilisateur(rs.getInt("idut"), rs.getString("pseudout"), rs.getString("emailut"),rs.getString("mdput"), actif,admin);
                this.utilisateur=utilisateur;
            }
            return bool;
        } catch (SQLException ex){
            throw new SQLException();
        }

    }

    public String venteAVenir() throws SQLException{
        String resultat="";
        try{
            // Création de la requête
            Statement s=laConnexion.createStatement();

            // La requête qui nous intéresse
            ResultSet rs=s.executeQuery("select nomob, prixbase, prixmin,debutve,finve,pseudout from UTILISATEUR natural join OBJET natural join VENTE where idst=1");
           
            //Récupération des données
            while (rs.next()){
                
                String nomob=rs.getString("nomob"); 
                Double prixbase= rs.getDouble("prixbase");
                Double prixmin= rs.getDouble("prixmin");
                String debutVente= rs.getString("debutve");
                String finVente=rs.getString("finve");
                String pseudoUtilisateur=rs.getString("pseudout");

                // Ajout des données dans la chaine de caractère
                resultat+= nomob + " " + prixbase + " " + " " + prixmin +
                " " +debutVente + " " + finVente + " " + pseudoUtilisateur + "\n" + "\n";
            }
            rs.close();
            return resultat;
        }
        catch (SQLException ex){
            // Si aucune vente n'est à venir donc si aucune vente n'a un idst=1
            throw new SQLException("Aucune vente à venir");
        }
    }

    public String venteUtilisateur(int idUtilisateur) throws SQLException{
        String resultat ="";
        try{
            // Création de la requête
            Statement s=laConnexion.createStatement();
            
            // La requête qui nous intéresse
            ResultSet rs= s.executeQuery("select * from PrixVentePasFini natural join OBJET natural join VENTE  where idut=" + idUtilisateur);
            
            //Récupération des données
            while (rs.next()){
                Double montant = rs.getDouble("montant");
                int idVe= rs.getInt("idve");
                String nomob=rs.getString("nomob");

                // Ajout des données dans la chaine de caractère
                resultat+= "La vente numéro "+idVe + " concerne "+ nomob + 
                " l'enchère la plus haute est à "+montant + " euros." +"\n";
                
            }
            rs.close();
            return resultat;
            
        } catch (SQLException ex){ 
            // Si l'utilisateur n'a pas de vente à son actif
            throw new SQLException("L'utilisateur n'a pas de vente à son actif");
        }
        
    }

    public Utilisateur getUtilisateur(){
        return this.utilisateur;
    }
    
}
