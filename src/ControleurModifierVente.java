import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;

import java.sql.SQLException;


public class ControleurModifierVente implements EventHandler<ActionEvent>{

    /**
     * L'application
     */
    ApplicationVAE appli;

    /**
     * La fenetre d'edition de la vente
     */
    FenetreEditionVente fenetreEdit;

    /**
     * Constructeur ControleurModifierVente
     * @param appli L'application
     * @param fenetreEdit La fenetre d'edition de la vente
     */
    public ControleurModifierVente(ApplicationVAE appli, FenetreEditionVente fenetreEdit) {
        this.appli=appli;
        this.fenetreEdit=fenetreEdit;

    }

    /**
     * Methode servant a gerer les actions sur les boutons de la fenêtre d'édition de la vente
     * @param actionEvent L'événement
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        
        if (!Valide.pasAvant(this.fenetreEdit.getVente().getdebutVente(), this.fenetreEdit.getNewEnd())){
            this.fenetreEdit.setAlerte("La date de fin de vente doit être après la date de début de vente");

        }
        else {
            try{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Voulez-vous vraiment modifier cette vente ?");
                alert.setContentText("Appuyez sur OK pour confirmer");
                alert.showAndWait();
                String finVe= fenetreEdit.getNewEnd();
                String titre= fenetreEdit.getNewTitle();
                String desc= fenetreEdit.getNewDesc();
                String categorieString= fenetreEdit.getNewCategory();
    
                Button bouton = (Button) actionEvent.getSource();
                ButtonType buttonClicked = alert.getResult();
                if(bouton.getText().equals("Sauvegarder les modifications") && buttonClicked == ButtonType.OK){
                    this.appli.fenetreMesVentes();
                } else{
                    alert.close();
                }
            
                Categorie categorie = this.appli.getCategorieBD().rechercherCategorieParNom(categorieString);
                this.appli.getObjetBD().modifierObjet(this.fenetreEdit.getVente().getIdentifiant(),titre, desc, categorie);
                this.appli.getVenteBD().modifierVente(this.fenetreEdit.getVente().getIdentifiant() ,finVe);
            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
            }

        }
        


    }
}
