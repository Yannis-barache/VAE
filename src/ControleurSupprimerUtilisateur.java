import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;


public class ControleurSupprimerUtilisateur implements EventHandler<ActionEvent>{
    
    ApplicationVAE appli;
    FenetreMonProfil fenetreMonProfil;

    public ControleurSupprimerUtilisateur(ApplicationVAE appli, FenetreMonProfil fenetreMonProfil) {
        this.appli=appli;
        this.fenetreMonProfil=fenetreMonProfil;

    }

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
