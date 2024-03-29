import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ObjetBD {
    /**
     * La connexion à la base de données
     */
    private ConnexionMySQL connexMySQL;
    /**
     * La requête SQL
     */
    private Statement st;

        /**
     * Initialise un nouvel objet ObjetBD avec la connexion MySQL spécifiée.
     *
     * @param connexMySQL La connexion MySQL à utiliser.
     */
    public ObjetBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }

        /**
     * Renvoie le numéro maximum d'objet dans la base de données.
     *
     * @return Le numéro maximum d'objet.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public int numObjetMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idOb),0) leMax from OBJET");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }

    /**
     * Insère un nouvel objet dans la base de données.
     *
     * @param o L'objet à insérer.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public void insererObjet(Objet o)throws SQLException{
        int num = this.numObjetMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into OBJET(idOb,nomOb,descriptionOb,idCat,idUt) values (?,?,?,?,?)");
        ps.setInt(1, num);
        ps.setString(2, o.getNom());
        ps.setString(3, o.getDescription());
        ps.setInt(4, o.getCategorie().getIdentifiant());
        ps.setInt(5, o.getVendeur().getIdentifiant());
        ps.executeUpdate();
        o.setIdentifiant(num);
    }

     /**
     * Supprime un objet de la base de données.
     *
     * @param o L'objet à supprimer.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public void supprimerObjet(Objet o)throws SQLException{
        int idOb = o.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from OBJET where "+idOb+"=idOb");
        rs.next();
        rs.close();
    }

    /**
     * Modifie les informations d'un objet dans la base de données.
     *
     * @param id L'identifiant de l'objet à modifier.
     * @param nom Le nouveau nom de l'objet.
     * @param description La nouvelle description de l'objet.
     * @param categorie La nouvelle catégorie de l'objet.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public void modifierObjet(int id,String nom, String description,Categorie categorie)throws SQLException{
        // UPDATE table SET colonne_1 = 'valeur 1', colonne_2 = 'valeur 2', colonne_3 = 'valeur 3' WHERE condition
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE OBJET SET nomOb = ?,descriptionOb = ?,idCat = ? where "+id+"=idOb");
        ps.setString(1, nom);
        ps.setString(2, description);
        ps.setInt(3, categorie.getIdentifiant());
        ps.executeUpdate();
    }

    /**
     * Recherche un objet dans la base de données en fonction de son numéro.
     *
     * @param idOb Le numéro de l'objet à rechercher.
     * @return L'objet trouvé.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public Objet rechercherObjetParNum(int idOb)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from OBJET where "+idOb+"=idOb");
        rs.next();
        CategorieBD categorieBD = new CategorieBD(connexMySQL);
        UtilisateurBD utilisateurBD = new UtilisateurBD(connexMySQL);
        Objet o = new Objet(rs.getInt(1), rs.getString(2),  rs.getString(3), categorieBD.rechercherCategorieParNum(rs.getInt(5)) , utilisateurBD.rechercherUtilisateurParNum(rs.getInt(4)));
        rs.close();
        return o;
    }

    /**
     * Renvoie une liste de tous les objets dans la base de données.
     *
     * @return La liste des objets.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public List<Objet> listeObjets() throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Objet> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from OBJET");
        CategorieBD categorieBD = new CategorieBD(connexMySQL);
        UtilisateurBD utilisateurBD = new UtilisateurBD(connexMySQL);
        while(rs.next()){
            Objet o=new Objet(rs.getInt(1), rs.getString(2),  rs.getString(3), categorieBD.rechercherCategorieParNum(rs.getInt(4)) , utilisateurBD.rechercherUtilisateurParNum(rs.getInt(5)));
            liste.add(o);
        }
        rs.close();
        return liste;
    }
}

