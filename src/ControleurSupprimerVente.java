import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * ControleurSupprimerVente est la classe qui represente le controleur de la fenetre de suppression de vente
 */
public class ControleurSupprimerVente implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * La fenetre de suppression de vente
     */
    FenetreEditionVente fenetreEdit;

    /**
     * Constructeur ControleurSupprimerVente
     * @param appli L'application
     * @param fenetreEdit La fenetre d'édition de vente
     */
    public ControleurSupprimerVente(ApplicationVAE appli, FenetreEditionVente fenetreEdit) {
        this.appli=appli;
        this.fenetreEdit=fenetreEdit;

    }

    /**
     * Méthode permettant de supprimer une vente
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette vente ?");
        alert.setContentText("Appuyez sur OK pour confirmer");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            try {
                this.appli.getVenteBD().supprimerVente(this.fenetreEdit.getVente());
                this.appli.getObjetBD().supprimerObjet(this.fenetreEdit.getVente().getObjet());
            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
            }
        }
        this.appli.fenetreMesVentes();
    }


}
