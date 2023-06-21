import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


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

            LocalDate dateFin = this.fenetreCreate.getEndSale();
            LocalDate dateDebut = this.fenetreCreate.getStartSale();
    
            String categorie = this.fenetreCreate.getCategorySale();

            String heureDebut = this.fenetreCreate.getHeureDebut();
            String heureFin = this.fenetreCreate.getHeureFin();
            int statut = 2;

            String jourDeb = String.valueOf(dateDebut.getDayOfMonth());
            String moisDeb = String.valueOf(dateDebut.getMonthValue());
            String anneeDeb = String.valueOf(dateDebut.getYear());

        

            if (moisDeb.length()==1){
            moisDeb="0"+moisDeb;
            }

            if (jourDeb.length()==1){
                jourDeb="0"+jourDeb;
            }



            String dateDeb= jourDeb+"/"+moisDeb+"/"+anneeDeb;
            String deb = dateDeb+":"+heureDebut+":00";


            String jourFin = String.valueOf(dateFin.getDayOfMonth());
            String moisFin = String.valueOf(dateFin.getMonthValue());
            String anneeFin = String.valueOf(dateFin.getYear());

            if (moisFin.length()==1){
                moisFin="0"+moisFin;
            }

            if (jourFin.length()==1){
                jourFin="0"+jourFin;
            }

            String dateF= jourFin+"/"+moisFin+"/"+anneeFin;
            String fin = dateF+":"+heureFin+":00";
            try{
                List<Categorie> categories = this.appli.getCategorieBD().listeCategories();
                int idCategorie = 0;
                for(Categorie c : categories){
                    if(c.getNom().equals(categorie)){
                        idCategorie = c.getIdentifiant();
                    }
                }

                if (dateDebut.isAfter(LocalDate.now())){
                    statut=1;
                }

                Objet objet = new Objet(titre, description,this.appli.getCategorieBD().rechercherCategorieParNum(idCategorie), this.appli.getUtilisateur());
                this.appli.getObjetBD().insererObjet(objet);

                Vente vente = new Vente(Integer.parseInt(prixBase), Integer.parseInt(prixMin),    deb   ,   fin  ,this.appli.getStatutBD().rechercherStatutParNum(statut),objet);
                this.appli.getVenteBD().insererVente(vente);
                vente.setDebut(this.fenetreCreate.getStartDateTime());
                vente.setFin(this.fenetreCreate.getEndDateTime());
                this.fenetreCreate.setAlertErreur("Vente créée avec succès");

            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }

        } catch (NullPointerException e){
            this.fenetreCreate.setAlertErreur("Veuillez remplir tous les champs");

        }

        



        


    
            

    }
}