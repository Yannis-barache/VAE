import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class VenteBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public VenteBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }

    // public int numVenteMax()throws SQLException{
    //     st= this.connexMySQL.createStatement();
    //     // execute la requte sql
    //     ResultSet rs = st.executeQuery("select IFNULL(max(idOb),0) leMax from VENTE");
    //     rs.next();
    //     int res  = rs.getInt(1);
    //     rs.close();
    //     return res;
    // }

    // public void insererVente(Vente v)throws SQLException{
    //     int num = this.numObjetMax()+1;
    //     PreparedStatement ps = this.connexMySQL.prepareStatement("insert into Objet(idOb,nomOb,descriptionOb,idCat,idUt) values (?,?,?,?,?)");
    //     ps.setInt(1, num);
    //     ps.setString(2, o.getNom());
    //     ps.setString(3, o.getDescription());
    //     ps.setInt(4, o.getCategorie().getIdentifiant());
    //     ps.setInt(5, o.getVendeur().getIdentifiant());
    //     ps.executeUpdate();
    // }

}
