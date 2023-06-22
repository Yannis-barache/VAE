import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ControleurPannelAdministration implements EventHandler<ActionEvent>{
    
    ApplicationVAE appli;

    public ControleurPanelAdministration(ApplicationVAE appli) {
        this.appli=appli;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.appli.fenetrePanelAdministration();
        
    }
}
