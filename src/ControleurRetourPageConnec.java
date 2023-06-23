import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * ControleurRetourPageConnec est la classe qui represente le controleur du retour a la page de connexion
 */
public class ControleurRetourPageConnec implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * Constructeur ControleurRetourPageConnec
     * @param appli L'application
     */
    public ControleurRetourPageConnec(ApplicationVAE appli) {
        this.appli=appli;

    }

    /**
     * Methode servant à changer de fenêtre vers la page de connexion
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.appli.fenetreConnexion();
        
    }
}
