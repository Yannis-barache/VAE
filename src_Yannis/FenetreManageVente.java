import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FenetreManageVente extends VBox {
    ApplicationVAE appli;
    Label alerte;

    public FenetreManageVente(ApplicationVAE appli){
        super();
        this.appli = appli;
        this.alerte = new Label();

        this.content();
    }


    /**
     * Création du contenu de la fenêtre de gestion des utilisateurs
     */
    private void content() {

        this.getChildren().add(new Label("Gestion des Ventes"));
        List<Vente> ventes = new ArrayList<>();
        //try{
        //    users = this.appli.getUtilisateurBD().listeUtilisateurs();
        //    categories = this.appli.getCategorieBD().listeCategories();
        //    objets = this.appli.getObjetBD().listeObjets();
        //    ventes = this.appli.getVenteBD().listeVentes();   
        //}catch (SQLException e){
        //    this.getChildren().add(new Label("Il n'y a pas d'utilisateurs dans la base de données"));
        //}
        Utilisateur user1=new Utilisateur("pseudo1", "mail1", "mdp1", true, false);
        Utilisateur user2=new Utilisateur("pseudo2", "mail2", "mdp2", false, true);
        Objet objet1=new Objet("objet1", "description1",new Categorie("test"), user1);
        Objet objet2=new Objet("objet2", "description2",new Categorie("test"), user2);
        Vente vente1=new Vente(10,20,"2022-12-4 12:27:00", "2022-12-4 12:27:00",new Statut("test"), objet1);
        Vente vente2=new Vente(10,20,"2022-12-4 12:27:00", "2022-12-4 12:27:00",new Statut("test"), objet2);
        ventes.add(vente1);
        ventes.add(vente2);


        for (Vente vente : ventes) {
            GridPane saleContent = new GridPane();
            saleContent.setAlignment(Pos.CENTER);
            saleContent.setPadding(new Insets(10, 10, 10, 10));
            saleContent.setStyle("-fx-background-color: #FFFFFF;");
            saleContent.setEffect(new DropShadow());
            saleContent.setHgap(10);
            saleContent.setVgap(10);


            ImageView image = new ImageView(new Image("file:../img/blank.png"));
            image.setFitHeight(100);
            image.setFitWidth(100);

            Label titre = new Label(vente.getObjet().getNom());
            Label description = new Label(vente.getObjet().getDescription());
            Button delete = new Button("Supprimer");
            Button modify = new Button("Modifier");

            delete.setOnAction(new ControleurAdminVente(this.appli, vente ));
            modify.setOnAction(new ControleurAdminVente(this.appli, vente));

            saleContent.add(image, 0, 0);
            saleContent.add(titre, 1, 0);
            saleContent.add(description, 1, 1);
            saleContent.add(delete, 2, 0);
            saleContent.add(modify, 2, 1);


            this.getChildren().add(saleContent);

        }




    }

    /**
     * Permet de changer le texte de l'alerte
     * @param alerte le texte de l'alerte
     */
    public void setAlerte(String alerte){
        this.alerte.setText(alerte);
    }

}
