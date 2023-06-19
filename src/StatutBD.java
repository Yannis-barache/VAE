import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StatutBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public StatutBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }
    public int numStatutMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idSt),0) leMax from STATUT");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }

    public void insererStatut(Statut s)throws SQLException{
        int num = this.numStatutMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into STATUT(idSt,nomSt) values (?,?)");
        ps.setInt(1, num);
        ps.setString(2, s.getNom());
        ps.executeUpdate();
    }

    public void supprimerStatut(Statut s)throws SQLException{
        int idSt = s.getIdentifiant();
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from STATUT where " + idSt + "=idSt");
        rs.next();
        rs.close();
    }

    public void modifierStatut( Statut s)throws SQLException{
        int idSt = s.getIdentifiant();
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE STATUT SET nomSt = ? where " + idSt + "=idSt");
        ps.setString(1, s.getNom());
        ps.executeUpdate();
    }

    public Statut rechercherStatutParNum(int idSt)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from STATUT where "+idSt+"=idSt");
        rs.next();
        Statut c=new Statut(rs.getInt(1),rs.getString(2));
        rs.close();
        return c;
    }

    public List<Statut> listeStatuts()throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Statut> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from STATUT");
        while(rs.next()){
            Statut s=new Statut(rs.getInt(1),rs.getString(2));
            liste.add(s);
    }
        rs.close();
        return liste;
    }
}
