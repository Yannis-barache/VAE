import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.SQLException;


/**
 * ControleurMenu est la classe qui represente le controleur du menu
 */
public class ControleurMenu implements EventHandler<ActionEvent>{
    

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * Constructeur ControleurMenu
     * @param appli L'application
     */
    public ControleurMenu(ApplicationVAE appli) {
        this.appli=appli;

    }

    /**
     * Methode servant a gerer les actions sur les boutons du menu
     * @param actionEvent L'evenement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        try{
            Button bouton = (Button) actionEvent.getSource();
            if (bouton.getText().equals("Accueil")){
                this.appli.fenetreAccueil();
            }
            if(bouton.getText().equals("Créer une vente")){
                this.appli.fenetreCreationVente();
            }
            if (bouton.getText().equals("Mes Ventes")){
                this.appli.fenetreMesVentes();
            }
            if (bouton.getText().equals("Mes enchères")){
                this.appli.fenetreMesEncheres();
            }
        } catch (ClassCastException ex){
            this.appli.fenetreMonProfil();
        }
        
    }
}
