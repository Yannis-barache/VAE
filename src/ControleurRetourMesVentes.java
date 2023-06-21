import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


public class ControleurRetourMesVentes implements EventHandler<ActionEvent>{
    
    ApplicationVAE appli;

    public ControleurRetourMesVentes(ApplicationVAE appli) {
        this.appli=appli;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Création de vente");
        alert.setHeaderText("Création de la vente réussi");
        alert.setContentText("Votre vente a bien été mis en ligne");
        alert.showAndWait();
        this.appli.fenetreMesVentes();
        
    }
}
