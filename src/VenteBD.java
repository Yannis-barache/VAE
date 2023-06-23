import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class VenteBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    /**
     * Constructeur de la classe VenteBD.
     * @param connexMySQL L'objet ConnexionMySQL utilisé pour la connexion à la base de données.
     */
    public VenteBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }
    /**
     * Obtient le numéro de vente maximum dans la base de données.
     * @return Le numéro de vente maximum.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
    public int numVenteMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idOb),0) leMax from VENTE");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }
    /**
     * Insère une nouvelle vente dans la base de données.
     * @param v L'objet Vente à insérer.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
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

    /**
     * Supprime une vente de la base de données.
     * @param v L'objet Vente à supprimer.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
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
    /**
     * Modifie la date de fin d'une vente dans la base de données.
     * @param id L'identifiant de la vente à modifier.
     * @param nvFin La nouvelle date de fin de la vente.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
    public void modifierVente(int id ,String nvFin)throws SQLException{
        // UPDATE table SET colonne_1 = 'valeur 1', colonne_2 = 'valeur 2', colonne_3 = 'valeur 3' WHERE condition
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE VENTE SET finVe = ? where "+id+"=idVe");
        ps.setString(1, nvFin);
        ps.executeUpdate();
    }
    /**
     * Recherche une vente par son numéro.
     * @param idVe Le numéro de vente à rechercher.
     * @return L'objet Vente correspondant au numéro recherché.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
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
    /**
     * Récupère la liste de toutes les ventes dans la base de données.
     * @return La liste des ventes.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
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
    /**
     * Récupère la liste des ventes en cours dans la base de données.
     * @return La liste des ventes en cours.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
    public List<Vente> listeVentesEnCours() throws SQLException{
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
        rs.close();
        return liste;
    }

    /**
     * Obtient la dernière enchère pour une vente donnée.
     * @param v La vente pour laquelle récupérer la dernière enchère.
     * @return L'objet Enchere représentant la dernière enchère, ou null s'il n'y a pas d'enchère pour la vente.
     * @throws SQLException Si une erreur se produit lors de l'exécution de la requête SQL.
     */
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

    /**
     * Récupère la dernière enchère d'un utilisateur pour une vente donnée.
     *
     * @param v La vente pour laquelle récupérer la dernière enchère.
     * @param u L'utilisateur concerné.
     * @return L'objet Enchere représentant la dernière enchère de l'utilisateur pour la vente, ou null s'il n'y a pas d'enchère.
     * @throws SQLException si une erreur survient lors de l'exécution de la requête SQL.
     */
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


    /**
     * Récupère le nombre total d'enchères pour une vente donnée.
     *
     * @param v La vente pour laquelle compter les enchères.
     * @return Le nombre total d'enchères sur la vente.
     * @throws SQLException si une erreur survient lors de l'exécution de la requête SQL.
     */
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

    /**
     * Récupère la liste des ventes dont le nom de l'objet commence par une certaine chaîne de recherche.
     *
     * @param recherche La chaîne de recherche.
     * @return La liste des ventes correspondantes.
     * @throws SQLException si une erreur survient lors de l'exécution de la requête SQL.
     */
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

    /**
     * Récupère la liste des ventes appartenant à une certaine catégorie.
     *
     * @param categorie La catégorie des ventes à récupérer.
     * @return La liste des ventes de la catégorie donnée.
     * @throws SQLException si une erreur survient lors de l'exécution de la requête SQL.
     */
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

    /**
     * Récupère la liste des ventes dont le prix actuel est inférieur à une certaine valeur.
     *
     * @param prix Le prix maximal des ventes à récupérer.
     * @return La liste des ventes dont le prix actuel est inférieur à la valeur donnée.
     * @throws SQLException si une erreur survient lors de l'exécution de la requête SQL.
     */
    public List<Vente> VentePrixActuelInf(int prix) throws SQLException{
        List<Vente> liste = new ArrayList<>();
        StatutBD statutBD = new StatutBD(connexMySQL);
        ObjetBD objetBD = new ObjetBD(connexMySQL);
        st = this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from VENTE where idve in (select idve from ENCHERIR E1 natural join VENTE V natural join STATUT S where montant<"+prix+" and montant>= all( select montant from ENCHERIR E2 where E1.idVe=E2.idVe))");
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

    /**
     * Récupère la liste des ventes dont la date de fin est antérieure à une certaine date.
     *
     * @param date La date de référence.
     * @return La liste des ventes dont la date de fin est antérieure à la date donnée.
     * @throws SQLException si une erreur survient lors de l'exécution de la requête SQL.
     */
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

    /**
     * Change le statut d'une vente donnée.
     *
     * @param vente La vente pour laquelle changer le statut.
     * @param idStatut L'identifiant du nouveau statut.
     */
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

