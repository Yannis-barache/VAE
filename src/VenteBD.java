import java.sql.PreparedStatement;
import java.time.LocalDateTime;
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
        System.out.println(v.getObjet().getLesPhotos());
    }

    public void supprimerVente(Vente v)throws SQLException{
        int idVe = v.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from VENTE where "+idVe+"=idVe");

        if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
            this.changeStatut(v, 2);
        }

        if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
            this.changeStatut(v, 4);
            
        }
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
        if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
            this.changeStatut(v, 2);
        }

        if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
            this.changeStatut(v, 4);
            
        }
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
            

            if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
                this.changeStatut(v, 2);
            }

            if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
                this.changeStatut(v, 4);
                
            }
            liste.add(v);
        }
    //     (1,'A venir'),
	// (2,'En cours'),
	// (3,'A valider'),
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
            if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
                this.changeStatut(v, 2);
            }

            if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
                this.changeStatut(v, 4);
                
            }
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

            if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
                this.changeStatut(v, 2);
            }

            if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
                this.changeStatut(v, 4);
                
            }
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
            if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
                this.changeStatut(v, 2);
            }

            if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
                this.changeStatut(v, 4);
                
            }
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
        if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
            this.changeStatut(v, 2);
        }

        if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
            this.changeStatut(v, 4);
            
        }
        int nb = rs.getInt(1);
        rs.close();
        return nb;
    }

    public List<Vente> VentesCommencantPar(String recherche) throws SQLException{
        List<Vente> liste = new ArrayList<>();
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from VENTE natural join OBJET where nomOb LIKE '%"+ recherche +"%'");
        while(rs.next()){
            Vente v = new Vente(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), statutBD.rechercherStatutParNum(rs.getInt(7)), objetBD.rechercherObjetParNum(rs.getInt(1)));
            if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
                this.changeStatut(v, 2);
            }

            if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
                this.changeStatut(v, 4);
                
            }
            liste.add(v);
        }
        rs.close();
        return liste;
    }

    public List<Vente> VentesParCategorie(String categorie) throws SQLException{
        List<Vente> liste = new ArrayList<>();
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from VENTE natural join OBJET natural join CATEGORIE where '"+ categorie +"' = nomCat");
        while(rs.next()){
            Vente v = new Vente(rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), statutBD.rechercherStatutParNum(rs.getInt(8)), objetBD.rechercherObjetParNum(rs.getInt(2)));
            
            
            liste.add(v);
        }
        rs.close();
        return liste;
    }

    public List<Vente> VentePrixActuelInf(int prix) throws SQLException{
        List<Vente> liste = new ArrayList<>();
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from VENTE where idve in (select idve from ENCHERIR E1 natural join VENTE V natural join STATUT S where nomSt='Validée' and montant<"+prix+" and montant>= all( select montant from ENCHERIR E2 where E1.idVe=E2.idVe))");
        while(rs.next()){
            Vente v = new Vente(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), statutBD.rechercherStatutParNum(rs.getInt(7)), objetBD.rechercherObjetParNum(rs.getInt(6)));
            if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
                this.changeStatut(v, 2);
            }

            if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
                this.changeStatut(v, 4);
                
            }
            liste.add(v);
        }
        rs.close();
        return liste;
    }

    public List<Vente> VenteDateFinInf(String date) throws SQLException{
        List<Vente> liste = new ArrayList<>();
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from VENTE where DATEDIFF( finve, STR_TO_DATE('"+date+"','%d/%m/%Y:%H:%i:%s'))<0 and idSt = 2");
        while(rs.next()){
            Vente v = new Vente(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), statutBD.rechercherStatutParNum(rs.getInt(7)), objetBD.rechercherObjetParNum(rs.getInt(6)));
            if ( (v.getDebut()+":00").compareTo(""+LocalDateTime.now())>0 && rs.getInt(7)!=2){
                this.changeStatut(v, 2);
            }

            if ((v.getFin()+":00").compareTo(""+LocalDateTime.now())>0 ){
                this.changeStatut(v, 4);
                
            }
            liste.add(v);
        }
        rs.close();
        System.out.println(liste);
        return liste;
    }


    public void changeStatut(Vente vente,int idStatut){
        System.out.println("Changement de statut pour "+ idStatut);
        vente.setStatut(new Statut("Terminé"));
        try{
            PreparedStatement ps=this.connexMySQL.prepareStatement("UPDATE VENTE SET idStatut = ?  where "+vente.getIdentifiant()+"=idVe");
            ps.setInt(1, idStatut);
        } catch (SQLException ex){
            System.out.println("Changement impossible");
        }
        
    }




}

