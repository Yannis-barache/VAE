import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


/**
 * ControleurRetourMesVentes est la classe qui represente le controleur de la fenetre de mes ventes
 */
public class ControleurRetourMesVentes implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * Constructeur ControleurRetourMesVentes
     * @param appli L'application
     */
    public ControleurRetourMesVentes(ApplicationVAE appli) {
        this.appli=appli;

    }

    /**
     * Methode servant a gerer les actions sur les boutons de la fenetre de mes ventes
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        try{
            Button bouton = (Button) actionEvent.getSource();
            if (bouton.getText().equals("Annuler")){
                this.appli.fenetreMesVentes();
            }
            if(bouton.getText().equals("Supprimer")){
                this.appli.fenetreMesVentes();
            }
            if(bouton.getText().equals("Mettre en ligne")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Création de vente");
                alert.setHeaderText("Création de la vente réussi");
                alert.setContentText("Votre vente a bien été mis en ligne");
                alert.showAndWait();
                this.appli.fenetreMesVentes();
            }
        } catch (ClassCastException ex){
            this.appli.fenetreMesVentes();
        }
        
    }
}
