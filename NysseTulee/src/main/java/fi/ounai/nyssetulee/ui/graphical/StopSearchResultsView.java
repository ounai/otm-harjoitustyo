package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Stop;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StopSearchResultsView implements View {
    
    private GraphicalUI graphicalUI;
    private View lastView;
    private Stop[] stops;
    private Scene scene;

    public StopSearchResultsView(GraphicalUI graphicalUI, View lastView, Stop[] stops) {
        this.graphicalUI = graphicalUI;
        this.lastView = lastView;
        this.stops = stops;
    }

    @Override
    public Scene getScene() throws Exception {
        return scene;
    }
    
    @Override
    public void generate() throws Exception {
        VBox view = new VBox();
        
        if (stops.length == 0) {
            Label noStopsLabel = new Label("No stops found.");
            view.getChildren().add(noStopsLabel);
        }
        
        for (Stop stop : stops) {
            HBox stopHBox = new HBox();
            
            Button viewStopButton = new Button("View");
            Label stopLabel = new Label(stop.toString());
            
            viewStopButton.setOnAction(event -> {
                StopView stopView = new StopView(graphicalUI, this, stop);
                graphicalUI.changeView(stopView, true);
            });
            
            stopHBox.getChildren().addAll(viewStopButton, stopLabel);
            view.getChildren().add(stopHBox);
        }
        
        Button backButton = new Button("Back");
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(lastView, false);
        });
        
        view.getChildren().add(backButton);
        
        scene = new Scene(view);
    }
    
}
