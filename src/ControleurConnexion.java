import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.SQLException;


public class ControleurConnexion implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FenetreConnexion fenetreConnexion;

    public ControleurConnexion(ApplicationVAE appli, FenetreConnexion fenetreConnexion) {
        this.appli=appli;
        this.fenetreConnexion=fenetreConnexion;

    }

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
