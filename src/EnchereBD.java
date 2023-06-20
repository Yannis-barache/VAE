import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class EnchereBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public EnchereBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }

    public void insererEnchere(Enchere e)throws SQLException{
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into ENCHERIR(idUT,idVe,dateheure,montant) values (?,?,STR_TO_DATE(?,'%d/%m/%Y:%h:%i:%s'),?)");
        ps.setInt(1, e.getEncherisseur().getIdentifiant());
        ps.setInt(2, e.getVente().getIdentifiant());
        ps.setString(3, e.getDate());
        ps.setInt(4, e.getMontant());
        ps.executeUpdate();
        System.out.println("------------------");
        System.out.println("Encherrisseur : ");
        System.out.println(e.getEncherisseur());
        System.out.println("------------------");
        e.getEncherisseur().ajouterEnchere(e);
    }

    public void supprimerEnchere(Enchere e)throws SQLException{
        int idUT = e.getEncherisseur().getIdentifiant();
        int idVe = e.getVente().getIdentifiant();
        String dateheure = e.getDate();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from ENCHERIR where "+idUT+"=idUT and "+idVe+"=idVe and STR_TO_DATE('"+dateheure+"','%d/%m/%Y:%h:%i:%s')=dateheure");
        rs.next();
        rs.close();
        e.getEncherisseur().supprimerEnchere(e);
    }

    public Enchere rechercherEnchereParNum(int idVe, int idUt, String dateheure)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from ENCHERE where "+idUt+"=idUT and "+idVe+"=idVe and STR_TO_DATE('"+dateheure+"','%d/%m/%Y:%h:%i:%s')=dateheure");
        VenteBD venteBD = new VenteBD(connexMySQL);
        UtilisateurBD utilisateurBD = new UtilisateurBD(connexMySQL);
        rs.next();
        Enchere e = new Enchere(venteBD.rechercherVenteParNum(rs.getInt(2)), utilisateurBD.rechercherUtilisateurParNum(rs.getInt(1)), rs.getInt(4), rs.getString(3));
        rs.close();
        return e;
    }

    public List<Enchere> listeEncheres() throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Enchere> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from ENCHERE");
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
