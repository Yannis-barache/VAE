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


public class FenetreManageUsers extends VBox {
    ApplicationVAE appli;
    Label alerte;
    List<Utilisateur> utilisateurs;
    TextField barreDeRecherche;

    public FenetreManageUsers(ApplicationVAE appli){
        super();
        this.appli = appli;
        this.setPadding(new Insets(10, 200, 10, 200));
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setEffect(new DropShadow());
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.alerte = new Label();
        this.utilisateurs = new ArrayList<Utilisateur>();
        this.barreDeRecherche = new TextField();
        this.barreDeRecherche.setPromptText("Rechercher un utilisateur");
        this.barreDeRecherche.setPrefWidth(200);
        this.barreDeRecherche.setMaxWidth(200);
        this.barreDeRecherche.setMinWidth(200);
        this.barreDeRecherche.setAlignment(Pos.CENTER);
        this.content();
    }


    /**
     * Création du contenu de la fenêtre de gestion des utilisateurs
     */
    private void content() {

        
        barreDeRecherche.setOnKeyTyped(new ControleurBarreDeRecherche(this.appli, barreDeRecherche,this));
        this.getChildren().add(new Label("Gestion des utilisateurs"));
        this.getChildren().add(barreDeRecherche);
        

        for (Utilisateur user : utilisateurs) {
            GridPane userContent = new GridPane();
            userContent.setAlignment(Pos.CENTER);
            userContent.setPadding(new Insets(10, 10, 10, 10));
            userContent.setStyle("-fx-background-color: #FFFFFF;");
            userContent.setEffect(new DropShadow());
            userContent.setHgap(10);
            userContent.setVgap(10);


            ImageView image = new ImageView(new Image("file:src/images/vae2.png"));
            image.setFitHeight(50);
            image.setFitWidth(50);
            Label pseudo = new Label(user.getPseudo());
            Label mail = new Label(user.getMail());
            Button delete = new Button("Supprimer");
            Button desactivate = new Button("Désactiver");
            Button activate = new Button("Activer");

            if (user.isActif()) {
                activate.setDisable(true);
            } else {
                desactivate.setDisable(true);
            }

            delete.setOnAction(new ControleurAdminUser(this.appli, user ));
            desactivate.setOnAction(new ControleurAdminUser(this.appli, user ));
            activate.setOnAction(new ControleurAdminUser(this.appli, user ));


            userContent.add(image, 0, 0, 2, 2);
            userContent.add(pseudo, 2, 0);
            userContent.add(mail, 2, 1);
            userContent.add(delete, 0, 2);
            userContent.add(desactivate, 2, 2);
            userContent.add(activate, 3, 2);
            userContent.add(this.alerte, 0, 3 );

            this.getChildren().add(userContent);

        }

        if (this.utilisateurs.size() == 0) {
            this.getChildren().add(new Label("Aucun utilisateur trouvé"));
        }




    }

    /**
     * Permet de changer le texte de l'alerte
     * @param alerte le texte de l'alerte
     */
    public void setAlerte(String alerte){
        this.alerte.setText(alerte);
    }


    public void setListeUsers(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public void majAffichage() {
        this.getChildren().clear();
        this.content();
    }
}
