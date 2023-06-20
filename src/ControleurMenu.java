import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.SQLException;


public class ControleurMenu implements EventHandler<ActionEvent>{
    
    ApplicationVAE appli;

    public ControleurMenu(ApplicationVAE appli) {
        this.appli=appli;

    }

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
