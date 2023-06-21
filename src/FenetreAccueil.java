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
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class FenetreAccueil extends BorderPane {
    
    private ApplicationVAE appli;
    private List<Vente> ventesEnCours;

    public FenetreAccueil(ApplicationVAE appli,List<Vente> ventesEnCours)  {
        super();
        this.appli = appli;
        this.ventesEnCours = ventesEnCours;

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
    
        //Catégories
        VBox categories = new VBox();
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

        categories.getChildren().addAll(filtersLabel,CBfilters);


        //Filtre prix
        VBox priceFilter = new VBox();
        Label priceFilterLabel = new Label("Prix maximal");
        priceFilterLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        priceFilterLabel.setTextFill(Color.web("#5D48D7"));
        TextField price = new TextField();
        price.setEffect(ds);
        price.setPrefHeight(50);
        price.setPrefWidth(300);
        price.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        price.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        price.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        priceFilter.getChildren().addAll(priceFilterLabel,price);

        //Filtre date
        VBox dateFilter = new VBox();
        Label dateFilterLabel = new Label("Dernière minute");
        dateFilterLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        dateFilterLabel.setTextFill(Color.web("#5D48D7"));
        DatePicker date = new DatePicker();
        date.getEditor().setDisable(true);
        date.setPromptText("Choisissez une date de fin");
            
        // Définition de la cellule de date pour la date de fin
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
        date.setDayCellFactory(startSaleCellFactory);
        date.setEffect(ds);
        date.setPrefHeight(50);
        date.setPrefWidth(350);
        date.setStyle("-fx-control-inner-background: #F8F8F8");
        date.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        dateFilter.getChildren().addAll(dateFilterLabel,date);

        // Ajout du bouton rechercher
        VBox searchButtonBox = new VBox();
        Button searchButton = new Button("Rechercher");
        searchButton.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        searchButton.setPadding(new Insets(10,30,10,30));
        searchButton.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        searchButton.setOnAction((key) -> System.out.println("next"));
        searchButton.setEffect(ds);
        searchButtonBox.setPadding(new Insets(30,0,0,0));

        searchButtonBox.getChildren().add(searchButton);

        //Placement
        searchContent.setHgap(50);
        searchContent.setVgap(30);
        searchContent.setPadding(new Insets(200,0,0,0));
        searchContent.setAlignment(Pos.CENTER);
        searchContent.add(searchBar,0,0,3,1);
        searchContent.add(categories,0,1,1,1);
        searchContent.add(priceFilter,1,1,1,1);
        searchContent.add(dateFilter,2,1,1,1);
        searchContent.add(searchButtonBox,3,0,1,1);

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

        if (ventesEnCours.size() > 0) {

            for (int i=0;i<15;++i) {

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

                //Titre
                Label actualTitle = new Label(String.valueOf(this.ventesEnCours.get(i).getObjet().getNom())); //Get le prix de la vente
                actualTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualTitle.setTextFill(Color.web("#5D48D7"));
                actualTitle.setAlignment(Pos.BASELINE_RIGHT);
        
                //Prix actuel
                Label actualPriceLabel = new Label("Prix actuel :");
                actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPriceLabel.setTextFill(Color.web("black"));
                Label actualPrice = new Label(String.valueOf(this.ventesEnCours.get(i).getPrixBase())+" €"); //Get le prix de la vente
                actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                actualPrice.setTextFill(Color.web("#5D48D7"));
                actualPrice.setAlignment(Pos.BASELINE_RIGHT);

                //Temps restant
                Label remainTimeLabel = new Label("Temps restant :");
                remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTimeLabel.setTextFill(Color.web("black"));
                Label remainTime = new Label("--.--:--.--");
                remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                remainTime.setTextFill(Color.web("#5D48D7"));
                remainTime.setAlignment(Pos.BASELINE_RIGHT);

                //Nombre d'enchères
                Label nbEncheresLabel = new Label("Nombre d'enchères :");
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
                informations.add(actualTitle,0,0,1,1);
                informations.add(actualPriceLabel,0,1,1,1);
                informations.add(actualPrice,1,1,1,1);
                informations.add(remainTimeLabel,0,2,1,1);
                informations.add(remainTime,1,2,1,1);
                informations.add(nbEncheresLabel,0,3,1,1);
                informations.add(nbEnchere,1,3,1,1);
                informations.add(buttonContainer,0,4,2,1);
                
                //Properties
                item.setPadding(new Insets(30));
                item.setPrefWidth(500);
                // item.setPrefHeight(520);
                item.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
                item.getChildren().addAll(picContainer,informations);
                item.setEffect(ds);
                discoverItems.getChildren().add(item);
            }
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