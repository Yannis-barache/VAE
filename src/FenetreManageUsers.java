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


public class FenetreManageUsers extends VBox {
    ApplicationVAE appli;
    Label alerte;

    public FenetreManageUsers(ApplicationVAE appli){
        super();
        this.appli = appli;
        this.setPadding(new Insets(200, 10, 10, 10));
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setEffect(new DropShadow());
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.alerte = new Label();

        this.content();
    }


    /**
     * Création du contenu de la fenêtre de gestion des utilisateurs
     */
    private void content() {

        this.getChildren().add(new Label("Gestion des utilisateurs"));
        List<Utilisateur> users = new ArrayList<>();
        //try{
        //    users = this.appli.getUtilisateurBD().listeUtilisateurs();
        //}catch (SQLException e){
        //    this.getChildren().add(new Label("Il n'y a pas d'utilisateurs dans la base de données"));
        //}
        users.add(new Utilisateur("pseudo1", "mail1", "mdp1", true, false));
        users.add(new Utilisateur("pseudo2", "mail2", "mdp2", false, true));

        for (Utilisateur user : users) {
            GridPane userContent = new GridPane();
            userContent.setAlignment(Pos.CENTER);
            userContent.setPadding(new Insets(10, 10, 10, 10));
            userContent.setStyle("-fx-background-color: #FFFFFF;");
            userContent.setEffect(new DropShadow());
            userContent.setHgap(10);
            userContent.setVgap(10);


            ImageView image = new ImageView(new Image("file:../img/blank.png"));
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




    }

    /**
     * Permet de changer le texte de l'alerte
     * @param alerte le texte de l'alerte
     */
    public void setAlerte(String alerte){
        this.alerte.setText(alerte);
    }

}
