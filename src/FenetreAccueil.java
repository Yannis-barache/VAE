import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
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

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreAccueil extends BorderPane {
    
    private ApplicationVAE appli;

    public FenetreAccueil(ApplicationVAE appli) {
        super();
        this.appli = appli;

        this.content();
    }

    private void content() {

        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        //Barre de recherches
        GridPane searchContent = new GridPane();
        
        VBox searchBar = new VBox();
        Label searchLabel = new Label("Rechercher");
        searchLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        searchLabel.setTextFill(Color.web("#5D48D7"));
        TextField search = new TextField();
        search.setEffect(ds);
        search.setPrefHeight(40);
        search.setPrefWidth(1000);
        search.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        search.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        searchBar.getChildren().addAll(searchLabel,search);


        VBox filters = new VBox();
        Label filtersLabel = new Label("Catégories");
        filtersLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        filtersLabel.setTextFill(Color.web("#5D48D7"));
        String[] categories = {"(Aucun)","Meuble","Outils","Ahmet","Martin"};
        ComboBox CBfilters = new ComboBox<>(FXCollections.observableArrayList(categories));
        CBfilters.setEffect(ds);
        CBfilters.setPrefHeight(50);
        CBfilters.setPrefWidth(300);
        CBfilters.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        filters.getChildren().addAll(filtersLabel,CBfilters);

        searchContent.setHgap(50);
        searchContent.setPadding(new Insets(200,0,0,0));
        searchContent.setAlignment(Pos.CENTER);
        searchContent.add(searchBar,0,0); //span
        searchContent.add(filters,1,0); //span

        //Decouvrez
        VBox discoverContent = new VBox();
        VBox discoverLabelContainer = new VBox();
        Label discoverLabel = new Label("Découvrez");
        discoverLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        discoverLabel.setPadding(new Insets(0,0,50,00));
        discoverLabel.setTextFill(Color.web("#5D48D7"));
        discoverLabel.setAlignment(Pos.TOP_LEFT);
        VBox discoverItemsContainer = new VBox();
        TilePane discoverItems = new TilePane();
        discoverItems.setHgap(50);
        discoverItems.setVgap(50);
        for (int i=0; i<7;++i) {

            //Une vente
            VBox item = new VBox();

            //Image
            VBox picContainer = new VBox();
            ImageView pic = new ImageView(new Image("file:./img/blank.png"));
            pic.setFitWidth(440);
            pic.setPreserveRatio(true);
            picContainer.getChildren().add(pic);
            picContainer.setPadding(new Insets(0,0,50,0));

            //Informations
            GridPane informations = new GridPane();

            //Prix actuel
            Label actualPriceLabel = new Label("Prix actuel : ");
            actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            actualPriceLabel.setTextFill(Color.web("black"));
            Label actualPrice = new Label(String.valueOf(50)+" €"); //Get le prix de la vente
            actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            actualPrice.setTextFill(Color.web("#5D48D7"));
            actualPrice.setAlignment(Pos.BASELINE_RIGHT);

            //Temps restant
            Label remainTimeLabel = new Label("Temps restant : ");
            remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            remainTimeLabel.setTextFill(Color.web("black"));
            Label remainTime = new Label("--.--:--.--");
            remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            remainTime.setTextFill(Color.web("#5D48D7"));
            remainTime.setAlignment(Pos.BASELINE_RIGHT);

            //Nombre d'enchères
            Label nbEncheresLabel = new Label("Nombre d'enchères : ");
            nbEncheresLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            nbEncheresLabel.setTextFill(Color.web("black"));
            Label nbEnchere = new Label(String.valueOf(12)); //Get nombre d'enchère
            nbEnchere.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            nbEnchere.setTextFill(Color.web("#5D48D7"));
            nbEnchere.setAlignment(Pos.BASELINE_RIGHT);

            //Button
            VBox buttonContainer = new VBox();
            Button buttonItem = new Button("Enchérir");
            buttonItem.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            buttonItem.setPadding(new Insets(10,30,10,30));
            buttonItem.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
            buttonItem.setOnAction((key) -> System.out.println("next"));
            buttonItem.setEffect(ds);
            buttonContainer.getChildren().add(buttonItem);
            buttonContainer.setAlignment(Pos.BASELINE_RIGHT);

            //Placement
            informations.setHgap(30);
            informations.setVgap(30);
            informations.setPadding(new Insets(50,0,50,0));
            informations.setStyle("-fx-border-color: lightgray; -fx-border-width: 3 0 0 0"); 
            informations.add(actualPriceLabel,0,0,1,1);
            informations.add(actualPrice,1,0,1,1);
            informations.add(remainTimeLabel,0,1,1,1);
            informations.add(remainTime,1,1,1,1);
            informations.add(nbEncheresLabel,0,2,1,1);
            informations.add(nbEnchere,1,2,1,1);
            informations.add(buttonContainer,0,3,2,1);
            
            //Properties
            item.setPadding(new Insets(30));
            item.setPrefWidth(500);
            item.setPrefHeight(600);
            item.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
            item.getChildren().addAll(picContainer,informations);
            item.setEffect(ds);
            discoverItems.getChildren().add(item);
        }

        // discoverItemsContainer.setAlignment(Pos.CENTER);

        discoverLabelContainer.getChildren().add(discoverLabel);
        discoverItemsContainer.getChildren().add(discoverItems);

        discoverContent.setPadding(new Insets(200,0,35,100));
        discoverContent.getChildren().addAll(discoverLabelContainer,discoverItemsContainer);

        this.setTop(searchContent);
        this.setCenter(discoverContent);
    }
}