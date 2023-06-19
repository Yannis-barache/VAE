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
import java.util.Map;
import java.util.HashMap;

public class FenetreMesEncheres extends BorderPane {

    private ApplicationVAE appli;
    private List<Map<String,String>> encheres;

    public FenetreMesEncheres(ApplicationVAE appli,List<Map<String,String>> encheres) {
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
            for (Map<String,String> enchere : this.encheres) {
                VBox bidContent = new VBox();
                HBox splitBid = new HBox();

                VBox leftSide = new VBox();

                //Titre et image (gauche)
                Label enchereTitle = new Label(enchere.get("titre"));
                enchereTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                enchereTitle.setTextFill(Color.web("#5D48D7"));     
                ImageView encherePic = new ImageView(new Image("file:./img/vae2.png"));
                encherePic.setFitWidth(400);
                encherePic.setFitHeight(400);
                encherePic.setPreserveRatio(true);    

                leftSide.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 3 0 0");
                leftSide.setPadding(new Insets(30));
                leftSide.getChildren().addAll(enchereTitle,encherePic);

                //Informations (droite)
                GridPane rightSide = new GridPane();

                //Prix actuel
                Label actualPriceLabel = new Label("Enchère actuel : ");
                actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPriceLabel.setTextFill(Color.web("black"));
                Label actualPrice = new Label(enchere.get("actualPrice")+" €");
                actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPrice.setTextFill(Color.web("#5D48D7"));  
                actualPrice.setAlignment(Pos.BASELINE_RIGHT);

                //Votre enchère
                Label actualEnchereLabel = new Label("Votre enchère : ");
                actualEnchereLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualEnchereLabel.setTextFill(Color.web("black"));
                Label actualEnchere = new Label(enchere.get("actualPrice")+" €");
                actualEnchere.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualEnchere.setTextFill(Color.web("#5D48D7"));  
                actualEnchere.setAlignment(Pos.BASELINE_RIGHT);

                //Prix de base
                Label basePriceLabel = new Label("Prix de base : ");
                basePriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                basePriceLabel.setTextFill(Color.web("black"));
                Label basePrice = new Label(enchere.get("basePrice")+" €");
                basePrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                basePrice.setTextFill(Color.web("#5D48D7"));  
                basePrice.setAlignment(Pos.BASELINE_RIGHT);

                //Temps restant
                Label remainTimeLabel = new Label("Temps restant : ");
                remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTimeLabel.setTextFill(Color.web("black"));
                Label remainTime = new Label("** **** *");
                remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTime.setTextFill(Color.web("#5D48D7"));  
                remainTime.setAlignment(Pos.BASELINE_RIGHT);

                //Bouton
                Tooltip bidToolTip = new Tooltip(enchere.get("id"));
                Button bid = new Button("Enchérir");
                bid.setTooltip(bidToolTip);
                Tooltip.uninstall(bid,bidToolTip);
                bid.setEffect(ds);
                bid.setOnAction((key) -> this.appli.fenetreEnchere(getEnchere(bidToolTip.getText())));
                bid.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                bid.setPadding(new Insets(10,30,10,30));
                bid.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
                bid.setPadding(new Insets(10,30,10,30));
                bid.setAlignment(Pos.BASELINE_RIGHT);

                //Placement dans les informations (partie de droite)
                rightSide.add(actualPriceLabel,0,0,1,1);
                rightSide.add(actualPrice,1,0,1,1);
                rightSide.add(actualEnchereLabel,0,1,1,1);
                rightSide.add(actualEnchere,1,1,1,1);
                rightSide.add(basePriceLabel,0,2,1,1);
                rightSide.add(basePrice,1,2,1,1);
                rightSide.add(remainTimeLabel,0,3,1,1);
                rightSide.add(remainTime,1,3,1,1);
                rightSide.add(bid,1,4,1,1);
                rightSide.setMaxWidth(700);
                rightSide.setPadding(new Insets(30));
                rightSide.setHgap(30);
                rightSide.setVgap(30);


                //Placement dans le container d'enchère
                splitBid.setPadding(new Insets(10));
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

    private Map<String,String> getEnchere(String id) {
        for (Map<String,String> enchere : this.encheres) {
            if (enchere.get("id").equals(id)) {
                return enchere;
            }
        }
        return null;
    }
}