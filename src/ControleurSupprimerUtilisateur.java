import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;


/**
 * ControleurSupprimerUtilisateur est la classe qui represente le controleur de la suppression d'un utilisateur
 */
public class ControleurSupprimerUtilisateur implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * La fenetre mon profil
     */
    FenetreMonProfil fenetreMonProfil;

    /**
     * Constructeur ControleurSupprimerUtilisateur
     * @param appli L'application
     * @param fenetreMonProfil La fenetre mon profil
     */
    public ControleurSupprimerUtilisateur(ApplicationVAE appli, FenetreMonProfil fenetreMonProfil) {
        this.appli=appli;
        this.fenetreMonProfil=fenetreMonProfil;

    }

    /**
     * Methode servant à gerer les actions sur le bouton supprimer utilisateur
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer votre profil ?");
        alert.setContentText("Appuyez sur OK pour confirmer");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            //     try {
            //         this.appli.getVenteBD().supprimerVente(this.fenetreEdit.getVente());
            //         this.appli.getObjetBD().supprimerObjet(this.fenetreEdit.getVente().getObjet());
            //     } catch (SQLException e) {
            //         System.out.println("Erreur SQL : " + e.getMessage());
            //     }
            System.out.println("Fausse suppression effectuée");
            this.appli.fenetreConnexion();
        } else{
            this.appli.fenetreMonProfil();
        }

    }

}
