import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
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

import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import javafx.util.Callback;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class FenetreCreationVente extends GridPane {
    
    private ApplicationVAE appli;
    private TextField titleSale,basePriceSale,minPriceSale;
    private TextArea descSale;
    private DatePicker startSale,endSale;

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

        //Titre page
        Label title = new Label("Créer une vente");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));

        //Titre vente
        VBox titleSaleContent = new VBox();
        Label titleSaleLabel = new Label("Titre");
        titleSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        titleSaleLabel.setTextFill(Color.web("#5D48D7"));
        titleSale= new TextField();
        titleSale.setEffect(ds);
        titleSale.setPrefHeight(40);
        titleSale.setPrefWidth(350);
        titleSale.setPromptText("Insérer le titre");
        titleSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        titleSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        titleSaleContent.getChildren().addAll(titleSaleLabel,titleSale);

        //Description
        VBox descSaleContent = new VBox();
        Label descSaleLabel = new Label("Description");
        descSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        descSaleLabel.setTextFill(Color.web("#5D48D7"));
        descSale = new TextArea();
        descSale.setPromptText("Insérez une description de l'objet");
        descSale.setEffect(ds);
        descSale.setPrefHeight(170);
        descSale.setPrefWidth(350);
        descSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        descSale.setStyle("-fx-control-inner-background: #F8F8F8");
        descSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        descSaleContent.getChildren().addAll(descSaleLabel,descSale);

        //Ajout d'images
        VBox filesSaleContent = new VBox();
        // Label filesSaleLabel = new Label("Ajouter des images ");
        // filesSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        // filesSaleLabel.setTextFill(Color.web("#5D48D7"));
        // Label filesCountLabel = new Label("0/4");
        // filesCountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        // filesCountLabel.setTextFill(Color.web("#5D48D7"));
        // FileChooser filesSaleFC = new FileChooser();
        // Label temp = new Label("AHMET AHMET AHMET AHMET AHMET"); //File chooser pas dans VBox
        HBox filesSaleLabels = new HBox();
        Label filesSaleLabel = new Label("Ajouter des images ");
        filesSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        filesSaleLabel.setTextFill(Color.web("#5D48D7"));
        Label filesCountLabel = new Label("0/4");
        filesCountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        filesCountLabel.setTextFill(Color.web("#5D48D7"));
        filesSaleLabels.getChildren().addAll(filesSaleLabel,filesCountLabel);
        Button openButton = new Button("+");
        openButton.setEffect(ds);
        openButton.setOnAction((key) -> System.out.println("controleur filechooser"));
        openButton.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        openButton.setPadding(new Insets(10,30,10,30));
        openButton.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        filesSaleContent.getChildren().addAll(filesSaleLabels,openButton);

        //Catégorie
        VBox categorySaleContent = new VBox();
        Label categorySaleLabel = new Label("Catégorie");
        categorySaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        categorySaleLabel.setTextFill(Color.web("#5D48D7"));
        String[] categories = {"(Aucun)","Meuble","Outils","Ahmet","Martin"};
        ComboBox categorySaleCB = new ComboBox<>(FXCollections.observableArrayList(categories));
        categorySaleCB.setEffect(ds);
        categorySaleCB.setPrefHeight(50);
        categorySaleCB.setPrefWidth(300);
        categorySaleCB.setStyle("-fx-control-inner-background: #F8F8F8");
        categorySaleCB.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        categorySaleContent.getChildren().addAll(categorySaleLabel,categorySaleCB);

        //Prix de base
        VBox basePriceSaleContent = new VBox();
        Label basePriceSaleLabel = new Label("Prix de base");
        basePriceSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        basePriceSaleLabel.setTextFill(Color.web("#5D48D7"));
        basePriceSale = new TextField();
        basePriceSale.setPromptText("Insérez le prix de base");
        basePriceSale.setEffect(ds);
        basePriceSale.setPrefHeight(40);
        basePriceSale.setPrefWidth(350);
        basePriceSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        basePriceSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        basePriceSale.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        basePriceSaleContent.getChildren().addAll(basePriceSaleLabel,basePriceSale);

        //Prix minimum
        VBox minPriceSaleContent = new VBox();
        Label minPriceSaleLabel = new Label("Prix minimum");
        minPriceSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        minPriceSaleLabel.setTextFill(Color.web("#5D48D7"));
        minPriceSale = new TextField();
        minPriceSale.setPromptText("Insérez le prix minimum");
        minPriceSale.setEffect(ds);
        minPriceSale.setPrefHeight(40);
        minPriceSale.setPrefWidth(350);
        minPriceSale.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        minPriceSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        minPriceSale.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        minPriceSaleContent.getChildren().addAll(minPriceSaleLabel,minPriceSale);

        //Date de début
        VBox startSaleContent = new VBox();
        Label startSaleLabel = new Label("Date de début");
        startSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        startSaleLabel.setTextFill(Color.web("#5D48D7"));
        startSale = new DatePicker();
        startSale.getEditor().setDisable(true);
        startSale.setPromptText("Choisissez la date du début");
            
        // Définition de la cellule de date pour la date de début
        Callback<DatePicker, DateCell> startSaleCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #F8F8F8; -fx-text-fill: gray;");
                        }
                    }
                };
            }
        };
        startSale.setDayCellFactory(startSaleCellFactory);
        startSale.setEffect(ds);
        startSale.setPrefHeight(50);
        startSale.setPrefWidth(350);
        startSale.setStyle("-fx-control-inner-background: #F8F8F8");
        startSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        startSaleContent.getChildren().addAll(startSaleLabel, startSale);
        
        //Date de fin
        VBox endSaleContent = new VBox();
        Label endSaleLabel = new Label("Date de fin");
        endSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        endSaleLabel.setTextFill(Color.web("#5D48D7"));
        endSale = new DatePicker();
        endSale.getEditor().setDisable(true);
        endSale.setPromptText("Choisissez la date de fin");
        // Définition de la cellule de date pour la date de fin
        Callback<DatePicker, DateCell> endSaleCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate selectedStartDate = startSale.getValue();
                        if (selectedStartDate != null && item.isBefore(selectedStartDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #F8F8F8; -fx-text-fill: gray;");
                        }
                    }
                };
            }
        };                     
        endSale.setDayCellFactory(endSaleCellFactory);
        endSale.setEffect(ds);
        endSale.setPrefHeight(50);
        endSale.setPrefWidth(350);
        endSale.setStyle("-fx-control-inner-background: #F8F8F8");
        endSale.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        endSale.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && startSale.getValue() != null && newValue.isBefore(startSale.getValue())) {
                endSale.setValue(startSale.getValue());
            }
        });
        
        startSale.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && endSale.getValue() != null && newValue.isAfter(endSale.getValue())) {
                endSale.setValue(newValue);
            }
        });
        
        endSaleContent.getChildren().addAll(endSaleLabel, endSale);
        
        //Buttons
        VBox cancelContent = new VBox();
        Button cancel = new Button("Annuler");
        cancel.setEffect(ds);
        cancel.setOnAction((key) -> this.appli.fenetreAccueil());
        cancel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        cancel.setPadding(new Insets(10,30,10,30));
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        cancelContent.getChildren().add(cancel);

        VBox sendContent = new VBox();
        Button send = new Button("Mettre en ligne");
        send.setEffect(ds);
        send.setOnAction((key) -> System.out.println("next"));
        send.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        send.setPadding(new Insets(10,30,10,30));
        send.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        sendContent.getChildren().add(send);
        sendContent.setAlignment(Pos.TOP_RIGHT);

        //Configuration de la grille
        this.setHgap(100);
        this.setVgap(50);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(50));

        //Ajouts des containers dans la grille
        this.add(title,0,0);
        this.add(titleSaleContent,0,1,2,1);
        this.add(descSaleContent,0,2,2,2);
        this.add(filesSaleContent,0,4,1,1);
        this.add(categorySaleContent,1,4,1,1);
        
        this.add(basePriceSaleContent,2,1,1,1);
        this.add(minPriceSaleContent,2,2,1,1);
        this.add(startSaleContent,2,3,1,1);
        this.add(endSaleContent,2,4,1,1);

        this.add(cancelContent,0,5,1,1);
        this.add(sendContent,2,5,1,1);
    }
}
