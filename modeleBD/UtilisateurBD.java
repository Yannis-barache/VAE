package modeleBD;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UtilisateurBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public UtilisateurBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }
    public int numUtilisateurMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idUt),0) leMax from UTILISATEUR");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;

    }

    public void insererUtilisateur(Utilisateur u)throws SQLException{
        int role;
        int num = this.numUtilisateurMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into UTILISATEUR(idUt,pseudoUt,emailUT,mdpUt,activeUt,idRole) values (?,?,?,?,?,?)");
        if(u.isAdmin()){
            role=1;
        }
        else{
            role=2;
        }
        ps.setInt(1, num);
        ps.setString(2, u.getPseudoUt());
        ps.setString(3, u.getEmailUt());
        ps.setString(4, u.getmdpUt());
        ps.setString(5, "O");
        ps.setInt(6, role);
        ps.executeUpdate();
    }

    public void supprimerUtilisateur(int idUt)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from UTILISATEUR where "+idUt+"=idUt");
        rs.next();
        rs.close();
    }

    public void modifierUtilisateur(Utilsateur u){
        int role;
        int num = this.numUtilisateurMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE UTILISATEUR SET idUt = ?,pseudoUt = ?,emailUT = ?,mdpUt = ?,activeUt = ?,idRole = ?");
        if(u.isAdmin()){
            role=1;
        }
        else{
            role=2;
        }
        ps.setInt(1, num);
        ps.setString(2, u.getPseudoUt());
        ps.setString(3, u.getEmailUt());
        ps.setString(4, u.getmdpUt());
        ps.setString(5, "O");
        ps.setInt(6, role);
        ps.executeUpdate();
    }

    public Utilisateur rechercherUtilisateurParNum(int idUt){
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from UTILISATEUR where "+idUt+"=idUt");
        rs.next();
        boolean role=false;
        if(rs.getInt(6)==1){
            role=true;
        }
        u=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),role);
        rs.close();
    }

    public List<Utilisateur> listeUtilisateurs(){
        st= this.connexMySQL.createStatement();
        List<Utilisateur> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from UTILISATEUR");
        while(rs.next()){
            boolean role=false;
            if(rs.getInt(6)==1){
                role=true;
            }
            u=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),role);
            liste.add(u);
    }
        rs.close();
    }
}
