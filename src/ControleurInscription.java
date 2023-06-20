import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.SQLException;


public class ControleurInscription implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FenetreInscription fenetreInscription;

    public ControleurInscription(ApplicationVAE appli, FenetreInscription fenetreInscription) {
        this.appli=appli;
        this.fenetreInscription=fenetreInscription;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String pseudo= this.fenetreInscription.getPseudoField().getText();
        String mail = this.fenetreInscription.getMailField().getText();
        String mdp1 = this.fenetreInscription.getPWField1().getText();
        String mdp2 = this.fenetreInscription.getPWField2().getText();

        if (!Valide.memeMDP(mdp1, mdp2)){
            this.fenetreInscription.setAlertLogin("Veuillez rentrez les mêmes mot de passe");
        }
        if (!Valide.emailValide(mail)){
            this.fenetreInscription.setAlertLogin("Veuillez rentrez un mail valide");
        }
        else{
            try{
                this.fenetreInscription.setAlertLogin("Insertion en cours");
                this.appli.getUtilisateurBD().insererUtilisateur(new Utilisateur(45, pseudo, mail, mdp1, true, false));
                this.fenetreInscription.setAlertLogin("Insertion réussie");

            } catch(SQLException ex){

            }
            
        }

    }
}
