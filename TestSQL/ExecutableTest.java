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
                System.out.println("-------------------------------------------- \n" + "\n");
                res=test.venteUtilisateur(707);
                System.out.println(res);
    
            } catch (SQLException ex){
                System.out.println("Erreur SQL : " + ex.getMessage());
            }
            finally{
                try{
                    laConnexion.close();
                }
                catch(SQLException ex){
                    System.out.println("Erreur SQL : " + ex.getMessage());
                }

            }

        
        }
        catch(ClassNotFoundException ex){
            
        }

        
        
        
        
        
    }
}