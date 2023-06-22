import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ControleurInsererVente implements EventHandler<ActionEvent>{
    ApplicationVAE appli;
    FenetreCreationVente fenetreCreate;

    public ControleurInsererVente(ApplicationVAE appli, FenetreCreationVente fenetreCrea) {
        this.appli=appli;
        this.fenetreCreate=fenetreCrea;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try{
            String titre = this.fenetreCreate.getTitleSale().getText();

            String description = this.fenetreCreate.getDescSale().getText();

            String prixBase = this.fenetreCreate.getBasePriceSale().getText();
            String prixMin = this.fenetreCreate.getMinPriceSale().getText();

            
            LocalDate dateFin = this.fenetreCreate.getEndSale().getValue();
            LocalDate dateDebut = this.fenetreCreate.getStartSale().getValue();
    
            String categorie = this.fenetreCreate.getCategorySale();

            String heureDebut = String.valueOf(this.fenetreCreate.getHeureDebut());
            String heureFin = String.valueOf(this.fenetreCreate.getHeureFin());
            int statut = 2;

            String jourDeb = String.valueOf(dateDebut.getDayOfMonth());
            String moisDeb = String.valueOf(dateDebut.getMonthValue());
            String anneeDeb = String.valueOf(dateDebut.getYear());

            Button bouton = (Button) actionEvent.getSource();
            if(bouton.getText().equals("Mettre en ligne")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Création de vente");
                alert.setHeaderText("Création de la vente réussi");
                alert.setContentText("Votre vente a bien été mis en ligne");
                alert.showAndWait();
                this.appli.fenetreMesVentes();
            }

            if (moisDeb.length()==1){
            moisDeb="0"+moisDeb;
            }

            if (jourDeb.length()==1){
                jourDeb="0"+jourDeb;
            }

            if (heureDebut.length()==1){
                heureDebut="0"+heureDebut;
            }

            if (heureFin.length()==1){
                heureFin="0"+heureFin;
            }


            

            // if (heureDebut.substring(0,1).equals("0")){
            //     heureDebut="00"+heureDebut.substring(3,4);
            // }


            // System.out.println("------------------- test -------------------");
            // System.out.println(heureDebut.substring(0, 1));
            // System.out.println(heureDebut.substring(0, 2));
            // System.out.println(heureDebut.substring(0, 3));
            // System.out.println(heureDebut.substring(0, 4));
            // System.out.println(heureDebut);


            if (!this.fenetreCreate.getCategorySale().equals("")){
                // On récupère la date et l'heure de début
            LocalDate date = this.fenetreCreate.getStartSale().getValue(); // Supposons que cela renvoie un objet LocalDate
            LocalTime time = LocalTime.of(this.fenetreCreate.getHeureDebut(), this.fenetreCreate.getMinuteDebut());

            // Combinez LocalDate et LocalTime en LocalDateTime
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            // Affichez la date et l'heure au format 24 heures en français
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String formattedDateTimeDebut = dateTime.format(formatter);
            System.out.println(formattedDateTimeDebut);
            formattedDateTimeDebut=formattedDateTimeDebut+":00";
            formattedDateTimeDebut=formattedDateTimeDebut.replace(" ",":");
            System.out.println(formattedDateTimeDebut);
            String deb = formattedDateTimeDebut;


            // On récupère la date et l'heure de fin
            LocalDate date2 = this.fenetreCreate.getEndSale().getValue(); // Supposons que cela renvoie un objet LocalDate
            LocalTime time2 = LocalTime.of(this.fenetreCreate.getHeureFin(), this.fenetreCreate.getMinuteFin());

            // Combinez LocalDate et LocalTime en LocalDateTime
            LocalDateTime dateTime2 = LocalDateTime.of(date2, time2);
            // Affichez la date et l'heure au format 24 heures en français
            String formattedDateTimeFin = dateTime2.format(formatter);
            formattedDateTimeFin=formattedDateTimeFin+":00";
            formattedDateTimeFin=formattedDateTimeFin.replace(" ",":");
            System.out.println(formattedDateTimeFin);
            String fin = formattedDateTimeFin;



            String jourFin = String.valueOf(dateFin.getDayOfMonth());
            String moisFin = String.valueOf(dateFin.getMonthValue());
            String anneeFin = String.valueOf(dateFin.getYear());

            LocalDateTime maintenant = LocalDateTime.now();
            String heureMinute = String.valueOf(maintenant.getHour())+ ":" + String.valueOf(maintenant.getMinute());
           

            if (jourFin.length()==1){
                jourFin="0"+jourFin;
            }


            if (heureMinute.compareTo(heureDebut)>0 && jourDeb.equals(String.valueOf(maintenant.getDayOfMonth()))){
                this.fenetreCreate.setAlertErreur("L'heure de début doit être supérieure à l'heure actuelle");
            }
            else{
                if (Valide.differentHeure(deb,fin)){
                    try{
                        List<Categorie> categories = this.appli.getCategorieBD().listeCategories();
                        int idCategorie = 0;
                        for(Categorie c : categories){
                            if(c.getNom().equals(categorie)){
                                idCategorie = c.getIdentifiant();
                            }
                        }
        
                        if (this.fenetreCreate.getStartDateTime().isAfter(LocalDateTime.now())){
                            statut=1;
                        }

        
                        Objet objet = new Objet(titre, description,this.appli.getCategorieBD().rechercherCategorieParNum(idCategorie), this.appli.getUtilisateur());
                        this.appli.getObjetBD().insererObjet(objet);
                        for (Map<String,String> photo: this.fenetreCreate.getListePhotos()){
                            for (String nom : photo.keySet()){
                                this.appli.getPhotoBD().insererPhoto(objet, new Photo(nom, photo.get(nom)));
                            }
                        }
                        for(Photo p : objet.getLesPhotos()){
                            System.out.println(p.getChemin());
                        }
                        System.out.println(objet.getLesPhotos());


        
                        Vente vente = new Vente(Integer.parseInt(prixBase), Integer.parseInt(prixMin),    deb   ,   fin  ,this.appli.getStatutBD().rechercherStatutParNum(statut),objet);
                        this.appli.getVenteBD().insererVente(vente);


                        vente.setDebut(this.fenetreCreate.getStartDateTime());
                        vente.setFin(this.fenetreCreate.getEndDateTime());
                        this.fenetreCreate.setAlertErreur("Vente créée avec succès");
        
                    } catch(SQLException ex){
                        this.fenetreCreate.setAlertErreur(ex.getMessage());
                        System.out.println(ex.getMessage());
                    }
                }
                else{
                    this.fenetreCreate.setAlertErreur("Les dates ne doivent pas être identiques");
                }
    
            } 
            }
            else{
                this.fenetreCreate.setAlertErreur("Veuillez choisir une catégorie");
            }
            
            }
            catch (NullPointerException e){
                this.fenetreCreate.setAlertErreur("Veuillez remplir tous les champs");
    
            }
            catch (Exception e){
                this.fenetreCreate.setAlertErreur("Veuillez remplir tous les champs");
    
            }

            


        
            

    }
}