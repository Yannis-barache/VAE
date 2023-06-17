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
        Label title = new Label("Créer une vente");

        VBox titleSaleContent = new VBox();
        Label titleSaleLabel = new Label("Titre");
        TextField titleSale = new TextField();

        titleSaleContent.getChildren().addAll(titleSaleLabel,titleSale);


        VBox descSaleContent = new VBox();
        Label descSaleLabel = new Label("Description");
        TextArea descSale = new TextArea();
        descSale.setPrefHeight(120);
        descSaleContent.getChildren().addAll(descSaleLabel,descSale);


        VBox filesSaleContent = new VBox();
        HBox filesSaleLabels = new HBox();
        Label filesSaleLabel = new Label("Ajouter des images");
        Label filesCountLabel = new Label("0/4");
        filesSaleLabels.getChildren().addAll(filesSaleLabel,filesCountLabel);
        FileChooser filesSaleFC = new FileChooser();
        Label temp = new Label("AHMET AHMET AHMET AHMET AHMET"); //File chooser pas dans VBox

        filesSaleContent.getChildren().addAll(filesSaleLabels,temp);


        VBox categorySaleContent = new VBox();
        Label categorySaleLabel = new Label("Catégorie");
        ComboBox categorySaleCB = new ComboBox<>();

        categorySaleContent.getChildren().addAll(categorySaleLabel,categorySaleCB);


        VBox basePriceSaleContent = new VBox();
        Label basePriceSaleLabel = new Label("Prix de base");
        TextField basePriceSale = new TextField();

        basePriceSaleContent.getChildren().addAll(basePriceSaleLabel,basePriceSale);


        VBox minPriceSaleContent = new VBox();
        Label minPriceSaleLabel = new Label("Prix minimal");
        TextField minPriceSale = new TextField();
        
        minPriceSaleContent.getChildren().addAll(minPriceSaleLabel,minPriceSale);


        VBox startSaleContent = new VBox();
        Label startSaleLabel = new Label("Date de début");
        DatePicker startSale = new DatePicker();

        startSaleContent.getChildren().addAll(startSaleLabel,startSale);


        VBox endSaleContent = new VBox();
        Label endSaleLabel = new Label("Date de fin");
        DatePicker endSale = new DatePicker();

        endSaleContent.getChildren().addAll(endSaleLabel,endSale);


        //Buttons
        Button cancel = new Button("Annuler");
        Button send = new Button("Mettre en ligne");

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
