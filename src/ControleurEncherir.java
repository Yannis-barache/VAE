import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ControleurEncherir implements EventHandler<ActionEvent>{
    private ApplicationVAE appli;
    private FenetreEnchere fenetreEnchere;

    public ControleurEncherir(ApplicationVAE appli,FenetreEnchere fenetreEnchere) {
        this.appli=appli;
        this.fenetreEnchere = fenetreEnchere;
    }

    public boolean newBidVerif(String newB,int oldB) {
        if (Integer.parseInt(newB) > oldB) return true;
        return false;
    }


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

