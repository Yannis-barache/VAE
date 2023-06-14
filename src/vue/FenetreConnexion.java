import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ButtonBar.ButtonData ;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreConnexion extends BorderPane {
    
    private ApplicationVAE appli;

    public FenetreConnexion(ApplicationVAE appli) {
        super();
        this.appli = appli;

        this.content();
    }

    private void content() {
        //Rectangle de gauche
        Rectangle leftAside = new Rectangle();
        leftAside.setWidth(300);
        leftAside.setHeight(1080);
        leftAside.setFill(Color.web("#5D48D7"));

        //Centre de la page
        VBox entries = new VBox();

        //Titre
        Label title = new Label("Connectez-vous");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));

        //Pseudo content
        VBox pseudoContent = new VBox();
        Label pseudoLabel = new Label("Identifiant"); 
        pseudoLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        pseudoLabel.setTextFill(Color.web("#5D48D7"));
        TextField pseudoEntry = new TextField();
        pseudoEntry.setPrefHeight(40);
        pseudoEntry.setPrefWidth(350);
        pseudoEntry.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        pseudoEntry.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        pseudoContent.getChildren().addAll(pseudoLabel,pseudoEntry);
        pseudoContent.setPadding(new Insets(50,0,10,0));
        
        //Password content
        VBox passwordContent = new VBox();
        Label passwordLabel = new Label("Mot de passe");
        passwordLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        passwordLabel.setTextFill(Color.web("#5D48D7"));
        PasswordField passwordEntry = new PasswordField();
        passwordEntry.setPrefHeight(40);
        passwordEntry.setPrefWidth(350);
        passwordEntry.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        passwordEntry.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        passwordContent.getChildren().addAll(passwordLabel,passwordEntry);
        passwordContent.setPadding(new Insets(10,0,50,0));

        VBox loginContent = new VBox();
        Button login = new Button("Se connecter");
        login.setOnAction((key) -> appli.fenetreAccueil());
        login.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        login.setPadding(new Insets(10,30,10,30));
        login.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        loginContent.getChildren().add(login);
        loginContent.setAlignment(Pos.TOP_RIGHT);
        loginContent.setPadding(new Insets(20,0,40,0));


        VBox registerContent = new VBox();
        Label registerLabel = new Label("Pas de compte ?");
        registerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Button register = new Button("Créer un compte");
        register.setOnAction((key) -> appli.fenetreRegiser());
        register.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        register.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
        register.setTextFill(Color.web("#5D48D7"));
        registerContent.getChildren().addAll(registerLabel,register);
        registerContent.setAlignment(Pos.TOP_RIGHT);


        entries.getChildren().addAll(title,pseudoContent,passwordContent,loginContent,registerContent);
        entries.setPadding(new Insets(300,50,0,150));

        //Logo à droite
        VBox logoContent = new VBox();
        ImageView logo = new ImageView(new Image("./img/vae2.png"));
        logoContent.getChildren().addAll(logo);
        logoContent.setPadding(new Insets(400,100,0,100));

        this.setLeft(leftAside);
        this.setCenter(entries);
        this.setRight(logoContent);
    }

}
