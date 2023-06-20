import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ObjetBD {
    private ConnexionMySQL connexMySQL;
    private Statement st;

    public ObjetBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }
    public int numObjetMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idOb),0) leMax from OBJET");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }

    public void insererObjet(Objet o)throws SQLException{
        int num = this.numObjetMax()+1;
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into Objet(idOb,nomOb,descriptionOb,idCat,idUt) values (?,?,?,?,?)");
        ps.setInt(1, num);
        ps.setString(2, o.getNom());
        ps.setString(3, o.getDescription());
        ps.setInt(4, o.getCategorie().getIdentifiant());
        ps.setInt(5, o.getVendeur().getIdentifiant());
        ps.executeUpdate();
        o.setIdentifiant(num);
    }

    public void supprimerObjet(Objet o)throws SQLException{
        int idOb = o.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from Objet where "+idOb+"=idOb");
        rs.next();
        rs.close();
    }

    public void modifierObjet( Objet o)throws SQLException{
        // UPDATE table SET colonne_1 = 'valeur 1', colonne_2 = 'valeur 2', colonne_3 = 'valeur 3' WHERE condition
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE OBJET SET nomOb = ?,descriptionOb = ?,idCat = ? where "+o.getIdentifiant()+"=idOb");
        ps.setString(1, o.getNom());
        ps.setString(2, o.getDescription());
        ps.setInt(3, o.getCategorie().getIdentifiant());
        ps.executeUpdate();
    }

    public Objet rechercherObjetParNum(int idOb)throws SQLException{
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("select * from Objet where "+idOb+"=idOb");
        rs.next();
        CategorieBD categorieBD = new CategorieBD(connexMySQL);
        UtilisateurBD utilisateurBD = new UtilisateurBD(connexMySQL);
        Objet o = new Objet(rs.getInt(1), rs.getString(2),  rs.getString(3), categorieBD.rechercherCategorieParNum(rs.getInt(4)) , utilisateurBD.rechercherUtilisateurParNum(rs.getInt(5)));
        rs.close();
        return o;
    }

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

