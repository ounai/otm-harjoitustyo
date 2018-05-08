package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Route;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RouteSearchView implements View {
    
    private GraphicalUI graphicalUI;
    private Scene scene;

    public RouteSearchView(GraphicalUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }
    
    @Override
    public Scene getScene() throws Exception {
        return scene;
    }

    @Override
    public void generate() throws Exception {
        VBox view = new VBox();
        
        TextField searchTextField = new TextField();
        Button searchButton = new Button("Search");
        Button backButton = new Button("Back");
        
        searchButton.setOnAction(event -> {
            String searchTerm = searchTextField.getText();
            
            if (searchTerm.isEmpty()) {
                // TODO warn user
                return;
            }
            
            try {
                Route[] routes = graphicalUI.getRouteAPI().getRoutes(searchTerm);
                RouteSearchResultsView resultsView = new RouteSearchResultsView(graphicalUI, this, routes);
                graphicalUI.changeView(resultsView, true);
            } catch (Exception ex) {
                Logger.getLogger(RouteSearchView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(graphicalUI.getMainView(), true);
        });
        
        view.getChildren().addAll(searchTextField, searchButton, backButton);
        
        scene = new Scene(view);
    }
    
}
