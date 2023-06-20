import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class ControleurRetourPageConnec implements EventHandler<ActionEvent>{
    
    ApplicationVAE appli;

    public ControleurRetourPageConnec(ApplicationVAE appli) {
        this.appli=appli;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.appli.fenetreConnexion();
        
    }
}
