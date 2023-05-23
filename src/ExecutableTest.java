import java.sql.*;

public class ExecutableTest{
    public static void main (String[] args){
        try{
            ConnexionMySQL laConnexion= new ConnexionMySQL();
            try{
                laConnexion.connecter("servinfo-mariadb", "DBbarache", "barache", "barache");
                TestJDBC test= new TestJDBC(laConnexion);
                String res= test.venteAVenir();
                System.out.println("Exemple des ventes à venir \n  ");
                System.out.println(res);

                res=test.
    
            } catch (SQLException ex){
            }

        
        }
        catch(ClassNotFoundException ex){
            
        }

        
        
        
        
        
    }
}