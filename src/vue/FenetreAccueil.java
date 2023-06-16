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
        ComboBox CBfilters = new ComboBox<>();
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
        discoverLabel.setTextFill(Color.web("#5D48D7"));
        discoverLabel.setAlignment(Pos.TOP_LEFT);
        VBox discoverItemsContainer = new VBox();
        TilePane discoverItems = new TilePane();
        discoverItems.setMaxWidth(1600);
        discoverItems.setHgap(50);
        discoverItems.setVgap(50);
        for (int i=0; i<7;++i) {
            VBox temp = new VBox();
            temp.setPrefWidth(500);
            temp.setPrefHeight(700);
            temp.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
            discoverItems.getChildren().add(temp);
        }

        discoverItemsContainer.setAlignment(Pos.CENTER);
        discoverLabelContainer.setPadding(new Insets(200,0,35,100));

        discoverLabelContainer.getChildren().add(discoverLabel);
        discoverItemsContainer.getChildren().add(discoverItems);

        discoverContent.getChildren().addAll(discoverLabelContainer,discoverItemsContainer);

        this.setTop(searchContent);
        this.setCenter(discoverContent);
    }

}
