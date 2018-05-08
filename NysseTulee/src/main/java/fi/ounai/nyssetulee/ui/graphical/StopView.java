package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class StopView implements View {
    
    private GraphicalUI graphicalUI;
    private Scene scene;
    private View lastView;
    private Stop stop;

    public StopView(GraphicalUI graphicalUI, View lastView, Stop stop) {
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
        
        Label stopLabel = new Label(stop.toString());
        stopLabel.setStyle("-fx-font-weight: bold;");
        
        Separator separator1 = new Separator(), separator2 = new Separator();
        separator1.setOrientation(Orientation.HORIZONTAL);
        separator2.setOrientation(Orientation.HORIZONTAL);
        
        VBox stoptimes = constructStoptimesVBox();
        
        Button addFavoriteStopButton = new Button("Add to favorites"),
                backButton = new Button("Back");
        
        addFavoriteStopButton.setOnAction(event -> {
            AddFavoriteStopView addView = new AddFavoriteStopView(graphicalUI, this, stop);
            graphicalUI.changeView(addView, true);
        });
        
        backButton.setOnAction(event -> {
            graphicalUI.changeView(lastView, false);
        });
        
        view.getChildren().addAll(stopLabel, separator1, stoptimes, separator2, addFavoriteStopButton, backButton);
        
        scene = new Scene(view);
    }
    
    private VBox constructStoptimesVBox() throws Exception {
        VBox stoptimesVBox = new VBox();
        
        Stoptime[] stoptimes = graphicalUI.getStopAPI().getStoptimes(stop.getGtfsId());
        
        for (Stoptime stoptime : stoptimes) {
            Label stoptimeLabel = new Label(stoptime.toString());
            stoptimesVBox.getChildren().add(stoptimeLabel);
        }
        
        return stoptimesVBox;
    }
    
}
