import java.sql.ResultSet;
import java.sql.*;

public class ExempleConnec{
    public static void main (String[] args){
        try{
            ConnexionMySQL laConnexion= new ConnexionMySQL();
            try{
                String res="";
                laConnexion.connecter("servinfo-mariadb", "DBbarache", "barache", "barache");
                Statement s=laConnexion.createStatement();
                ResultSet rs= s.executeQuery("select montant , idVe, nomob  from PrixVente natural join VENTE natural join OBJET where idacheteur=707");
                while (rs.next()){
                    int montant = rs.getInt("montant");
                    String idVe= rs.getString("idVe");
                    String nomob = rs.getString("nomob");
                    res+= montant + " "+ idVe + " "  + nomob + "\n";
                }
                System.out.println(res);

            }
            catch(SQLException e){

            }
            
            System.out.println(laConnexion.isConnecte());
        
        }
        catch(ClassNotFoundException ex){
            
        }
        
        
        
        
    }
}