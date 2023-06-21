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

    public int numVenteMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idOb),0) leMax from VENTE");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }

    public void insererVente(Vente v)throws SQLException{
        int num = this.numVenteMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into VENTE(idVe,prixBase,prixMin,debutVe,finVe,idSt,idOb) values (?,?,?,STR_TO_DATE(?,'%d/%m/%Y:%H:%i:%s'),STR_TO_DATE(?,'%d/%m/%Y:%H:%i:%s'),?,?)");
        ps.setInt(1, num);
        ps.setInt(2, v.getPrixBase());
        ps.setInt(3, v.getPrixMin());
        ps.setString(4, v.getdebutVente());
        ps.setString(5, v.getFinVente());
        ps.setInt(6, v.getStatus().getIdentifiant());
        ps.setInt(7, v.getObjet().getIdentifiant());
        ps.executeUpdate();
        v.setIdentifiant(num);
        v.getObjet().getVendeur().ajouterVente(v);
    }

    public void supprimerVente(Vente v)throws SQLException{
        int idVe = v.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from VENTE where "+idVe+"=idVe");
        rs.next();
        rs.close();
        v.getObjet().getVendeur().supprimerVente(v);
    }

    public void modifierVente(int id ,String nvFin)throws SQLException{
        // UPDATE table SET colonne_1 = 'valeur 1', colonne_2 = 'valeur 2', colonne_3 = 'valeur 3' WHERE condition
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE VENTE SET finVe = ? where "+id+"=idVe");
        ps.setString(1, nvFin);
        ps.executeUpdate();
    }

    public Vente rechercherVenteParNum(int idVe)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from VENTE where "+idVe+"=idVe");
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        rs.next();
            
            Vente v = new Vente(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), statutBD.rechercherStatutParNum(rs.getInt(7)), objetBD.rechercherObjetParNum(rs.getInt(6)));
        rs.close();
        return v;
    }

    public List<Vente> listeVentes() throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Vente> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from VENTE");
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        while(rs.next()){
            Vente v = new Vente(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), statutBD.rechercherStatutParNum(rs.getInt(7)), objetBD.rechercherObjetParNum(rs.getInt(6)));
            liste.add(v);
        }
        rs.close();
        return liste;
    }

    public List<Vente> listeVentesEnCours() throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Vente> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from VENTE where idSt = 2");
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        while(rs.next()){
            Vente v = new Vente(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), statutBD.rechercherStatutParNum(rs.getInt(7)), objetBD.rechercherObjetParNum(rs.getInt(6)));
            liste.add(v);
        }
        rs.close();
        return liste;
    }

    public Enchere derniereEnchere(Vente v) throws SQLException{
        st = this.connexMySQL.createStatement();
        UtilisateurBD uBd = new UtilisateurBD(connexMySQL);
        try {
            ResultSet rs = st.executeQuery("select * from ENCHERIR where idVe ="+ v.getIdentifiant() +" order by montant desc limit 1;");
            rs.next();
                Enchere e = new Enchere(v, uBd.rechercherUtilisateurParNum(rs.getInt(1)) , rs.getInt(4), rs.getString(3));
            rs.close();
            return e;
        } catch (Exception e) {
            System.out.println("Pas d'enchere sur la vente");
        }
        return null;
    }

    public Enchere derniereEnchereUtilisateur(Vente v, Utilisateur u) throws SQLException{
        st = this.connexMySQL.createStatement();
        //select * from ENCHERIR where idVe = 53 and idUt = 647  order by montant desc limit 1;
        try {
            ResultSet rs = st.executeQuery("select * from ENCHERIR where idVe ="+ v.getIdentifiant() +" and  idUt = "+ u.getIdentifiant() +" order by montant desc limit 1;");
            rs.next();
                Enchere e = new Enchere(v, u , rs.getInt(4), rs.getString(3));
            rs.close();
            return e;
        } catch (Exception e) {
            System.out.println("Pas d'enchere sur la vente");
        }
        return null;
    }

    public int nbTotalEnchereSurUneVente(Vente v)throws SQLException{
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select Count(*) nb from ENCHERIR where idve="+v.getIdentifiant());
        rs.next();
        int nb = rs.getInt(1);
        rs.close();
        return nb;
    }
}

