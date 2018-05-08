package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Alert;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AlertsView implements View {
    
    private GraphicalUI graphicalUI;
    private Scene scene;

    public AlertsView(GraphicalUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }
    
    @Override
    public Scene getScene() throws Exception {
        return scene;
    }
    
    @Override
    public void generate() throws Exception {
        VBox view = new VBox();
        
        Alert[] alerts = graphicalUI.getAlertAPI().getAlerts();
        
        if (alerts.length == 0) {
            view.getChildren().add(new Label("No alerts."));
        } else for (Alert alert : alerts) {
            view.getChildren().add(new Label(alert.toString()));
        }
        
        Button backButton = new Button("Back");
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(graphicalUI.getMainView(), true);
        });
        
        view.getChildren().add(backButton);
        
        scene = new Scene(view);
    }
    
}
