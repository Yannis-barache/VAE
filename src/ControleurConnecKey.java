import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * ControleurConnecKey est la classe qui represente le controleur de la fenetre de connexion via le clavier
 */
public class ControleurConnecKey implements EventHandler<KeyEvent>{

    /**
     * L'application
     */
    private ApplicationVAE appli;

    /**
     * La fenetre de connexion
     */
    private FenetreConnexion fenetreConnexion;


    /**
     * Constructeur ControleurConnecKey
     * @param appli L'application
     * @param fenetreConnexion La fenetre de connexion
     */
    public ControleurConnecKey(ApplicationVAE appli, FenetreConnexion fenetreConnexion) {
        this.appli=appli;
        this.fenetreConnexion=fenetreConnexion;
    }


    /**
     * Methode servant à gérer les actions sur la fenêtre de connexion
     * @param e L'évenement qui se produit lorsqu'on tape sur entrée
     */
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