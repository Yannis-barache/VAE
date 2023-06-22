import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


/**
 * ControleurBarreDeRecherche est la classe qui represente le controleur de la barre de recherche
 */
public class ControleurBarreDeRecherche implements EventHandler<KeyEvent> {

    /**
     * L'application
     */
    private ApplicationVAE appli;

    /**
     * La barre de recherche de la fenetre de gestion des utilisateurs
     */
    private TextField barreDeRecherche;

    /**
     * La fenetre de gestion des utilisateurs
     */
    private FenetreManageUsers fenetreManageUsers;

    /**
     * Constructeur ControleurBarreDeRecherche
     * @param appli L'application
     * @param barreDeRecherche La barre de recherche
     * @param fenetreManageUsers La fenetre de gestion des utilisateurs
     */
    public ControleurBarreDeRecherche(ApplicationVAE appli,TextField barreDeRecherche,FenetreManageUsers fenetreManageUsers) {
        this.appli=appli;
        this.barreDeRecherche=barreDeRecherche;
        this.fenetreManageUsers=fenetreManageUsers;
    }

    /**
     * Methode servant a gerer les actions sur la barre de recherche
     * @param keyEvent L'evenement qui se produit lorsqu'on tape sur une touche
     */
    @Override
    public void handle(KeyEvent keyEvent){
        String saisie = this.barreDeRecherche.getText();
        this.fenetreManageUsers.setListeUsers(this.appli.getUtilisateurBD().rechercheUtilisateursParChaine(saisie));
        this.fenetreManageUsers.majAffichage();
        
    }

    
}
