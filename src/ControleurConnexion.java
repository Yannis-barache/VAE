import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.SQLException;


public class ControleurConnexion implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * La fenetre de connexion
     */
    FenetreConnexion fenetreConnexion;

    /**
     * Constructeur ControleurConnexion
     * @param appli L'application
     * @param fenetreConnexion La fenetre de connexion
     */
    public ControleurConnexion(ApplicationVAE appli, FenetreConnexion fenetreConnexion) {
        this.appli=appli;
        this.fenetreConnexion=fenetreConnexion;

    }

    /**
     * Methode servant a gerer les actions sur les boutons de la fenêtre de connexion
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        String pseudo=fenetreConnexion.getPseudoEntry().getText();
        String motDePasse=fenetreConnexion.getPasswordEntry().getText();
        try {
           
            if(this.appli.getScriptJDBC().connexion(pseudo, motDePasse)){

                this.appli.fenetreAccueil();
            }
            
            else{
                this.fenetreConnexion.setAlertLogin("pseudo ou mot de passe incorrect");
            }
        } catch (SQLException e) {
            this.fenetreConnexion.setAlertLogin("pseudo ou mot de passe incorrect");
            
        }

    }
}
