import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ControleurRecherche implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FenetreAccueil fenetreAccueil;

    public ControleurRecherche(ApplicationVAE appli,FenetreAccueil fenetreAccueil) {
        this.appli=appli;
        this.fenetreAccueil = fenetreAccueil;
    }

    @Override
    public void handle(ActionEvent actionEvent) {      
        String keyWord = fenetreAccueil.getRecherche();
        String priceMax = fenetreAccueil.getMontantMax();
        String category = fenetreAccueil.getCategorie();
        LocalDate date = fenetreAccueil.getDateMax();

        List<Vente> resultRe = new ArrayList<>();
        if (keyWord.length()>0) {
            try {
                resultRe = this.appli.getVenteBD().VentesCommencantPar(keyWord);
            }
            catch(SQLException ex) {}
        }

        System.out.println(resultRe);

        List<Vente> resultCa = new ArrayList<>();
        if (!category.equals("Toutes catégories")) {
            try {
                resultCa = this.appli.getVenteBD().VentesParCategorie(category);
            }
            catch(SQLException ex) {}
        }


        fenetreAccueil.setResult(resultRe);


        
    }
}