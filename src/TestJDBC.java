import java.sql.*;

public class TestJDBC {
    ConnexionMySQL laConnexion;
    public TestJDBC(ConnexionMySQL uneConnexion){
        this.laConnexion=uneConnexion;
    }

    public String venteAVenir() throws SQLException{
        String resultat="";
        try{
            Statement s=laConnexion.createStatement();
            ResultSet rs=s.executeQuery("select nomob, prixbase, prixmin,debutve,finve,pseudout from UTILISATEUR natural join OBJET natural join VENTE where idst=1");
            while (rs.next()){
                String nomob=rs.getString("nomob");
                Double prixbase= rs.getDouble("prixbase");
                Double prixmin= rs.getDouble("prixmin");
                String debutVente= rs.getString("debutve");
                String finVente=rs.getString("finve");
                String pseudoUtilisateur=rs.getString("pseudout");
                resultat+= nomob + " " + prixbase + " " + " " + prixmin + " " +debutVente + " " + finVente + " " + pseudoUtilisateur + "\n";
            }
            return resultat;
        }
        catch (SQLException ex){
            throw new SQLException("Aucune vente à venir");
        }
    }

    
}
