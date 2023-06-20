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
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreInscription extends BorderPane {
    
    private ApplicationVAE appli;
    private TextField pseudoEntry,mailEntry;
    private PasswordField passwordEntry1,passwordEntry2;
    private Label alertLogin;

   



    public FenetreInscription(ApplicationVAE appli) {
        super();
        this.appli = appli;
        this.alertLogin = new Label();
        alertLogin.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        alertLogin.setTextFill(Color.web("#e66954"));
        this.content();
    }

     private void content() {
        
        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

         //Linear gradient
         Stop[] stops = new Stop[] {
            new Stop(0, Color.web("#5D48B0")),
            new Stop(1, Color.web("#5D48D7")),
         };
        LinearGradient lg = new LinearGradient(1,0,0,1,true,CycleMethod.NO_CYCLE,stops);

        //Rectangle de gauche
        Rectangle leftAside = new Rectangle();
        leftAside.setWidth(300);
        leftAside.setHeight(1080);
        leftAside.setFill(lg);

        //Inscription properties
        VBox entries = new VBox();
        
        //Titre
        Label title = new Label("Créer un compte");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));

        //Pseudo
        VBox pseudoContent = new VBox();
        Label pseudoLabel = new Label("Pseudo");
        pseudoLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        pseudoLabel.setTextFill(Color.web("#5D48D7"));
        this.pseudoEntry = new TextField();
        pseudoEntry.setEffect(ds);
        pseudoEntry.setPrefHeight(40);
        pseudoEntry.setPrefWidth(350);
        pseudoEntry.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        pseudoEntry.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        pseudoContent.setPadding(new Insets(30,0,10,0));
        pseudoContent.getChildren().addAll(pseudoLabel,pseudoEntry);

        //Mail
        VBox mailContent = new VBox();
        Label mailLabel = new Label("Mail");
        mailLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        mailLabel.setTextFill(Color.web("#5D48D7"));
        this.mailEntry=new TextField();
        mailEntry.setEffect(ds);
        mailEntry.setPrefHeight(40);
        mailEntry.setPrefWidth(350);
        mailEntry.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        mailEntry.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        mailContent.setPadding(new Insets(30,0,10,0));
        mailContent.getChildren().addAll(mailLabel,mailEntry);

        //Password
        VBox passwordContent1 = new VBox();
        Label passwordLabel1 = new Label("Mot de passe");
        passwordLabel1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        passwordLabel1.setTextFill(Color.web("#5D48D7"));
        this.passwordEntry1 = new PasswordField();
        passwordEntry1.setEffect(ds);
        passwordEntry1.setPrefHeight(40);
        passwordEntry1.setPrefWidth(350);
        passwordEntry1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        passwordEntry1.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        passwordContent1.setPadding(new Insets(30,0,10,0));
        passwordContent1.getChildren().addAll(passwordLabel1,passwordEntry1);

        //Confirm password
        VBox passwordContent2 = new VBox();
        Label passwordLabel2 = new Label("Confirmer");
        passwordLabel2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        passwordLabel2.setTextFill(Color.web("#5D48D7"));
        this.passwordEntry2 = new PasswordField();
        passwordEntry2.setEffect(ds);
        passwordEntry2.setPrefHeight(40);
        passwordEntry2.setPrefWidth(350);
        passwordEntry2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        passwordEntry2.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        passwordContent2.setPadding(new Insets(30,0,10,0));
        passwordContent2.getChildren().addAll(passwordLabel2,passwordEntry2);
        passwordContent2.setOnKeyReleased(new ControleurInscriptionKey(appli, this));
 

        //Buttons register cancel
        BorderPane buttons = new BorderPane();
        Button cancel = new Button("Annuler");
        cancel.setEffect(ds);
        cancel.setOnAction((key) -> this.appli.fenetreConnexion());
        cancel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        cancel.setPadding(new Insets(10,30,10,30));
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        cancel.setAlignment(Pos.TOP_LEFT);

        Button register = new Button("Créer un compte");
        register.setEffect(ds);
        register.setOnAction(new ControleurInscription(appli, this)); //ICI AJOUTER LES VALEURS A LA BASE DE DONNEE JE SAIS PAS COMMENT FAIRE
        register.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        register.setPadding(new Insets(10,30,10,30));
        register.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));

        buttons.setPadding(new Insets(80,0,0,0));
        buttons.setLeft(cancel);
        buttons.setRight(register);

        entries.getChildren().addAll(title,pseudoContent,mailContent,passwordContent1,passwordContent2,this.alertLogin,buttons);
        entries.setPadding(new Insets(150,0,0,300));


        //Logo à droite
        VBox logoContent = new VBox();
        ImageView logo = new ImageView(new Image("file:./img/vae2.png"));
        logoContent.getChildren().addAll(logo);
        logoContent.setPadding(new Insets(400,100,0,100));

    


        this.setLeft(leftAside);
        this.setCenter(entries);
        this.setRight(logoContent);

        
    }
    public TextField getPseudoField(){
        return this.pseudoEntry;
    }

    public TextField getMailField(){
        return this.mailEntry;
    }

    public PasswordField getPWField1(){
        return this.passwordEntry1;
    }

    public PasswordField getPWField2(){
        return this.passwordEntry2;
    }

    public void setAlertLogin(String message){
        this.alertLogin.setText(message);
    }

}