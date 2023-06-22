import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UtilisateurBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;
    private Utilisateur utilisateur;

    public UtilisateurBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }


    /**
     * Méthode qui permet de vérifier des identifiants de connexion d'un utilisateur
     * @param pseudo Le pseudo de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     * @return true si l'utilisateur existe, false sinon
     * @throws SQLException Si la requête SQL est incorrecte
     */
    public boolean connexion(String pseudo, String motDePasse) throws SQLException{
        try{
            boolean bool=false;
            Statement s=connexMySQL.createStatement();
            ResultSet rs=s.executeQuery("SELECT * from UTILISATEUR where mdput='"+motDePasse+"' and pseudout='" +pseudo+"'");
            if (rs.next()){
                bool=true;
                boolean actif= false;
                boolean admin= false;
                if (rs.getString("activeut").equals("O")){
                    actif=true;
                }

                if (rs.getInt("idrole")== 1){
                    admin=true;
                }
                Utilisateur utilisateur = new Utilisateur(rs.getInt("idut"), rs.getString("pseudout"), rs.getString("emailut"),rs.getString("mdput"), actif,admin);
                this.utilisateur = utilisateur;
            }
            return bool;
        } catch (SQLException ex){
            throw new SQLException();
        }

    }

    public int numUtilisateurMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requete sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idUt),0) leMax from UTILISATEUR");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;

    }

    public void insererUtilisateur(Utilisateur u)throws SQLException{
        int role;
        int num = this.numUtilisateurMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into UTILISATEUR(idUt,pseudoUt,emailUT,mdpUt,activeUt,idRole) values (?,?,?,?,?,?)");
        if(u.isAdmin()){
            role=1;
        }
        else{
            role=2;
        }
        ps.setInt(1, num);
        ps.setString(2, u.getPseudo());
        ps.setString(3, u.getMail());
        ps.setString(4, u.getMdp());
        ps.setString(5, "O");
        ps.setInt(6, role);
        ps.executeUpdate();
        u.setIdentifiant(num);
    }

    public void supprimerUtilisateur(Utilisateur u)throws SQLException{
        int idUt= u.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from UTILISATEUR where "+idUt+"=idUt");
        rs.next();
        rs.close();
    }

    public void modifierUtilisateur(Utilisateur u)throws SQLException{
        int role;
        String actif="";
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE UTILISATEUR SET pseudoUt = ?,emailUT = ?,mdpUt = ?,activeUt = ?,idRole = ? where idut="+u.getIdentifiant());
        if(u.isAdmin()){
            role=1;
        }
        else{
            role=2;
        }
        if(u.isActif()){
            actif="O";
        }
        else{
            actif="N";
        }
        ps.setString(1, u.getPseudo());
        ps.setString(2, u.getMail());
        ps.setString(3, u.getMdp());
        ps.setString(4, actif);
        ps.setInt(5, role);
        ps.executeUpdate();
    }

    public Utilisateur rechercherUtilisateurParNum(int idUt)throws SQLException{
        st= this.connexMySQL.createStatement();



        ResultSet rs = st.executeQuery("select * from UTILISATEUR where "+idUt+"=idUt");
        rs.next();
        boolean admin=false;
        boolean actif = true;
        if(rs.getInt(6)==1){
            admin=true;
        }
        if(rs.getString(5).equals("N")){
            actif=false;
        }
        Utilisateur u=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),actif,admin);
        rs.close();
        
        return u;
    }

    public void ventesUtilisateur(Utilisateur u) throws SQLException {
        st= this.connexMySQL.createStatement();
        StatutBD sBD = new StatutBD(connexMySQL);
        ObjetBD oBD = new ObjetBD(connexMySQL);
        try {
            ResultSet rs = st.executeQuery("select * from VENTE natural join OBJET where  "+ u.getIdentifiant() + " = idUt");
            while(rs.next()){
                Vente v = new Vente(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), sBD.rechercherStatutParNum(rs.getInt(7)), oBD.rechercherObjetParNum(rs.getInt(1)));
                if (!u.getVentes().contains(v)){
                    u.ajouterVente(v);
                }
            }
                
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void encheresUtilisateur(Utilisateur u) throws SQLException {
        st= this.connexMySQL.createStatement();
        VenteBD vBd = new VenteBD(connexMySQL);
        try {
            ResultSet rs3 = st.executeQuery("select * from ENCHERIR where " + u.getIdentifiant() + " = idUt");
            while (rs3.next()){
                Enchere e = new Enchere(vBd.rechercherVenteParNum(rs3.getInt(2)), u, rs3.getInt(4), rs3.getString(3));
                if (!u.getEncheres().contains(e)){
                    u.ajouterEnchere(e);
                }
                    
            }
            rs3.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Utilisateur> listeUtilisateurs()throws SQLException{
        st= this.connexMySQL.createStatement();
        List<Utilisateur> liste = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from UTILISATEUR");
        while(rs.next()){
            boolean admin=false;
            boolean actif = true;
            if(rs.getInt(6)==1){
                admin=true;
            }
            if(rs.getString(5).equals("N")){
                actif=false;
            }
            Utilisateur u=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),actif,admin);
            liste.add(u);
    }
        rs.close();
        return liste;
    }

    // public void activerUtilisateur
}
