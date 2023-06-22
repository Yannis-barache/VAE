import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.DateFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;


/**
 * ControleurInsererVente est la classe qui represente le controleur de la fenetre de creation de vente
 */
public class ControleurRecherche implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * La fenetre de creation de vente
     */
    FenetreAccueil fenetreAccueil;

    /**
     * Constructeur ControleurInsererVente
     * @param appli L'application
     * @param fenetreAccueil La fenetre d'accueil
     */
    public ControleurRecherche(ApplicationVAE appli,FenetreAccueil fenetreAccueil) {
        this.appli=appli;
        this.fenetreAccueil = fenetreAccueil;
    }

    /**
     * Méthode permettant de faire l'intersection de deux listes
     * @param list1 La première liste
     * @param list2 La deuxième liste
     * @return La liste résultante
     * @param <T> Le type de la liste
     */

    public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();
        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * Methode servant a gerer les actions sur les boutons de la fenêtre de creation de vente
     * @param actionEvent L'evenement
     */

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

        List<Vente> resultCa = new ArrayList<>();
        if (!category.equals("Toutes catégories")) {
            try {
                resultCa = this.appli.getVenteBD().VentesParCategorie(category);
            }
            catch(SQLException ex) {}
        }

        List<Vente> resultPrix = new ArrayList<>();
        if (priceMax.length()>0){
            try{
                resultPrix = this.appli.getVenteBD().VentePrixActuelInf(Integer.valueOf(priceMax));
            }
            catch(SQLException ex) {}
        }

        List<Vente> resDate = new ArrayList<>();
        if (date != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDateTime = date.format(formatter);
            formattedDateTime += ":00:00:00";
            try{
                resDate = this.appli.getVenteBD().VenteDateFinInf(formattedDateTime);
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
        }



        

        System.out.println(resultRe);
        System.out.println(resultCa);



        // public List<Vente> intersection (List<Vente> list1, List<Vente> list2){

        // }



        List<Vente> result = null;
        List<List<Vente>> liste = Arrays.asList(resultRe,resultCa,resultPrix,resDate);
        for (List<Vente> set : liste) {
            if (set.size()!=0){
                if (result==null){
                    result = set;
                } 
                else{
                    result = intersection(result, set);       
                }
            }
        }

        if (result==null){
            result = new ArrayList<>();
        }

        fenetreAccueil.afficheVentes(result);


        
    }
}