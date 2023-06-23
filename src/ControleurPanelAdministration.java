import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * ControleurPanelAdministration est la classe qui represente le controleur du panel administration
 */
public class ControleurPanelAdministration implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * Constructeur ControleurPanelAdministration
     * @param appli L'application
     */
    public ControleurPanelAdministration(ApplicationVAE appli) {
        this.appli=appli;

    }

    /**
     * Methode servant à changer de fenêtre vers le panel administration
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.appli.fenetrePanelAdministration();
        
    }
}
