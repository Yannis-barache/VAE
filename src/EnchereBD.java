import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class EnchereBD {

    /**
     * La connexion à la base de données
     */
    private ConnexionMySQL connexMySQL;

    /**
     * La requête SQL
     */
    private Statement st;

    /**
     * Constructeur de la classe EnchereBD.
     *
     * @param connexMySQL La connexion à la base de données.
     */
    public EnchereBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }


    /**
     * Insère une enchère dans la base de données.
     * @param e L'enchère à insérer.
     * @throws SQLException Si une erreur survient lors de l'insertion.
     */
    public void insererEnchere(Enchere e)throws SQLException{
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into ENCHERIR(idUT,idVe,dateheure,montant) values (?,?,STR_TO_DATE(?,'%d/%m/%Y:%H:%i:%s'),?)");
        ps.setInt(1, e.getEncherisseur().getIdentifiant());
        ps.setInt(2, e.getVente().getIdentifiant());
        ps.setString(3, e.getDate());
        ps.setInt(4, e.getMontant());
        ps.executeUpdate();
        e.getEncherisseur().ajouterEnchere(e);
    }

    /**
     * Supprime une enchère de la base de données.
     * @param e L'enchère à supprimer.
     * @throws SQLException Si une erreur survient lors de la suppression.
     */
    public void supprimerEnchere(Enchere e)throws SQLException{
        try{
        int idUT = e.getEncherisseur().getIdentifiant();
        int idVe = e.getVente().getIdentifiant();
        String dateheure = e.getDate();
        dateheure=dateheure.replace(" ",":");
        dateheure=dateheure.replace(".0","");
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from ENCHERIR where "+idVe+"=idVe");
        rs.next();
        rs.close();
        e.getEncherisseur().supprimerEnchere(e);
        }
        catch(Exception ex){System.out.println("eeeee "+ex);}
    }

    /**
     * Recherche une enchère dans la base de données.
     * @param idVe L'identifiant de la vente associée à l'enchère.
     * @param idUt L'identifiant de l'utilisateur encherisseur.
     * @param dateheure La date de l'enchère.
     * @return L'enchère recherchée.
     * @throws SQLException Si une erreur survient lors de la recherche.
     */
    public Enchere rechercherEnchereParNum(int idVe, int idUt, String dateheure)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from ENCHERIR where "+idUt+"=idUT and "+idVe+"=idVe and STR_TO_DATE('"+dateheure+"','%d/%m/%Y:%h:%i:%s')=dateheure");
        VenteBD venteBD = new VenteBD(connexMySQL);
        UtilisateurBD utilisateurBD = new UtilisateurBD(connexMySQL);
        rs.next();
        Enchere e = new Enchere(venteBD.rechercherVenteParNum(rs.getInt(2)), utilisateurBD.rechercherUtilisateurParNum(rs.getInt(1)), rs.getInt(4), rs.getString(3));
        rs.close();
        return e;
    }

    /**
     * Recherche toutes les enchères de la base.
     * @return La liste des enchères.
     */
    public List<Enchere> listeEncheres() throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Enchere> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from ENCHERIR");
        VenteBD venteBD = new VenteBD(connexMySQL);
        UtilisateurBD utilisateurBD = new UtilisateurBD(connexMySQL);
        while(rs.next()){
            Enchere e = new Enchere(venteBD.rechercherVenteParNum(rs.getInt(2)), utilisateurBD.rechercherUtilisateurParNum(rs.getInt(1)), rs.getInt(4), rs.getString(3));
            liste.add(e);
        }
        rs.close();
        return liste;
    }

    /**
     * Recherche toutes les enchères de la base.
     * @return La liste des enchères.
     */
    public List<Enchere> listeEncheresParVente(Vente v) throws SQLException{
        st= this.connexMySQL.createStatement();
        int numV = v.getIdentifiant();
        List<Enchere> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from ENCHERIR where idve="+numV);
        VenteBD venteBD = new VenteBD(connexMySQL);
        UtilisateurBD utilisateurBD = new UtilisateurBD(connexMySQL);
        while(rs.next()){
            Enchere e = new Enchere(venteBD.rechercherVenteParNum(rs.getInt(2)), utilisateurBD.rechercherUtilisateurParNum(rs.getInt(1)), rs.getInt(4), rs.getString(3));
            liste.add(e);
        }
        rs.close();
        return liste;
    }
}
