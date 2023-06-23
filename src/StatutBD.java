import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StatutBD {
        /**
     * La connexion à la base de données MySQL.
     */
    private ConnexionMySQL connexMySQL;
       /**
     * L'objet utilisé pour exécuter des instructions SQL.
     */
    private Statement st;

        /**
     * Constructeur de la classe StatutBD.
     * @param connexMySQL La connexion à la base de données MySQL.
     */
    public StatutBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }

        /**
     * Récupère le numéro maximum de statut dans la table STATUT.
     * @return Le numéro maximum de statut.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public int numStatutMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idSt),0) leMax from STATUT");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }
    /**
     * Insère un nouvel objet Statut dans la table STATUT.
     * Le numéro de statut est automatiquement généré.
     * @param s Le Statut à insérer.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public void insererStatut(Statut s)throws SQLException{
        int num = this.numStatutMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into STATUT(idSt,nomSt) values (?,?)");
        ps.setInt(1, num);
        ps.setString(2, s.getNom());
        ps.executeUpdate();
        s.setIdentifiant(num);
    }
    /**
     * Supprime un objet Statut de la table STATUT.
     * @param s Le Statut à supprimer.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public void supprimerStatut(Statut s)throws SQLException{
        int idSt = s.getIdentifiant();
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from STATUT where " + idSt + "=idSt");
        rs.next();
        rs.close();
    }
    /**
     * Modifie un objet Statut dans la table STATUT.
     * @param s Le Statut modifié.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public void modifierStatut( Statut s)throws SQLException{
        int idSt = s.getIdentifiant();
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE STATUT SET nomSt = ? where " + idSt + "=idSt");
        ps.setString(1, s.getNom());
        ps.executeUpdate();
    }
    /**
     * Recherche un objet Statut dans la table STATUT en utilisant son numéro.
     * @param idSt Le numéro du statut à rechercher.
     * @return Le Statut correspondant au numéro spécifié, ou null s'il n'est pas trouvé.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
    public Statut rechercherStatutParNum(int idSt)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from STATUT where "+idSt+"=idSt");
        rs.next();
        Statut c=new Statut(rs.getInt(1),rs.getString(2));
        rs.close();
        return c;
    }
    /**
     * Récupère la liste de tous les statuts de la table STATUT.
     * @return Une liste contenant tous les statuts de la table.
     * @throws SQLException Si une erreur SQL se produit lors de l'exécution de la requête.
     */
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
