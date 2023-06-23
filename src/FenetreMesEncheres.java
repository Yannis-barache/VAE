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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FenetreMesEncheres extends BorderPane {

    private ApplicationVAE appli;
    private List<Enchere> encheres;

    public FenetreMesEncheres(ApplicationVAE appli,List<Enchere> encheres) {
        super();
        this.appli = appli;
        this.encheres = encheres;

        this.content();
    }

    private void content() {

        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        //Enchères
        VBox bidsContent = new VBox(100);

        //Titre
        HBox titleContent = new HBox();
        Label title = new Label("Mes enchères : ");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));
        Label countEnchere = new Label(String.valueOf(this.encheres.size()));
        countEnchere.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        countEnchere.setTextFill(Color.web("black"));
        titleContent.getChildren().addAll(title,countEnchere);
        //Ajout du titre
        bidsContent.getChildren().add(titleContent);

        if (this.encheres.size() > 0) {
            for (Enchere enchere : this.encheres) {
                VBox bidContent = new VBox();
                HBox splitBid = new HBox();

                VBox leftSide = new VBox();

                //image (gauche)
                ImageView encherePic;
                try {
                    List<Photo> liste =this.appli.getPhotoBD().rechercherPhotosParObjet(enchere.getVente().getObjet());
                    System.out.println(enchere.getVente().getObjet().getLesPhotos());
                    for(Photo ph : liste){
                        enchere.getVente().getObjet().ajoutePhoto(ph);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    System.out.println(enchere.getVente().getObjet().getLesPhotos());
                    encherePic = new ImageView(enchere.getVente().getObjet().getLesPhotos().get(0).getImg());
                } catch (Exception e) {
                    System.out.println("erreur");
                    encherePic = new ImageView(new Image("file:./img/blank.png"));
                }
                encherePic.setFitHeight(350);
                encherePic.setPreserveRatio(true);    

                leftSide.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 3 0 0");
                leftSide.setPadding(new Insets(0,30,0,0));
                leftSide.maxWidth(300);
                leftSide.getChildren().addAll(encherePic);

                //Informations (droite)
                GridPane rightSide = new GridPane();

                //Titre
                Label enchereTitle = new Label(enchere.getVente().getObjet().getNom());
                enchereTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                enchereTitle.setTextFill(Color.web("#5D48D7"));     
                enchereTitle.setPadding(new Insets(0,0,0,0));

                //Prix actuel
                Label actualPriceLabel = new Label("Enchère actuel : ");
                actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPriceLabel.setTextFill(Color.web("black"));
                int actualPriceValue = enchere.getVente().getPrixBase();
                try {

                    Enchere e = this.appli.getVenteBD().derniereEnchere(enchere.getVente());
                    if (e!= null){
                        actualPriceValue = e.getMontant();
                    }
                }
                catch(SQLException ex) {}
                Label actualPrice = new Label(String.valueOf(actualPriceValue)+" €"); //Get le prix de la vente
                actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPrice.setTextFill(Color.web("#5D48D7"));  
                actualPrice.setAlignment(Pos.BASELINE_RIGHT);

                //Votre enchère
                Label actualEnchereLabel = new Label("Votre enchère : ");
                actualEnchereLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualEnchereLabel.setTextFill(Color.web("black"));
                int ourPriceValue = 0;
                try {
                    Enchere newEnchere = this.appli.getVenteBD().derniereEnchereUtilisateur(enchere.getVente(),this.appli.getUtilisateur());
                    if (newEnchere != null) {
                        ourPriceValue = newEnchere.getMontant();
                    }
                }
                catch(SQLException ex) {}
                String ourPriceFlex = "";
                if (actualPriceValue == ourPriceValue) {
                    ourPriceFlex = "Vous êtes le meneur !";
                } else {
                    ourPriceFlex = String.valueOf(ourPriceValue)+" €";
                }
                Label actualEnchere = new Label(ourPriceFlex);
                actualEnchere.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualEnchere.setTextFill(Color.web("#5D48D7"));  
                actualEnchere.setAlignment(Pos.BASELINE_RIGHT);

                //Prix de base
                Label basePriceLabel = new Label("Prix de base : ");
                basePriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                basePriceLabel.setTextFill(Color.web("black"));
                Label basePrice = new Label(enchere.getVente().getPrixBase()+" €");
                basePrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                basePrice.setTextFill(Color.web("#5D48D7"));  
                basePrice.setAlignment(Pos.BASELINE_RIGHT);

                //Temps restant
                Label remainTimeLabel = new Label("Temps restant : ");
                remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTimeLabel.setTextFill(Color.web("black"));
                Label remainTime = new Label(enchere.getVente().tempsRestant());
                remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTime.setTextFill(Color.web("#5D48D7"));  
                remainTime.setAlignment(Pos.BASELINE_RIGHT);

                //Bouton
                Tooltip bidToolTip = new Tooltip(String.valueOf(enchere.getVente().getIdentifiant()));
                VBox bidButtonContent = new VBox();
                Button bid = new Button("Enchérir");
                bid.setTooltip(bidToolTip);
                Tooltip.uninstall(bid,bidToolTip);
                bid.setEffect(ds);
                bid.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                bid.setPadding(new Insets(10,30,10,30));
                if (remainTime.getText().equals("Vente terminée")) {
                    bid.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
                    bid.setDisable(true);
                } else {
                    bid.setOnAction((key) -> this.appli.fenetreEnchere(enchere.getVente(),this));
                    bid.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
                    bid.setAlignment(Pos.BASELINE_RIGHT);
                }                
                bidButtonContent.getChildren().add(bid);

                //Placement dans les informations (partie de droite)
                rightSide.add(enchereTitle,0,0,3,1);
                rightSide.add(actualPriceLabel,0,1,1,1);
                rightSide.add(actualPrice,1,1,1,1);
                rightSide.add(actualEnchereLabel,0,2,1,1);
                rightSide.add(actualEnchere,1,2,1,1);
                rightSide.add(basePriceLabel,0,3,1,1);
                rightSide.add(basePrice,1,3,1,1);
                rightSide.add(remainTimeLabel,0,4,1,1);
                rightSide.add(remainTime,1,4,1,1);
                rightSide.add(bid,0,5,1,1);
                rightSide.setMaxWidth(700);
                rightSide.setPadding(new Insets(0,0,0,30));
                rightSide.setHgap(30);
                rightSide.setVgap(30);


                //Placement dans le container d'enchère
                splitBid.setPadding(new Insets(30));
                splitBid.getChildren().addAll(leftSide,rightSide);
                
                bidContent.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
                bidContent.setAlignment(Pos.CENTER);
                bidContent.getChildren().add(splitBid);
                bidContent.setEffect(ds);

                bidsContent.setPadding(new Insets(50,200,200,200));
                bidsContent.getChildren().add(bidContent);
            }
            this.setCenter(bidsContent);
        } else {
            VBox emptyBidsContent = new VBox(10);
            Label emptyBids = new Label("Vous n'avez pas d'enchères en cours");
            emptyBids.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            emptyBids.setTextFill(Color.web("black")); 
    
            emptyBidsContent.setAlignment(Pos.CENTER);
            emptyBidsContent.setPadding(new Insets(100));
            emptyBidsContent.getChildren().addAll(emptyBids);

            this.setCenter(emptyBidsContent);
        }
    }
}