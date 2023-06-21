import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.sql.SQLException;


public class ControleurAdminVente implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    Vente vente;

    public ControleurAdminVente(ApplicationVAE appli, Vente vente) {
        this.appli=appli;
        this.vente=vente;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();
        String buttonName = button.getText();
        if (buttonName.equals("Supprimer")) {
            // try{
            //     this.appli.getVenteBD().supprimerVente(this.vente);
            // } catch (SQLException e) {
            //     System.out.println("Erreur lors de la suppression de la vente");
            // }
            System.out.println("Supprimer");

        } else if(buttonName.equals("Modifier")){
            // try{
            //     this.appli.fenetreModifierVente(this.vente);
            // } catch (SQLException e) {
            //     System.out.println("Erreur lors de la modification de la vente");
            // }
            
            System.out.println("Modifier");
        }
    }
}