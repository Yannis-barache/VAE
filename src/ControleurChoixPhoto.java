import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;


public class ControleurChoixPhoto implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FileChooser fileChooser;

    public ControleurChoixPhoto(ApplicationVAE appli, FileChooser fileChooser) {
        this.appli=appli;
        this.fileChooser=fileChooser;

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
            System.out.println(fichierChoisi.getAbsolutePath());
            // this.appli.getPhotoBD().insererPhoto(new Photo(fichierChoisi.getName(), fichierChoisi.getAbsolutePath()), 0);
        }

    }
}

