import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.SQLException;

import javax.swing.filechooser.FileNameExtensionFilter;

public class ControleurBouton implements EventHandler<ActionEvent> {
    private ApplicationVAE appli;
    private FenetreMonProfil fenetreMonProfil;

    public ControleurBouton(ApplicationVAE appli,FenetreMonProfil fenetreMonProfil) {
        this.appli=appli;
        this.fenetreMonProfil=fenetreMonProfil;
    }

    @Override
    public void handle(ActionEvent actionEvent){
        Button bouton = (Button) actionEvent.getSource();
        if (bouton.getText().equals("Modifier")){
            this.fenetreMonProfil.modeTF();
            this.fenetreMonProfil.getButton().setText("Sauvegarder");
            
        }
        else {
            this.fenetreMonProfil.modeTF();
            this.fenetreMonProfil.getButton().setText("Modifier");

        }

        
    }

    
}
