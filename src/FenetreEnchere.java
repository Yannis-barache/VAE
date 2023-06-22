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
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FenetreEnchere extends GridPane{
    
    private ApplicationVAE appli;
    private Vente vente;
    private BorderPane precFenetre;
    private TextField newBid;
    private int actualPriceValue;
    private Label alertVerifNewBid;

    public FenetreEnchere(ApplicationVAE appli,Vente vente,BorderPane precFenetre) {
        super();
        this.appli = appli;
        this.vente = vente;
        this.precFenetre = precFenetre;

        this.content();
    }

    private void content() {    

        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        //Titre page
        Label title = new Label("Enchérir");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));  

        //Titre et description de l'enchère
        VBox titleDesc = new VBox();
        Label bidTitle = new Label(this.vente.getObjet().getNom()); //Titre
        bidTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        bidTitle.setTextFill(Color.web("#5D48D7"));
        bidTitle.setPadding(new Insets(30,0,0,0));
        Text bidDesc = new Text(this.vente.getObjet().getDescription()); //Desc
        bidDesc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        bidDesc.setWrappingWidth(600);
        // bidDesc.setTextFill(Color.web("black"));

        titleDesc.getChildren().addAll(bidTitle,bidDesc);

        //Image de l'enchère
        ImageView bidPic = new ImageView(new Image("file:./img/blank.png"));
        bidPic.setFitWidth(440);
        bidPic.setPreserveRatio(true);

        //Informations sur l'enchère
        GridPane bidInformations = new GridPane();
        
        //Enchère actuelle
        Label actualBidLabel = new Label("Enchère actuelle : ");
        actualBidLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        actualBidLabel.setTextFill(Color.web("black"));
        this.actualPriceValue = this.vente.getPrixBase();
        try {
            this.actualPriceValue = this.appli.getVenteBD().derniereEnchere(this.vente).getMontant();
        }
        catch(SQLException ex) {}
        Label actualBid = new Label(String.valueOf(this.actualPriceValue)+" €"); //PRIX ACTUEL
        actualBid.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        actualBid.setTextFill(Color.web("#5D48D7"));
        
        //Votre enchère
        Label ourBidLabel = new Label("Votre enchère : ");
        ourBidLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        ourBidLabel.setTextFill(Color.web("black"));
        int ourPriceValue = 0;
        try {
            Enchere enchere = this.appli.getVenteBD().derniereEnchereUtilisateur(this.vente,this.appli.getUtilisateur());
            if (enchere != null) {
                ourPriceValue = enchere.getMontant();
            }
        }
        catch(SQLException ex) {}
        Label ourBid = new Label(String.valueOf(ourPriceValue)+" €"); //NOTRE ENCHERE
        ourBid.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        ourBid.setTextFill(Color.web("#5D48D7"));  

        //Temps restants
        Label remainTimeLabel = new Label("Temps restant : ");
        remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        remainTimeLabel.setTextFill(Color.web("black"));
        Label remainTime = new Label(this.vente.tempsRestant()); //TEMPS RESTANTS
        remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        remainTime.setTextFill(Color.web("#5D48D7"));  

        //Nouvelle enchère
        VBox newBidContainer = new VBox();
        Label newBidLabel = new Label("Nouvelle enchère");
        newBidLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newBidLabel.setTextFill(Color.web("#5D48D7"));
        newBid = new TextField();
        newBid.setPromptText("Nouvelle enchère");
        newBid.setEffect(ds);
        newBid.setPrefHeight(40);
        newBid.setPrefWidth(350);
        newBid.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newBid.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        newBid.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
        this.alertVerifNewBid = new Label("");
        this.alertVerifNewBid.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        this.alertVerifNewBid.setTextFill(Color.web("#e66954"));
        this.alertVerifNewBid.setPadding(new Insets(20,0,0,0));

        newBidContainer.getChildren().addAll(newBidLabel,newBid,this.alertVerifNewBid);

        //Buttons
        VBox cancelContent = new VBox();
        Button cancel = new Button("Annuler");
        cancel.setEffect(ds);


        if (precFenetre.getClass().getName().equals("FenetreAccueil")) {
            cancel.setOnAction((key) -> this.appli.fenetreAccueil());
        } else {
            cancel.setOnAction((key) -> this.appli.fenetreMesEncheres());
        }

        cancel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        cancel.setPadding(new Insets(10,30,10,30));
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        cancelContent.getChildren().add(cancel);


        VBox sendContent = new VBox(5);
        Button send = new Button("Enchérir");
        send.setEffect(ds);


        send.setOnAction(new ControleurEncherir(this.appli,this)); //SAUVEGARDER L'ENCHERE

        send.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        send.setPadding(new Insets(10,30,10,30));
        send.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        send.setAlignment(Pos.TOP_RIGHT);
        sendContent.getChildren().addAll(send);
        sendContent.setAlignment(Pos.TOP_RIGHT);

        //Placements dans les informations

        bidInformations.setHgap(30);
        bidInformations.setVgap(60);
        bidInformations.add(actualBidLabel,0,0,1,1);
        bidInformations.add(actualBid,1,0,1,1);
        bidInformations.add(ourBidLabel,0,1,1,1);
        bidInformations.add(ourBid,1,1,1,1);
        bidInformations.add(remainTimeLabel,0,2,1,1);
        bidInformations.add(remainTime,1,2,1,1);
        bidInformations.add(newBidContainer,0,3,2,1);

        //Ajout des containers
        //Configuration de la grille
        this.setHgap(100);
        this.setVgap(50);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(50));

        this.add(title,0,0,1,1);
        this.add(titleDesc,0,1,2,1);
        this.add(bidPic,0,3,2,1);
        this.add(bidInformations,2,1,1,3);
        this.add(cancel,0,4,1,1);
        this.add(send,2,4,1,1);
    }

    public Vente getVente() {
        return this.vente;
    }

    public String getNewBid() {
        return this.newBid.getText();
    }

    public int getActualBid() {
        return this.actualPriceValue;
    }

    public Label getAlertEnchere() {
        return this.alertVerifNewBid;
    }
}
