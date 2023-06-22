import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.sql.SQLException;


/**
 * ControleurAdminUser est la classe qui represente le controleur de la fenetre permettant de gerer les utilisateurs
 */
public class ControleurAdminUser implements EventHandler<ActionEvent>{

    /**
     * L'application vae
     */
    ApplicationVAE appli;

    /**
     * L'utilisateur
     */
    Utilisateur user;


    /**
     * Constructeur ControleurAdminUser
     * @param appli l'application vae
     * @param user l'utilisateur
     */
    public ControleurAdminUser(ApplicationVAE appli, Utilisateur user) {
        this.appli=appli;
        this.user=user;
    }


    /**
     * Methode permettant de gerer les actions sur les boutons de la fenêtre de gestion des utilisateurs
     * @param actionEvent l'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();
        String buttonName = button.getText();
        if (buttonName.equals("Supprimer")) {
            // try {
            //    this.appli.getUtilisateurBD().supprimerUtilisateur(this.user);
            // } catch (SQLException e) {
            //   System.out.println("Erreur lors de la suppression de l'utilisateur");
            // }
            System.out.println("Supprimer");

        } else if (buttonName.equals("Désactiver")) {
            try {
             this.appli.getUtilisateurBD().desactiverUtilisateur(this.user);
            } catch (SQLException e) {
               e.printStackTrace();
            }
            System.out.println("Désactiver");

        } else if (buttonName.equals("Activer")) {
            try {
               this.appli.getUtilisateurBD().activerUtilisateur(this.user);
            } catch (SQLException e) {
               e.printStackTrace();
            }
            System.out.println("Activer");
        }

        this.appli.fenetreManageUsers();
    }
}