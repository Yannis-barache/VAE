import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class ControleurChoixPhoto implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FileChooser fileChooser;
    List<Map<String, String>> listePhoto;


    public ControleurChoixPhoto(ApplicationVAE appli, FileChooser fileChooser,List<Map<String, String>> listePhoto) {
        this.appli=appli;
        this.fileChooser=fileChooser;
        this.listePhoto=listePhoto;

    }

    /** L'action consiste à changer le fichier pris en compte pour le dictionnaire
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent){
        Stage stageFichier = new Stage();
        File fichierChoisi = fileChooser.showOpenDialog(stageFichier);
        if (fichierChoisi != null) {
            System.out.println(fichierChoisi.getName());
            String chemin = "file:"+fichierChoisi.getAbsolutePath();
            this.listePhoto.add(Map.of(fichierChoisi.getName(), chemin));
            System.out.println(this.listePhoto);
        }

    }
}

