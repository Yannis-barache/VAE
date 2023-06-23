import javafx.beans.property.ListProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class ControleurSupImageCrea implements EventHandler<ActionEvent>{

    /**
     * La liste des photos
     */
    List<Map<String,String>> listePhoto;

    /**
     * L'indice de la photo
     */
    int i;

    /**
     * La fenetre de creation de vente
     */
    FenetreCreationVente fenetreCreationVente;


    /**
     * Constructeur ControleurSupImage
     * @param listePhoto La liste des photos
     * @param i L'indice de la photo
     * @param fenetreCreationVente La fenetre de creation de vente
     */

    public ControleurSupImageCrea(List<Map<String,String>> listePhoto, int i, FenetreCreationVente fenetreCreationVente){
        this.listePhoto=listePhoto;
        this.i=i-1;
        this.fenetreCreationVente=fenetreCreationVente;
    }

    /**
     * Methode servant a gerer les actions sur les boutons de la fenêtre de creation de vente
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if (this.listePhoto.size()>0) {
            this.listePhoto.remove(this.i);
            this.fenetreCreationVente.setNbPics(listePhoto.size());
        }
        this.fenetreCreationVente.majNomImage();
        
    }

}
