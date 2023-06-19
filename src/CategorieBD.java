import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CategorieBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public CategorieBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }
    public int numCategorieMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idCat),0) leMax from CATEGORIE");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }

    public void insererCategorie(Categorie c)throws SQLException{
        int num = this.numCategorieMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into CATEGORIE(idCat,nomCat) values (?,?)");
        ps.setInt(1, num);
        ps.setString(2, c.getNom());
        ps.executeUpdate();
    }

    public void supprimerCategorie(Categorie c)throws SQLException{
        int idCat = c.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from CATEGORIE where "+idCat+"=idCat");
        rs.next();
        rs.close();
    }

    public void modifierCategorie( Categorie c)throws SQLException{
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE CATEGORIE SET nomCat = ? where idCat="+c.getIdentifiant());
        ps.setString(1, c.getNom());
        ps.executeUpdate();
    }

    public Categorie rechercherCategorieParNum(int idCat)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from CATEGORIE where "+idCat+"=idCat");
        rs.next();
        Categorie c=new Categorie(rs.getInt(1),rs.getString(2));
        rs.close();
        return c;
    }

    public List<Categorie> listeCategories()throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Categorie> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from CATEGORIE");
        while(rs.next()){
            Categorie c=new Categorie(rs.getInt(1),rs.getString(2));
            liste.add(c);
    }
        rs.close();
        return liste;
    }
}
