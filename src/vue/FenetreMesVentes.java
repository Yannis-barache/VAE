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
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;

public class FenetreMesVentes extends BorderPane {
    
    private ApplicationVAE appli;
    private List<List<Map<String,String>>> ventes;

    public FenetreMesVentes(ApplicationVAE appli,List<List<Map<String,String>>> ventes) {
        super();
        this.appli = appli;
        this.ventes = ventes;

        this.content();
    }

    private void content() {
        
        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        //Titre
        HBox titleContent = new HBox();
        Label title = new Label("Mes ventes : ");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));
        Label countVente = new Label(String.valueOf(ventes.size()));
        countVente.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        countVente.setTextFill(Color.web("black"));
        titleContent.getChildren().addAll(title,countVente);

        //Ventes
        VBox ventesContent = new VBox(50);

        for (List<Map<String,String>> vente : this.ventes) {
            VBox venteContent = new VBox();
            HBox splitVente = new HBox();
            VBox leftSide = new VBox();

            //Titre et image (gauche)
            Label venteTitle = new Label(vente.get(0).get("titre"));
            venteTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            venteTitle.setTextFill(Color.web("#5D48D7"));     
            ImageView ventePic = new ImageView(new Image("./img/vae.png"));
            ventePic.setFitWidth(120);
            ventePic.setFitHeight(120);
            ventePic.setPreserveRatio(true);    

            leftSide.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 3 0 0");
            leftSide.setPadding(new Insets(10));
            leftSide.getChildren().addAll(venteTitle,ventePic);

            //Informations (droite)
            GridPane rightSide = new GridPane();

            //Prix actuel
            Label actualPriceLabel = new Label("Prix actuel : ");
            Label actualPrice = new Label(vente.get(1).get("prixBase"));
            actualPrice.setAlignment(Pos.BASELINE_RIGHT);

            //Temps restant
            Label remainTimeLabel = new Label("Temps restant : ");
            Label remainTime = new Label("** **** *");
            remainTime.setAlignment(Pos.BASELINE_RIGHT);

            //Nombre enchères
            Label nbEnchereslabel = new Label("Nombre d'enchère : ");
            Label nbEncheres = new Label("8");
            nbEncheres.setAlignment(Pos.BASELINE_RIGHT);

            //Bouton
            Button edit = new Button("Modifier");
            edit.setEffect(ds);
            edit.setOnAction((key) -> System.out.println("edit vente"));
            edit.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            edit.setPadding(new Insets(10,30,10,30));
            edit.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));

            //Placement dans les informations (partie de droite)
            rightSide.add(actualPriceLabel,0,0,1,1);
            rightSide.add(actualPrice,1,0,1,1);
            rightSide.add(remainTimeLabel,0,1,1,1);
            rightSide.add(remainTime,1,1,1,1);
            rightSide.add(nbEnchereslabel,0,2,1,1);
            rightSide.add(nbEncheres,1,2,1,1);
            rightSide.add(edit,1,3,1,1);
            rightSide.setPadding(new Insets(10));


            //Placement dans le container de vente
            splitVente.setPadding(new Insets(10));
            splitVente.getChildren().addAll(leftSide,rightSide);
            
            venteContent.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
            venteContent.setAlignment(Pos.CENTER);
            venteContent.getChildren().addAll(splitVente);

            ventesContent.setPadding(new Insets(100));
            ventesContent.getChildren().add(venteContent);
        }

        this.setTop(titleContent);
        this.setCenter(ventesContent);
    }
}
