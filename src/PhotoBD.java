import java.sql.PreparedStatement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;

public class PhotoBD {
    /**
     * La connexion à la base de données
     */
    private ConnexionMySQL connexMySQL;
    /**
     * La requête SQL
     */
    private Statement st;

    /**
     * Constructeur de la classe PhotoBD.
     *
     * @param connexMySQL La connexion à la base de données.
     */
    public PhotoBD(ConnexionMySQL connexMySQL){
        this.connexMySQL=connexMySQL;
    }

    /**
     * Obtient l'identifiant maximum des photo
     * @return l'identifiant maximum des photo
     * @throws SQLException Si une erreur SQL se produit.
     */
    public int numPhotoMax()throws SQLException{
        st= this.connexMySQL.createStatement();
        // execute la requte sql
        ResultSet rs = st.executeQuery("select IFNULL(max(idPh),0) leMax from PHOTO");
        rs.next();
        int res  = rs.getInt(1);
        rs.close();
        return res;
    }
    /**
     * Insère une Photo dans la base de données.
     * @param o la photo à insérer.
     * @throws SQLException Si une erreur SQL se produit.
     */
    public void insererPhoto(Objet o, Photo ph)throws SQLException{
        o.ajoutePhoto(ph);
        int num = this.numPhotoMax()+1;
        File imgfile;
        String res="";
        try{
        if(ph.getChemin().equals(" ")){
            for(int i =5; i<ph.getImg().getUrl().length() ; i++){
                res+=ph.getImg().getUrl().charAt(i);
            }
            System.out.println(res);
            File imgf = new File(res);
            imgfile=imgf;
        }
        else{
            for(int i =5; i<ph.getChemin().length() ; i++){
                res+=ph.getChemin().charAt(i);
            }
            File imgf = new File(res);
            imgfile=imgf;
        }
        FileInputStream fin = new FileInputStream(imgfile);
        PreparedStatement ps = this.connexMySQL.prepareStatement("insert into PHOTO(idph,titreph,imgph,idob) values (?,?,?,?)");
        ps.setInt(1, num);
        ps.setString(2, ph.getTitre());
        ps.setBinaryStream(3, fin , imgfile.length());
        ps.setInt(4, o.getIdentifiant());
        ps.executeUpdate();
        ph.setIdentifiant(num);
        }
        catch(Exception e){System.out.println("try inserer "+e);}
    }

    public void supprimerPhoto(Photo ph)throws SQLException{
        int idph = ph.getIdentifiant();
        st= this.connexMySQL.createStatement();
        ResultSet rs = st.executeQuery("DELETE from PHOTO where "+idph+"=idph");
        rs.next();
        rs.close();
    }

    public void modifierPhoto( Photo ph)throws SQLException{
        PreparedStatement ps = this.connexMySQL.prepareStatement("UPDATE PHOTO SET titreph = ? , imgph = ? where idph="+ph.getIdentifiant());
        File imgF = new File(ph.getChemin());
        try{
        FileInputStream imgIS = new FileInputStream(imgF);
        ps.setString(1, ph.getTitre());
        ps.setBinaryStream(3,imgIS ,(int) imgF.length());
        ps.executeUpdate();
        }
        catch(Exception e){System.out.println(e);}
    }

    public Photo rechercherPhotoParNum(int idph)throws SQLException{
        try {
            st= this.connexMySQL.createStatement();
            ResultSet rs = st.executeQuery("select * from PHOTO where "+idph+"=idph");
            rs.next();
            Blob blob = rs.getBlob(3);
            byte[] res = blob.getBytes(1, (int) blob.length());
            ByteArrayInputStream bis = new ByteArrayInputStream(res);
            BufferedImage bImage2 = ImageIO.read(bis);
            // File test = new File("img/output.jpg"); // permet de creer l'image en local
            // ImageIO.write(bImage2, "jpg", test );
            // System.out.println("image created");
            Image image = SwingFXUtils.toFXImage(bImage2, null);
            Photo ph=new Photo(rs.getInt(1),rs.getString(2),image);
            rs.close();
            return ph;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
    public List<Photo> rechercherPhotosParObjet(Objet o)throws SQLException{
        List<Photo> liste = new ArrayList<>();
        try {
            st= this.connexMySQL.createStatement();
            ResultSet rs = st.executeQuery("select * from PHOTO where "+o.getIdentifiant()+"=idob");
            while(rs.next()){
                Blob blob = rs.getBlob(3);
                byte[] res = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(res);
                BufferedImage bImage2 = ImageIO.read(bis);
                // File test = new File("img/output.jpg"); // permet de creer l'image en local
                // ImageIO.write(bImage2, "jpg", test );
                // System.out.println("image created");
                Image image = SwingFXUtils.toFXImage(bImage2, null);
                Photo ph=new Photo(rs.getInt(1),rs.getString(2),image);
                liste.add(ph);
            }
            rs.close();
            return liste;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }


}