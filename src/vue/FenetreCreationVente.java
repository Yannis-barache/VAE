import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
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

public class FenetreCreationVente extends GridPane {
    
    private ApplicationVAE appli;

    public FenetreCreationVente(ApplicationVAE appli) {
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

        Label title = new Label("Créer une vente");

        VBox titleSaleContent = new VBox();
        Label titleSaleLabel = new Label("Titre");
        titleSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        titleSaleLabel.setTextFill(Color.web("#5D48D7"));
        TextField titleSale = new TextField();
        titleSale.setEffect(ds);
        titleSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        titleSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        titleSaleContent.getChildren().addAll(titleSaleLabel,titleSale);


        VBox descSaleContent = new VBox();
        Label descSaleLabel = new Label("Description");
        descSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        descSaleLabel.setTextFill(Color.web("#5D48D7"));
        TextArea descSale = new TextArea();
        descSale.setPrefHeight(120);
        descSale.setEffect(ds);
        descSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        descSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        descSaleContent.getChildren().addAll(descSaleLabel,descSale);


        VBox filesSaleContent = new VBox();
        HBox filesSaleLabels = new HBox();
        Label filesSaleLabel = new Label("Ajouter des images");
        filesSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        filesSaleLabel.setTextFill(Color.web("#5D48D7"));
        Label filesCountLabel = new Label("0/4");
        filesCountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        filesCountLabel.setTextFill(Color.web("#5D48D7"));
        filesSaleLabels.getChildren().addAll(filesSaleLabel,filesCountLabel);
        FileChooser filesSaleFC = new FileChooser();
        Label temp = new Label("AHMET AHMET AHMET AHMET AHMET"); //File chooser pas dans VBox

        filesSaleContent.getChildren().addAll(filesSaleLabels,temp);


        VBox categorySaleContent = new VBox();
        Label categorySaleLabel = new Label("Catégorie");
        categorySaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        categorySaleLabel.setTextFill(Color.web("#5D48D7"));
        ComboBox categorySaleCB = new ComboBox<>();
        categorySaleCB.setEffect(ds);
        categorySaleCB.setPrefHeight(50);
        categorySaleCB.setPrefWidth(100);
        categorySaleCB.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));


        categorySaleContent.getChildren().addAll(categorySaleLabel,categorySaleCB);


        VBox basePriceSaleContent = new VBox();
        Label basePriceSaleLabel = new Label("Prix de base");
        basePriceSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        basePriceSaleLabel.setTextFill(Color.web("#5D48D7"));
        TextField basePriceSale = new TextField();
        basePriceSale.setEffect(ds);
        basePriceSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        basePriceSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        basePriceSaleContent.getChildren().addAll(basePriceSaleLabel,basePriceSale);


        VBox minPriceSaleContent = new VBox();
        Label minPriceSaleLabel = new Label("Prix minimal");
        minPriceSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        minPriceSaleLabel.setTextFill(Color.web("#5D48D7"));
        TextField minPriceSale = new TextField();
        minPriceSale.setEffect(ds);
        minPriceSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        minPriceSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        minPriceSaleContent.getChildren().addAll(minPriceSaleLabel,minPriceSale);


        VBox startSaleContent = new VBox();
        Label startSaleLabel = new Label("Date de début");
        startSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startSaleLabel.setTextFill(Color.web("#5D48D7"));
        DatePicker startSale = new DatePicker();
        startSale.setEffect(ds);
        // startSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        startSaleContent.getChildren().addAll(startSaleLabel,startSale);


        VBox endSaleContent = new VBox();
        Label endSaleLabel = new Label("Date de fin");
        endSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endSaleLabel.setTextFill(Color.web("#5D48D7"));
        DatePicker endSale = new DatePicker();
        endSale.setEffect(ds);
        // endSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        endSaleContent.getChildren().addAll(endSaleLabel,endSale);


        //Buttons
        Button cancel = new Button("Annuler");
        cancel.setEffect(ds);
        cancel.setOnAction((key) -> this.appli.fenetreAccueil());
        cancel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        cancel.setPadding(new Insets(10,30,10,30));
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        Button send = new Button("Mettre en ligne");
        send.setEffect(ds);
        send.setOnAction((key) -> System.out.println("next"));
        send.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        send.setPadding(new Insets(10,30,10,30));
        send.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));

        this.setHgap(30);
        this.setVgap(30);
        this.setAlignment(Pos.CENTER);

        this.add(title,0,0);
        this.add(titleSaleContent,0,1,2,1);
        this.add(descSaleContent,0,2,2,2);
        this.add(filesSaleContent,0,4,1,1);
        this.add(categorySaleContent,1,4,1,1);
        
        this.add(basePriceSaleContent,2,1,1,1);
        this.add(minPriceSaleContent,2,2,1,1);
        this.add(startSaleContent,2,3,1,1);
        this.add(endSaleContent,2,4,1,1);

        this.add(cancel,0,5,1,1);
        this.add(send,2,5,1,1);
    }
}
