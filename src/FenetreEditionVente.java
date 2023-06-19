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
import javafx.scene.control.skin.TextAreaSkin;
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

public class FenetreEditionVente extends GridPane {
    
    private ApplicationVAE appli;
    private Map<String,String> vente;

    public FenetreEditionVente(ApplicationVAE appli,Map<String,String> vente) {
        super();
        this.appli = appli;
        this.vente = vente;

        this.content();
    }

    private void content() {

        //Shadows
        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setOffsetX(4.0f);
        ds.setColor(Color.web("lightgray"));

        //Titre
        VBox titleContent = new VBox();
        System.out.println(this.vente.get("id"));
        Label title = new Label("Modifier une vente");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setTextFill(Color.web("black"));
        titleContent.getChildren().add(title);

        //Nouveau titre
        VBox newTitleContent = new VBox();
        Label newTitleLabel = new Label("Nouveau titre");
        TextField newTitle = new TextField();
        newTitleContent.getChildren().addAll(newTitleLabel,newTitle);

        //Nouvelle description
        VBox newDescContent = new VBox();
        Label newDescLabel = new Label("Nouvelle description");
        TextArea newDesc = new TextArea();
        newTitleContent.getChildren().addAll(newDescLabel,newDesc);

        //Ajout d'images
        VBox newFilesContent = new VBox();
        // Label filesSaleLabel = new Label("Ajouter des images ");
        // filesSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        // filesSaleLabel.setTextFill(Color.web("#5D48D7"));
        // Label filesCountLabel = new Label("0/4");
        // filesCountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        // filesCountLabel.setTextFill(Color.web("#5D48D7"));
        // FileChooser filesSaleFC = new FileChooser();
        // Label temp = new Label("AHMET AHMET AHMET AHMET AHMET"); //File chooser pas dans VBox
        HBox newFilesSaleLabels = new HBox();
        Label newFilesSaleLabel = new Label("Ajouter des images ");
        newFilesSaleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newFilesSaleLabel.setTextFill(Color.web("#5D48D7"));
        Label newFilesCountLabel = new Label("0/4");
        newFilesCountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        newFilesCountLabel.setTextFill(Color.web("#5D48D7"));
        newFilesSaleLabels.getChildren().addAll(newFilesSaleLabel,newFilesCountLabel);
        Button openButton = new Button("+");
        openButton.setEffect(ds);
        openButton.setOnAction((key) -> System.out.println("controleur filechooser"));
        openButton.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        openButton.setPadding(new Insets(10,30,10,30));
        openButton.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        newFilesContent.getChildren().addAll(newFilesSaleLabels,openButton);

        //Catégorie
        VBox newCategorySaleContent = new VBox();
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

        categoryContent.getChildren().addAll(categorySaleLabel,categorySaleCB);



        


        //Ajout du titre
        this.add(titleContent,1,0,1,1);
    }

    
}
