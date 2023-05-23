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
                ResultSet rs= s.executeQuery("Select nome from EMPLOYE");
                rs.next();
                res += rs.getString("nome");
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