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
    private List<Map<String,String>> ventes;

    public FenetreMesVentes(ApplicationVAE appli,List<Map<String,String>> ventes) {
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

        //Ventes
        VBox ventesContent = new VBox(100);

        //Titre
        HBox titleContent = new HBox();
        Label title = new Label("Mes ventes : ");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));
        Label countVente = new Label(String.valueOf(ventes.size()));
        countVente.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        countVente.setTextFill(Color.web("black"));
        titleContent.getChildren().addAll(title,countVente);

        //Ajout du titre
        ventesContent.getChildren().add(titleContent);

        // this.ventes = new ArrayList<List<Map<String,String>>>();

        if (this.ventes.size() > 0) {

            for (Map<String,String> vente : this.ventes) {
                VBox venteContent = new VBox();
                HBox splitVente = new HBox();

                VBox leftSide = new VBox();

                //Titre et image (gauche)
                Label venteTitle = new Label(vente.get("titre"));
                venteTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                venteTitle.setTextFill(Color.web("#5D48D7"));     
                ImageView ventePic = new ImageView(new Image("./img/vae.png"));
                ventePic.setFitWidth(400);
                ventePic.setFitHeight(400);
                ventePic.setPreserveRatio(true);    

                leftSide.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 3 0 0");
                leftSide.setPadding(new Insets(30));
                leftSide.getChildren().addAll(venteTitle,ventePic);

                //Informations (droite)
                GridPane rightSide = new GridPane();

                //Prix actuel
                Label actualPriceLabel = new Label("Prix actuel : ");
                actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPriceLabel.setTextFill(Color.web("black"));
                Label actualPrice = new Label(vente.get("prixBase")+" €");
                actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPrice.setTextFill(Color.web("#5D48D7"));  
                actualPrice.setAlignment(Pos.BASELINE_RIGHT);

                //Temps restant
                Label remainTimeLabel = new Label("Temps restant : ");
                remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTimeLabel.setTextFill(Color.web("black"));
                Label remainTime = new Label("** **** *");
                remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTime.setTextFill(Color.web("#5D48D7"));  
                remainTime.setAlignment(Pos.BASELINE_RIGHT);

                //Nombre enchères
                Label nbEnchereslabel = new Label("Nombre d'enchère : ");
                nbEnchereslabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                nbEnchereslabel.setTextFill(Color.web("black"));
                Label nbEncheres = new Label("8");
                nbEncheres.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                nbEncheres.setTextFill(Color.web("#5D48D7"));
                nbEncheres.setAlignment(Pos.BASELINE_RIGHT);

                //Bouton
                Button edit = new Button("Modifier");
                edit.setEffect(ds);
                edit.setOnAction((key) -> System.out.println("edit vente"));
                edit.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                edit.setPadding(new Insets(10,30,10,30));
                edit.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
                edit.setPadding(new Insets(10,30,10,30));
                edit.setAlignment(Pos.BASELINE_RIGHT);

                //Placement dans les informations (partie de droite)
                rightSide.add(actualPriceLabel,0,0,1,1);
                rightSide.add(actualPrice,1,0,1,1);
                rightSide.add(remainTimeLabel,0,1,1,1);
                rightSide.add(remainTime,1,1,1,1);
                rightSide.add(nbEnchereslabel,0,2,1,1);
                rightSide.add(nbEncheres,1,2,1,1);
                rightSide.add(edit,0,3,2,1);
                rightSide.setMaxWidth(500);
                rightSide.setPadding(new Insets(30));
                rightSide.setHgap(30);
                rightSide.setVgap(30);


                //Placement dans le container de vente
                splitVente.setPadding(new Insets(10));
                splitVente.getChildren().addAll(leftSide,rightSide);
                
                venteContent.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
                venteContent.setAlignment(Pos.CENTER);
                venteContent.getChildren().addAll(splitVente);
                venteContent.setEffect(ds);

                ventesContent.setPadding(new Insets(50,200,200,200));
                ventesContent.getChildren().add(venteContent);
            }
            this.setCenter(ventesContent);
        } else {
            VBox emptyVentesContent = new VBox(10);
            Label emptyVentes = new Label("Vous n'avez pas de vente en cours");
            emptyVentes.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            emptyVentes.setTextFill(Color.web("black")); 
            Button createVente = new Button("Mettre en ligne une vente maintenant !");
            createVente.setOnAction((key) -> this.appli.fenetreCreationVente());
            createVente.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            createVente.setBackground(new Background(new BackgroundFill(Color.web("white"),CornerRadii.EMPTY,Insets.EMPTY)));
            createVente.setTextFill(Color.web("#5D48D7"));

            emptyVentesContent.setAlignment(Pos.CENTER);
            emptyVentesContent.setPadding(new Insets(100));
            emptyVentesContent.getChildren().addAll(emptyVentes,createVente);

            this.setCenter(emptyVentesContent);
        }
    }
}
