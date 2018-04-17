package fi.ounai.nyssetulee;

import fi.ounai.nyssetulee.api.AlertAPI;
import fi.ounai.nyssetulee.api.DigitransitAlertAPI;
import fi.ounai.nyssetulee.api.DigitransitRouteAPI;
import fi.ounai.nyssetulee.api.DigitransitStopAPI;
import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import fi.ounai.nyssetulee.ui.TextUI;
import java.util.Scanner;

public class NysseTulee {
    
    private static final String apiUrl = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
    
    public static void main(String[] args) {
        // Initialize the APIs
        
        RouteAPI routeAPI = new DigitransitRouteAPI(apiUrl);
        StopAPI stopAPI = new DigitransitStopAPI(apiUrl);
        AlertAPI alertAPI = new DigitransitAlertAPI(apiUrl);
        
        // Create and launch a textual UI
        
        Scanner scanner = new Scanner(System.in);
        
        TextUI ui = new TextUI(scanner, System.out, routeAPI, stopAPI, alertAPI);
        ui.launch();
    }
    
}
