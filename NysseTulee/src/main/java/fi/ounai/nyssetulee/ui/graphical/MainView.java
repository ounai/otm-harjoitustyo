package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.domain.Profile;
import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;
import java.util.List;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView implements View {
    
    private GraphicalUI graphicalUI;
    private Scene scene;
    private VBox nextStoptimes;
    private ComboBox profileComboBox;

    public MainView(GraphicalUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }
    
    @Override
    public Scene getScene() throws Exception {
        return scene;
    }
    
    @Override
    public void generate() throws Exception {
        VBox view = new VBox();
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        VBox favoritesVBox = constructFavoritesVBox(),
                buttonsVBox = constructButtonsVBox();
        
        view.getChildren().addAll(favoritesVBox, separator, buttonsVBox);
        
        scene = new Scene(view);
    }
    
    private VBox constructFavoritesVBox() throws Exception {
        VBox favoritesVBox = new VBox();
        
        HBox controlHBox = new HBox();
        Button refreshButton = new Button("Refresh");
        
        profileComboBox = new ComboBox();
        profileComboBox.setPromptText("Choose profile...");
        
        refreshButton.setOnAction(event -> {
            updateNextStoptimes();
        });
        
        profileComboBox.setOnAction(event -> {
            updateNextStoptimes();
        });
        
        nextStoptimes = new VBox();
        
        List<Profile> profiles = graphicalUI.getProfileStopDao().getProfiles();
        
        for (Profile profile : profiles) {
            profileComboBox.getItems().add(profile.getName());
        }
        
        nextStoptimes.getChildren().add(new Label("Choose a profile to view its stops."));
        
        controlHBox.getChildren().addAll(profileComboBox, refreshButton);
        favoritesVBox.getChildren().addAll(controlHBox, nextStoptimes);
        
        return favoritesVBox;
    }
    
    private void updateNextStoptimes() {
        try {
            String profile = (String) profileComboBox.getValue();
            
            if (profile.isEmpty()) {
                return;
            }
            
            nextStoptimes.getChildren().clear();
            nextStoptimes.getChildren().add(constructStoptimesForProfile(profile));
        } catch (Exception ex) {
            new ExceptionWindow(graphicalUI, ex);
        }
    }
    
    private VBox constructButtonsVBox() {
        VBox buttons = new VBox();
        
        Button routeSearchButton = new Button("Route search"),
                stopSearchButton = new Button("Stop search"),
                alertsButton = new Button("Alerts"),
                exitButton = new Button("Exit");
        
        routeSearchButton.setOnAction(event -> {
            graphicalUI.changeView(graphicalUI.getRouteSearchView(), true);
        });
        
        stopSearchButton.setOnAction(event -> {
            graphicalUI.changeView(graphicalUI.getStopSearchView(), true);
        });
        
        alertsButton.setOnAction(event -> {
            graphicalUI.changeView(graphicalUI.getAlertsView(), true);
        });
        
        exitButton.setOnAction(event -> {
            graphicalUI.close();
        });
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        buttons.getChildren().addAll(routeSearchButton, stopSearchButton, alertsButton, separator, exitButton);
    
        return buttons;
    }
    
    private VBox constructStoptimesForProfile(String profileName) throws Exception {
        VBox stoptimesVBox = new VBox();
        
        List<Stop> stops = graphicalUI.getProfileStopDao().findStopsByProfile(new Profile(profileName));
        
        for (Stop stop : stops) {
            Label stopLabel = new Label(stop.toString());
            stopLabel.setStyle("-fx-font-weight: bold;");
            
            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            
            stoptimesVBox.getChildren().addAll(stopLabel, separator);
            
            Stoptime[] stoptimes = graphicalUI.getStopAPI().getStoptimes(stop.getGtfsId());
            
            for (Stoptime stoptime : stoptimes) {
                Label stoptimeLabel = new Label(stoptime.toString());
                stoptimesVBox.getChildren().add(stoptimeLabel);
            }
        }
        
        return stoptimesVBox;
    }
    
}
