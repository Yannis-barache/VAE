import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * ControleurChoixPhoto est la classe qui represente le controleur du choix de photo
 */
public class ControleurChoixPhoto implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * La fenetre de creation de vente
     */
    FenetreCreationVente fenetreCreationVente;

    /**
     * Le fileChooser permettant de choisir une photo
     */
    FileChooser fileChooser;

    /**
     * La liste des photos
     */
    List<Map<String, String>> listePhoto;

    /**
     * La fenetre d'edition de vente
     */
    FenetreEditionVente fenetreEditionVente;


    /**
     * Constructeur ControleurChoixPhoto
     * @param appli L'application
     * @param creationVente La fenetre de creation de vente
     * @param fileChooser Le fileChooser permettant de choisir une photo
     * @param listePhoto La liste des photos
     */
    public ControleurChoixPhoto(ApplicationVAE appli,FenetreCreationVente creationVente, FileChooser fileChooser,List<Map<String, String>> listePhoto) {
        this.appli=appli;
        this.fenetreCreationVente = creationVente;
        this.fileChooser=fileChooser;
        this.listePhoto=listePhoto;
        this.fenetreEditionVente=null;

    }

    /**
     * Constructeur ControleurChoixPhoto
     * @param appli L'application
     * @param fenetreEditionVente La fenetre d'edition de vente
     * @param fileChooser Le fileChooser permettant de choisir une photo
     * @param listePhoto La liste des photos
     */
    public ControleurChoixPhoto(ApplicationVAE appli, FenetreEditionVente fenetreEditionVente, FileChooser fileChooser, List<Map<String, String>> listePhoto) {
        this.appli=appli;
        this.fenetreEditionVente = fenetreEditionVente;
        this.fileChooser=fileChooser;
        this.listePhoto=listePhoto;
        this.fenetreCreationVente = null;

    }


    /** L'action consiste à ajouter une photo à la liste des photos et la lier à l'objet dans la base de données
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent){
        Stage stageFichier = new Stage();
        File fichierChoisi = fileChooser.showOpenDialog(stageFichier);

        // Si on crée une vente
        if (fichierChoisi != null && this.listePhoto.size()<4 && this.fenetreCreationVente != null) {
            String chemin = "file:"+fichierChoisi.getAbsolutePath();
            this.listePhoto.add(Map.of(fichierChoisi.getName(), chemin));
            System.out.println(this.listePhoto);
            this.fenetreCreationVente.setNbPics(this.listePhoto.size());
            this.fenetreCreationVente.majNomImage();
        }

        // Si on édite une vente
        if (fichierChoisi!= null && this.listePhoto.size()<4 && this.fenetreEditionVente != null) {

            System.out.println(fichierChoisi.getName());
            String chemin = "file:"+fichierChoisi.getAbsolutePath();
            try{
                Objet objet = this.fenetreEditionVente.getVente().getObjet();
                this.listePhoto.add(Map.of(fichierChoisi.getName(), chemin));
                this.appli.getPhotoBD().insererPhoto(objet,new Photo(fichierChoisi.getName(), new Image(chemin)));
                this.fenetreEditionVente.setNbPics(this.listePhoto.size());
                this.fenetreEditionVente.majNomImage();

            } catch (SQLException ex){
                System.out.println("Impossible !!!");
            }
            
            
            this.fenetreEditionVente.setNbPics(this.listePhoto.size());
            
            System.out.println(this.listePhoto.size());
        }


    }
}

