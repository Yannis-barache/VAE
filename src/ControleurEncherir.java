import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * ControleurEncherir est la classe qui represente le controleur de la fenetre d'enchere
 */
public class ControleurEncherir implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    private ApplicationVAE appli;

    /**
     * La fenetre d'enchere
     */
    private FenetreEnchere fenetreEnchere;


    /**
     * Constructeur ControleurEncherir
     * @param appli L'application
     * @param fenetreEnchere La fenetre d'enchere
     */
    public ControleurEncherir(ApplicationVAE appli,FenetreEnchere fenetreEnchere) {
        this.appli=appli;
        this.fenetreEnchere = fenetreEnchere;
    }


    /**
     * Methode servant a verifier si la nouvelle enchere est superieur a l'enchere actuelle
     * @param newB La nouvelle enchere
     * @param oldB L'enchere actuelle
     * @return true si la nouvelle enchere est superieur a l'enchere actuelle, false sinon
     */
    public boolean newBidVerif(String newB,int oldB) {
        return Integer.parseInt(newB) > oldB;
    }


    /**
     * Methode servant à gérer les actions sur les boutons de la fenêtre d'enchère
     * @param actionEvent L'événement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        String newBid = this.fenetreEnchere.getNewBid();
        int actualBid = this.fenetreEnchere.getActualBid();
        if (newBid.length()>0) {
            if (newBidVerif(newBid,actualBid)) {
                //Date actuelle
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
                String dateNow = now.format(formatter);
                System.out.println(dateNow);
                //Nouvelle enchère
                Enchere e = new Enchere(fenetreEnchere.getVente(),this.appli.getUtilisateur(),Integer.parseInt(newBid),dateNow);
                try {
                    this.appli.getEnchereBD().insererEnchere(e);
                    this.appli.fenetreMesEncheres();
                }
                catch(SQLException ex) {System.out.println(ex);}
            } else {
                fenetreEnchere.getAlertEnchere().setText("Veuillez saisir une enchère supérieur à l'enchère actuelle");
            }
        }
        fenetreEnchere.getAlertEnchere().setText("veuillez insérer une valeur");
    }
}

