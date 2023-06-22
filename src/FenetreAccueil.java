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
import java.sql.SQLException;
import java.util.ArrayList;

public class FenetreAccueil extends BorderPane {
    
    private ApplicationVAE appli;
    private List<Vente> ventesEnCours;
    private List<Vente> searchResult;
    private TextField search;
    private ComboBox<String> CBfilters;
    private DatePicker date;
    private TextField price;
    private DropShadow ds;
    private VBox discoverContent;
    private VBox discoverLabelContainer;
    private VBox discoverItemsContainer;
    private Label discoverLabel;
    private TilePane discoverItems;

    public FenetreAccueil(ApplicationVAE appli,List<Vente> ventesEnCours)  {
        super();
        this.appli = appli;
        this.ventesEnCours = ventesEnCours;
        this.searchResult = new ArrayList<Vente>();


        this.content();
    }

    private void content() {

        //Shadows
        this.ds = new DropShadow();
        this.ds.setOffsetY(6.0f);
        this.ds.setOffsetX(4.0f);
        this.ds.setColor(Color.web("lightgray"));

        //Barre de recherches
        GridPane searchContent = new GridPane();
        
        VBox searchBar = new VBox();
        Label searchLabel = new Label("Rechercher");
        searchLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        searchLabel.setTextFill(Color.web("#5D48D7"));
        this.search = new TextField();
        this.search.setEffect(ds);
        this.search.setPrefHeight(40);
        this.search.setPrefWidth(1000);
        this.search.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        this.search.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        searchBar.getChildren().addAll(searchLabel,this.search);      
    
        //Catégories
        VBox categories = new VBox();
        Label filtersLabel = new Label("Catégories");
        filtersLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        filtersLabel.setTextFill(Color.web("#5D48D7"));
        List<String> filtersList = this.appli.getScriptJDBC().getCategories();
        filtersList.add("Toutes catégories");
        this.CBfilters = new ComboBox<String>();
        this.CBfilters.setValue("Toutes catégories");
        this.CBfilters.getItems().addAll(filtersList);
        this.CBfilters.setEffect(ds);
        this.CBfilters.setPrefHeight(50);
        this.CBfilters.setPrefWidth(300);
        this.CBfilters.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        categories.getChildren().addAll(filtersLabel,this.CBfilters);


        //Filtre prix
        VBox priceFilter = new VBox();
        Label priceFilterLabel = new Label("Prix maximal");
        priceFilterLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        priceFilterLabel.setTextFill(Color.web("#5D48D7"));
        this.price = new TextField();
        this.price.setEffect(ds);
        this.price.setPrefHeight(50);
        this.price.setPrefWidth(300);
        this.price.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        this.price.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));
        this.price.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

        priceFilter.getChildren().addAll(priceFilterLabel,this.price);

        //Filtre date
        VBox dateFilter = new VBox();
        Label dateFilterLabel = new Label("Dernière minute");
        dateFilterLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        dateFilterLabel.setTextFill(Color.web("#5D48D7"));
        this.date = new DatePicker();
        this.date.getEditor().setDisable(true);
        this.date.setPromptText("Choisissez une date de fin");
            
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
        this.date.setDayCellFactory(startSaleCellFactory);
        this.date.setEffect(ds);
        this.date.setPrefHeight(50);
        this.date.setPrefWidth(350);
        this.date.setStyle("-fx-control-inner-background: #F8F8F8");
        this.date.setBackground(new Background(new BackgroundFill(Color.web("#F8F8F8"),CornerRadii.EMPTY,Insets.EMPTY)));

        dateFilter.getChildren().addAll(dateFilterLabel,this.date);

        // Ajout du bouton rechercher
        VBox searchButtonBox = new VBox();
        Button searchButton = new Button("Rechercher");
        searchButton.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        searchButton.setPadding(new Insets(10,30,10,30));
        searchButton.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
        searchButton.setOnAction(new ControleurRecherche(this.appli,this)); //ACTUALISER LA LISTE this.searchResult
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
        this.discoverContent = new VBox();
        this.discoverLabelContainer = new VBox();
        this.discoverLabel = new Label("Découvrez");
        this.discoverLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        this.discoverLabel.setPadding(new Insets(0,0,50,00));
        this.discoverLabel.setTextFill(Color.web("#5D48D7"));
        this.discoverLabel.setAlignment(Pos.TOP_LEFT);
        
        
        this.discoverItemsContainer = new VBox();
        this.discoverItems = new TilePane();
        this.discoverItems.setHgap(50);
        this.discoverItems.setVgap(50);
        this.discoverLabelContainer.getChildren().add(this.discoverLabel);
        this.discoverItemsContainer.getChildren().add(this.discoverItems);
        this.discoverContent.setPadding(new Insets(200,0,35,100));
        this.discoverContent.getChildren().addAll(discoverLabelContainer,this.discoverItemsContainer);

        this.setTop(searchContent);
        afficheVentes(ventesEnCours);
    }

    public void afficheVentes(List<Vente> ventes) {
        this.discoverItems.getChildren().clear();
    
        for (int i=0;i<ventes.size();++i) {
            // int j = (int) (Math.random()*ventesEnCours.size());
            // List<Integer> indexs = new ArrayList<>();
            // indexs.add(j);
            // while (indexs.contains(j)) j = (int) (Math.random()*ventesEnCours.size());
            // indexs.add(j);

            Vente actualVente = ventes.get(i);

            //Une vente
            VBox item = new VBox();

            //Image
            VBox picContainer = new VBox();
            this.ventesEnCours.get(i);
            ImageView pic;
            try {
                List<Photo> liste =this.appli.getPhotoBD().rechercherPhotosParObjet(actualVente.getObjet());
                System.out.println(actualVente.getObjet().getLesPhotos());
                for(Photo ph : liste){
                    actualVente.getObjet().ajoutePhoto(ph);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                System.out.println(actualVente.getObjet().getLesPhotos());
                pic = new ImageView(actualVente.getObjet().getLesPhotos().get(0).getImg());
            } catch (Exception e) {
                System.out.println("erreur");
                pic=new ImageView(new Image("file:./img/blank.png"));
            }
            pic.setFitWidth(440);
            pic.setPreserveRatio(true);
            picContainer.getChildren().add(pic);
            picContainer.setPadding(new Insets(0,0,50,0));

            //Informations
            GridPane informations = new GridPane();

            //Titre
            Label actualTitle = new Label(String.valueOf(actualVente.getObjet().getNom())); //Get le prix de la vente
            actualTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            actualTitle.setTextFill(Color.web("#5D48D7"));
            actualTitle.setAlignment(Pos.BASELINE_RIGHT);
    
            //Prix actuel
            Label actualPriceLabel = new Label("Prix actuel :");
            actualPriceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            actualPriceLabel.setTextFill(Color.web("black"));
            int actualPriceValue = this.ventesEnCours.get(i).getPrixBase();
            try {
                Enchere e = this.appli.getVenteBD().derniereEnchere(actualVente);
                if (e != null){
                    actualPriceValue = e.getMontant();
                }
            }
            catch(SQLException ex) {}
            Label actualPrice = new Label(String.valueOf(actualPriceValue)); //Get le prix de la vente
            actualPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            actualPrice.setTextFill(Color.web("#5D48D7"));
            actualPrice.setAlignment(Pos.BASELINE_RIGHT);

            //Temps restant
            Label remainTimeLabel = new Label("Temps restant :");
            remainTimeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            remainTimeLabel.setTextFill(Color.web("black"));
            Label remainTime = new Label(actualVente.tempsRestant()); //temps restants
            remainTime.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            remainTime.setTextFill(Color.web("#5D48D7"));
            remainTime.setAlignment(Pos.BASELINE_RIGHT);

            //Nombre d'enchères
            Label nbEncheresLabel = new Label("Nombre d'enchères :");
            nbEncheresLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            nbEncheresLabel.setTextFill(Color.web("black"));
            int enchereCount = 0;
            try {
                enchereCount = this.appli.getVenteBD().nbTotalEnchereSurUneVente(actualVente);
            }
            catch(SQLException ex) {}
            Label nbEnchere = new Label(String.valueOf(enchereCount)); //Get nombre d'enchère (j)
            nbEnchere.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            nbEnchere.setTextFill(Color.web("#5D48D7"));
            nbEnchere.setAlignment(Pos.BASELINE_RIGHT);

            //Button
            Tooltip bidToolTip = new Tooltip(String.valueOf(actualVente.getIdentifiant()));
            VBox buttonContainer = new VBox();
            Button buttonItem = new Button("Enchérir");
            buttonItem.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            buttonItem.setPadding(new Insets(10,30,10,30));
            buttonItem.setBackground(new Background(new BackgroundFill(Color.web("#FEE159"),CornerRadii.EMPTY,Insets.EMPTY)));
            Vente v = actualVente;
            buttonItem.setOnAction((key) -> this.appli.fenetreEnchere(v,this));
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
            this.discoverItems.getChildren().add(item);
        }
        this.setCenter(this.discoverContent);
    }


    public String getRecherche() {
        return this.search.getText();
    }

    public String getCategorie() {
        return this.CBfilters.getValue();
    }

    public String getMontantMax() {
        return this.price.getText();
    }

    public LocalDate getDateMax() {
        return this.date.getValue();
    }
}