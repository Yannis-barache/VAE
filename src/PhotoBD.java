import java.sql.PreparedStatement;
import java.sql.*;

public class PhotoBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public PhotoBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }
    public int numPhotoMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idPh),0) leMax from PHOTO");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }

    public void insererPhoto(Photo ph,int idOb)throws SQLException{
        int num = this.numPhotoMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into PHOTO(idph,titreph,imgph,idob) values (?,?,?,?)");
        ps.setInt(1, num);
        ps.setString(2, ph.getTitre());
        ps.setBlob(3, ph.getImg());
        ps.setInt(4, idOb);
        ps.executeUpdate();
        ph.setIdentifiant(num);
    }

    public void supprimerPhoto(Photo ph)throws SQLException{
        int idph = ph.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from PHOTO where "+idph+"=idph");
        rs.next();
        rs.close();
    }

    public void modifierPhoto( Photo ph)throws SQLException{
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE PHOTO SET titreph = ? , imgph = ? where idph="+ph.getIdentifiant());
        ps.setString(1, ph.getTitre());
        ps.setBlob(2, ph.getImg());
        ps.executeUpdate();
    }

    public Photo rechercherPhotoParNum(int idph)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from PHOTO where "+idph+"=idph");
        rs.next();
        Photo ph=new Photo(rs.getInt(1),rs.getString(2),rs.getBlob(3));
        rs.close();
        return ph;
    }
}
