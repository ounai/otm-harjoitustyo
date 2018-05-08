package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Route;
import fi.ounai.nyssetulee.domain.Stop;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RouteView implements View {

    private GraphicalUI graphicalUI;
    private Scene scene;
    private View lastView;
    private Route route;

    public RouteView(GraphicalUI graphicalUI, View lastView, Route route) {
        this.graphicalUI = graphicalUI;
        this.lastView = lastView;
        this.route = route;
    }

    @Override
    public Scene getScene() throws Exception {
        return scene;
    }

    @Override
    public void generate() throws Exception {
        VBox view = new VBox();
        
        Label routeLabel = new Label(route.toString());
        routeLabel.setStyle("-fx-font-weight: bold;");
        
        Separator separator1 = new Separator(), separator2 = new Separator();
        separator1.setOrientation(Orientation.HORIZONTAL);
        separator2.setOrientation(Orientation.HORIZONTAL);
        
        VBox stops = constructStopsVBox();
        ScrollPane stopsScrollPane = new ScrollPane();
        stopsScrollPane.setContent(stops);
        
        Button backButton = new Button("Back");
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(lastView, false);
        });
        
        view.getChildren().addAll(routeLabel, separator1, stopsScrollPane, separator2, backButton);
        
        scene = new Scene(view);
    }
    
    private VBox constructStopsVBox() {
        VBox stopsVBox = new VBox();
        
        for (Stop stop : route.getStops()) {
            HBox stopHBox = new HBox();
            
            Button viewStopButton = new Button("View");
            Label stopLabel = new Label(stop.toString());
            
            viewStopButton.setOnAction(event -> {
                View stopView = new StopView(graphicalUI, this, stop);
                graphicalUI.changeView(stopView, true);
            });
            
            stopHBox.getChildren().addAll(viewStopButton, stopLabel);
            
            stopsVBox.getChildren().add(stopHBox);
        }
        
        return stopsVBox;
    }
    
}
