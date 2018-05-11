package fi.ounai.nyssetulee.ui.graphical;

import fi.ounai.nyssetulee.NysseTulee;
import fi.ounai.nyssetulee.api.AlertAPI;
import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import fi.ounai.nyssetulee.database.ProfileStopDao;
import fi.ounai.nyssetulee.ui.UI;
import javafx.application.Application;
import javafx.stage.Stage;

public class GraphicalUI extends Application implements UI {
    
    private Stage window;
    private RouteAPI routeAPI;
    private StopAPI stopAPI;
    private AlertAPI alertAPI;
    private ProfileStopDao profileStopDao;
    private View mainView, routeSearchView, stopSearchView, alertsView;
    
    private void initAPIs() {
        routeAPI = NysseTulee.getRouteAPI();
        stopAPI = NysseTulee.getStopAPI();
        alertAPI = NysseTulee.getAlertAPI();
        profileStopDao = NysseTulee.getProfileStopDao();
    }
    
    @Override
    public void launch() {
        launch(GraphicalUI.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        
        initAPIs();
        
        mainView = new MainView(this);
        mainView.generate();
        
        routeSearchView = new RouteSearchView(this);
        stopSearchView = new StopSearchView(this);
        alertsView = new AlertsView(this);
        
        window.setScene(mainView.getScene());
        window.setTitle("NysseTulee");
        window.setWidth(800);
        window.setHeight(600);
        window.show();
    }
    
    public void changeView(View view, boolean generate) {
        try {
            if (generate) {
                view.generate();
            }
            
            window.setScene(view.getScene());
        } catch (Exception ex) {
            new ExceptionWindow(this, ex);
        }
    }
    
    public void close() {
        window.close();
    }

    public RouteAPI getRouteAPI() {
        return routeAPI;
    }

    public StopAPI getStopAPI() {
        return stopAPI;
    }

    public AlertAPI getAlertAPI() {
        return alertAPI;
    }

    public ProfileStopDao getProfileStopDao() {
        return profileStopDao;
    }

    public View getMainView() {
        return mainView;
    }

    public View getRouteSearchView() {
        return routeSearchView;
    }

    public View getStopSearchView() {
        return stopSearchView;
    }

    public View getAlertsView() {
        return alertsView;
    }
    
}
