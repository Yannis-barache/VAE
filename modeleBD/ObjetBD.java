package modeleBD;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ObjetBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public ObjetBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }
    public int numObjetMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idOb),0) leMax from OBJET");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;

    }

    public void insererObjet(Objet o)throws SQLException{
        int num = this.numObjetMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into Objet(idOb,nomOb,descriptionOb,idCat,idOb) values (?,?,?,?,?)");
        ps.setInt(1, num);
        ps.setString(2, o.getNomOb());
        ps.setString(3, o.getDescriptionOb());
        ps.setInt(4, o.getIdCat());
        ps.setString(5, o.getidOb());
        ps.executeUpdate();
    }

    public void supprimerObjet(int idOb)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from Objet where "+idOb+"=idOb");
        rs.next();
        rs.close();
    }

    public void modifierObjet( Objet o){
        int num = this.numObjetMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE Objet SET idOb = ?,nomOb,descriptionOb,idCat,idOb");
        ps.setInt(1, num);
        ps.setString(2, o.getPseudoUt());
        ps.setString(3, o.getEmailUt());
        ps.setString(4, o.getmdpUt());
        ps.setString(5, "O");
        ps.setInt(6, role);
        ps.executeUpdate();
    }

    public Objet rechercherObjetParNum(int idOb){
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from Objet where "+idOb+"=idOb");
        rs.next();
        boolean role=false;
        if(rs.getInt(6)==1){
            role=true;
        }
        o=new Objet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),role);
        rs.close();
    }

    public List<Objet> listeObjets(){
        st= this.connexMySQL.createStatement();
        List<Objet> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from Objet");
        while(rs.next()){
            boolean role=false;
            if(rs.getInt(6)==1){
                role=true;
            }
            o=new Objet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),role);
            liste.add(u);
    }
        rs.close();
    }
}

