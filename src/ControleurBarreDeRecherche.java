import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import java.sql.SQLException;

import javax.swing.filechooser.FileNameExtensionFilter;

public class ControleurBarreDeRecherche implements EventHandler<KeyEvent> {
    private ApplicationVAE appli;
    private TextField barreDeRecherche;
    private FenetreManageUsers fenetreManageUsers;

    public ControleurBarreDeRecherche(ApplicationVAE appli,TextField barreDeRecherche,FenetreManageUsers fenetreManageUsers) {
        this.appli=appli;
        this.barreDeRecherche=barreDeRecherche;
        this.fenetreManageUsers=fenetreManageUsers;
    }

    @Override
    public void handle(KeyEvent keyEvent){
        String saisie = this.barreDeRecherche.getText();
        this.fenetreManageUsers.setListeUsers(this.appli.getUtilisateurBD().rechercheUtilisateursParChaine(saisie));
        this.fenetreManageUsers.majAffichage();


        
    }

    
}
