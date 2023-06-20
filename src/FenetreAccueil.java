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
        List<String> filtersList = this.appli.getScriptJDBC().getCategories();
        ComboBox<String> CBfilters = new ComboBox<String>();
        CBfilters.getItems().addAll(filtersList);
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
            ImageView pic = new ImageView(new Image("file:./img/vae2.png"));

            //Informations
            GridPane informations = new GridPane();
            Label actualPriceLabel = new Label("Prix actuel : ");
            actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            actualPriceLabel.setTextFill(Color.web("black"));
            Label actualPrice = new Label(String.valueOf(50)+" €"); //Get le prix de la vente
            actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            actualPrice.setTextFill(Color.web("#5D48D7"));
            actualPrice.setAlignment(Pos.BASELINE_RIGHT);
            Label remainTimeLabel = new Label("Temps restant : ");
            remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            remainTimeLabel.setTextFill(Color.web("black"));
            Label remainTime = new Label("--.--:--.--");
            remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            remainTime.setTextFill(Color.web("#5D48D7"));
            remainTime.setAlignment(Pos.BASELINE_RIGHT);
            Label nbEncheresLabel = new Label("Nombre d'enchères : ");
            nbEncheresLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            nbEncheresLabel.setTextFill(Color.web("black"));
            Label nbEnchere = new Label(String.valueOf(12)); //Get nombre d'enchère
            nbEnchere.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            nbEnchere.setTextFill(Color.web("#5D48D7"));
            nbEnchere.setAlignment(Pos.BASELINE_RIGHT);

            informations.setHgap(30);
            informations.setVgap(30);
            informations.setStyle("-fx-border-color: lightgray; -fx-border-width: 3 0 0 0"); 
            informations.add(actualPriceLabel,0,0,1,1);
            informations.add(actualPrice,1,0,1,1);
            informations.add(remainTimeLabel,0,1,1,1);
            informations.add(remainTime,1,1,1,1);
            informations.add(nbEncheresLabel,0,2,1,1);
            informations.add(nbEnchere,1,2,1,1);
            
            item.setPadding(new Insets(30));
            item.setPrefWidth(500);
            item.setPrefHeight(700);
            item.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
            item.getChildren().addAll(pic,informations);
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