import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class ControleurSupImage implements EventHandler<ActionEvent>{
    List<Map<String,String>> listePhoto;
    int i;
    FenetreCreationVente fenetreCreationVente;

    public ControleurSupImage(List<Map<String,String>> listePhoto, int i, FenetreCreationVente fenetreCreationVente) {
        this.listePhoto=listePhoto;
        this.i=i-1;
        this.fenetreCreationVente=fenetreCreationVente;




    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.listePhoto.size()>0) {
            this.listePhoto.remove(this.i);
        }
        this.fenetreCreationVente.majNomImage();
        
    }

}
