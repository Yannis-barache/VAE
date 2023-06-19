import java.sql.SQLException;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControleurConnecKey implements EventHandler<KeyEvent>{ 
    private ApplicationVAE appli;
    private FenetreConnexion fenetreConnexion;


    public ControleurConnecKey(ApplicationVAE appli, FenetreConnexion fenetreConnexion) {
        this.appli=appli;
        this.fenetreConnexion=fenetreConnexion;
    }

    @Override
    public void handle(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)){
            try{
                String pseudo=fenetreConnexion.getPseudoEntry().getText(); 
                String motDePasse=fenetreConnexion.getPasswordEntry().getText();
                if(this.appli.getScriptJDBC().connexion(pseudo, motDePasse)){
                    this.appli.fenetreAccueil();
                }        
                else{
                    this.fenetreConnexion.setAlertLogin("pseudo ou mot de passe incorrect");
                }
            }
            catch(SQLException ex){
                this.fenetreConnexion.setAlertLogin("pseudo ou mot de passe incorrect");
            }
        } 
    }

    
          
}