import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.sql.SQLException;


/**
 * ControleurAdminVente est la classe qui represente le controleur de la fenetre de gestion des ventes
 */
public class ControleurAdminVente implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * La vente
     */
    Vente vente;

    /**
     * Constructeur ControleurAdminVente
     * @param appli L'application
     * @param vente La vente a gerer
     */
    public ControleurAdminVente(ApplicationVAE appli, Vente vente) {
        this.appli=appli;
        this.vente=vente;
    }

    /**
     * Methode servant a gerer les actions sur les boutons de la fenêtre de gestion des ventes
     * @param actionEvent L'evenement
     */
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