package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Route;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RouteSearchResultsView implements View {
    
    private GraphicalUI graphicalUI;
    private View lastView;
    private Route[] routes;
    private Scene scene;

    public RouteSearchResultsView(GraphicalUI graphicalUI, View lastView, Route[] routes) {
        this.graphicalUI = graphicalUI;
        this.lastView = lastView;
        this.routes = routes;
    }
    
    @Override
    public Scene getScene() throws Exception {
        return scene;
    }

    @Override
    public void generate() throws Exception {
        VBox view = new VBox();
        
        VBox routesVBox = constructRoutesVBox();
        
        ScrollPane routesScrollPane = new ScrollPane();
        routesScrollPane.setContent(routesVBox);
        
        Button backButton = new Button("Back");
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(lastView, false);
        });
        
        view.getChildren().addAll(routesScrollPane, backButton);
        
        scene = new Scene(view);
    }
    
    private VBox constructRoutesVBox() {
        VBox routesVBox = new VBox();
        
        for (Route route : routes) {
            HBox routeHBox = new HBox();
            
            Button viewRouteButton = new Button("View");
            Label routeLabel = new Label(route.toString());
            
            viewRouteButton.setOnAction(event -> {
                try {
                    Route routeWithStops = graphicalUI.getRouteAPI().getRoute(route.getGtfsId());
                    View routeView = new RouteView(graphicalUI, this, routeWithStops);
                    graphicalUI.changeView(routeView, true);
                } catch (Exception ex) {
                    Logger.getLogger(RouteSearchResultsView.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            routeHBox.getChildren().addAll(viewRouteButton, routeLabel);
            
            routesVBox.getChildren().add(routeHBox);
        }
        
        return routesVBox;
    }
    
}
