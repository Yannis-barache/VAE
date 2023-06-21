import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.SQLException;


public class ControleurEncherir implements EventHandler<ActionEvent>{
    private ApplicationVAE appli;
    private String nouvelleEnchere;

    public ControleurEncherir(ApplicationVAE appli,String nouvelleEnchere) {
        this.appli=appli;
        this.nouvelleEnchere = nouvelleEnchere;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(nouvelleEnchere);
    }
}
