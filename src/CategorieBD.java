import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CategorieBD {

    /**
     * La connexion à la base de données.
     */
    private ConnexionMySQL connexMySQL;

    /**
     * L'objet qui permet d'effectuer des requêtes.
     */
    private Statement st;

    /**
     * Constructeur de la classe CategorieBD.
     * @param connexMySQL La connexion à la base de données.
     */
    public CategorieBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }

    /**
     * Obtient l'identifiant maximum des catégories.
     * @return L'identifiant maximum des catégories.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public int numCategorieMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idCat),0) leMax from CATEGORIE");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }

    /**
     * Insère une catégorie dans la base de données.
     * @param c La catégorie à insérer.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public void insererCategorie(Categorie c)throws SQLException{
        int num = this.numCategorieMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into CATEGORIE(idCat,nomCat) values (?,?)");
        ps.setInt(1, num);
        ps.setString(2, c.getNom());
        ps.executeUpdate();
        c.setIdentifiant(num);
    }

    /**
     * Supprime une catégorie de la base de données.
     * @param c La catégorie à supprimer.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public void supprimerCategorie(Categorie c)throws SQLException{
        int idCat = c.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from CATEGORIE where "+idCat+"=idCat");
        rs.next();
        rs.close();
    }

    /**
     * Modifie une catégorie dans la base de données.
     * @param c La catégorie à modifier.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public void modifierCategorie( Categorie c)throws SQLException{
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE CATEGORIE SET nomCat = ? where idCat="+c.getIdentifiant());
        ps.setString(1, c.getNom());
        ps.executeUpdate();
    }


    /**
     * Recherche une catégorie dans la base de données.
     * @param idCat L'identifiant de la catégorie à rechercher.
     * @return La catégorie recherchée.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public Categorie rechercherCategorieParNum(int idCat)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from CATEGORIE where "+idCat+"=idCat");
        rs.next();
        Categorie c=new Categorie(rs.getInt(1),rs.getString(2));
        rs.close();
        return c;
    }


    /**
     * Recherche une catégorie dans la base de données.
     * @param nomCat Le nom de la catégorie à rechercher.
     * @return La catégorie recherchée.
     * @throws SQLException Si une erreur SQL se produit.
     */

    public Categorie rechercherCategorieParNom(String nomCat)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from CATEGORIE where '"+nomCat+"'=nomCat");
        rs.next();
        Categorie c=new Categorie(rs.getInt(1),rs.getString(2));
        rs.close();
        return c;
    }

    /**
     * Obtient la liste des catégories.
     * @return La liste des catégories.
     * @throws SQLException Si une erreur SQL se produit.
     */
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
