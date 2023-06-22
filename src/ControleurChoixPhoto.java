import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class ControleurChoixPhoto implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FenetreCreationVente fenetreCreationVente;
    FileChooser fileChooser;
    List<Map<String, String>> listePhoto;
    FenetreEditionVente fenetreEditionVente;


    public ControleurChoixPhoto(ApplicationVAE appli,FenetreCreationVente creationVente, FileChooser fileChooser,List<Map<String, String>> listePhoto) {
        this.appli=appli;
        this.fenetreCreationVente = creationVente;
        this.fileChooser=fileChooser;
        this.listePhoto=listePhoto;
        this.fenetreEditionVente=null;

    }

    public ControleurChoixPhoto(ApplicationVAE appli, FenetreEditionVente fenetreEditionVente, FileChooser fileChooser, List<Map<String, String>> listePhoto) {
        this.appli=appli;
        this.fenetreEditionVente = fenetreEditionVente;
        this.fileChooser=fileChooser;
        this.listePhoto=listePhoto;
        this.fenetreCreationVente = null;

    }

    /** L'action consiste à changer le fichier pris en compte pour le dictionnaire
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent){
        Stage stageFichier = new Stage();
        File fichierChoisi = fileChooser.showOpenDialog(stageFichier);
        if (fichierChoisi != null && this.listePhoto.size()<4 && this.fenetreCreationVente != null) {
            String chemin = "file:"+fichierChoisi.getAbsolutePath();
            this.listePhoto.add(Map.of(fichierChoisi.getName(), chemin));
            System.out.println(this.listePhoto);
            this.fenetreCreationVente.setNbPics(this.listePhoto.size());
            this.fenetreCreationVente.majNomImage();
        }

        if (fichierChoisi!= null && this.listePhoto.size()<4 && this.fenetreEditionVente != null) {
            System.out.println(fichierChoisi.getName());
            String chemin = "file:"+fichierChoisi.getAbsolutePath();
            try{
                Objet objet = this.fenetreEditionVente.getVente().getObjet();
                this.appli.getPhotoBD().insererPhoto(objet,new Photo(fichierChoisi.getName(), new Image(chemin)));

            } catch (SQLException ex){
                System.out.println("Impossible !!!");
            }
            
            
            this.fenetreEditionVente.setNbPics(this.listePhoto.size());
            
            System.out.println(this.listePhoto.size());
        }


    }
}

