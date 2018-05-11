package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Profile;
import fi.ounai.nyssetulee.domain.Stop;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddFavoriteStopView implements View {
    
    private GraphicalUI graphicalUI;
    private View lastView;
    private Stop stop;
    private Scene scene;

    public AddFavoriteStopView(GraphicalUI graphicalUI, View lastView, Stop stop) {
        this.graphicalUI = graphicalUI;
        this.lastView = lastView;
        this.stop = stop;
    }
    
    @Override
    public Scene getScene() throws Exception {
        return scene;
    }

    @Override
    public void generate() throws Exception {
        VBox view = new VBox();
        
        Label instructionLabel = new Label("Input profile to which the stop should be added (e.g. home)");
        
        TextField profileTextField = new TextField();
        
        Button addButton = new Button("Add"),
                backButton = new Button("Back");
        
        addButton.setOnAction(event -> {
            String profileName = profileTextField.getText();
            
            if (profileName.isEmpty() || profileName.length() > 20) {
                return;
            }
            
            try {
                graphicalUI.getProfileStopDao().addStopToProfile(stop, new Profile(profileName));
                graphicalUI.changeView(lastView, false);
            } catch (Exception ex) {
                new ExceptionWindow(graphicalUI, ex);
            }   
        });
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(lastView, false);
        });
        
        view.getChildren().addAll(instructionLabel, profileTextField, addButton, backButton);
        
        scene = new Scene(view);
    }
    
}
