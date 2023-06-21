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
                this.appli.getUtilisateurBD().insererUtilisateur(new Utilisateur(pseudo, mail, mdp1, true, false));
                this.appli.setNotifReussie("Inscription réussie, veuillez vous connecter");
                this.appli.fenetreConnexion();

            } catch(SQLException ex){
                if(ex.getErrorCode()==1062){
                    this.fenetreInscription.setAlertLogin("Pseudo déjà utilisé. Veuillez en choisir un autre");
                }
                else{
                    this.fenetreInscription.setAlertLogin("Erreur lors de l'insertion, veuillez réessayer");
                }

            }
            
        }

    }
}