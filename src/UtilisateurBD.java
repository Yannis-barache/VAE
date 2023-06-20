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
        // execute la requete sql
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
        ps.setString(2, u.getPseudo());
        ps.setString(3, u.getMail());
        ps.setString(4, u.getMdp());
        ps.setString(5, "O");
        ps.setInt(6, role);
        ps.executeUpdate();
        u.setIdentifiant(num);
    }

    public void supprimerUtilisateur(Utilisateur u)throws SQLException{
        int idUt= u.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from UTILISATEUR where "+idUt+"=idUt");
        rs.next();
        rs.close();
    }

    public void modifierUtilisateur(Utilisateur u)throws SQLException{
        int role;
        String actif="";
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE UTILISATEUR SET pseudoUt = ?,emailUT = ?,mdpUt = ?,activeUt = ?,idRole = ? where idut="+u.getIdentifiant());
        if(u.isAdmin()){
            role=1;
        }
        else{
            role=2;
        }
        if(u.isActif()){
            actif="O";
        }
        else{
            actif="N";
        }
        ps.setString(1, u.getPseudo());
        ps.setString(2, u.getMail());
        ps.setString(3, u.getMdp());
        ps.setString(4, actif);
        ps.setInt(5, role);
        ps.executeUpdate();
    }

    public Utilisateur rechercherUtilisateurParNum(int idUt)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from UTILISATEUR where "+idUt+"=idUt");
        rs.next();
        boolean admin=false;
        boolean actif = true;
        if(rs.getInt(6)==1){
            admin=true;
        }
        if(rs.getString(5).equals("N")){
            actif=false;
        }
        Utilisateur u=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),actif,admin);
        rs.close();
        return u;
    }

    public List<Utilisateur> listeUtilisateurs()throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Utilisateur> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from UTILISATEUR");
        while(rs.next()){
            boolean admin=false;
            boolean actif = true;
            if(rs.getInt(6)==1){
                admin=true;
            }
            if(rs.getString(5).equals("N")){
                actif=false;
            }
            Utilisateur u=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),actif,admin);
            liste.add(u);
    }
        rs.close();
        return liste;
    }
}
