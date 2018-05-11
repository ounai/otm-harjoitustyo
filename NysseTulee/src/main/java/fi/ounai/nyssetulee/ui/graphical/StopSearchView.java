package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Stop;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StopSearchView implements View {
    
    private GraphicalUI graphicalUI;
    private Scene scene;

    public StopSearchView(GraphicalUI graphicalUI) {
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
                return;
            }
            
            try {
                Stop[] stops = graphicalUI.getStopAPI().getStops(searchTerm);
                StopSearchResultsView resultsView = new StopSearchResultsView(graphicalUI, this, stops);
                graphicalUI.changeView(resultsView, true);
            } catch (Exception ex) {
                new ExceptionWindow(graphicalUI, ex);
            }
        });
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(graphicalUI.getMainView(), true);
        });
        
        view.getChildren().addAll(searchTextField, searchButton, backButton);
        
        scene = new Scene(view);
    }
    
}
