package fi.ounai.nyssetulee;

import fi.ounai.nyssetulee.api.DigitransitRouteAPI;
import fi.ounai.nyssetulee.api.DigitransitStopAPI;
import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import fi.ounai.nyssetulee.ui.TextUI;
import java.util.Scanner;

public class NysseTulee {
    private static final String API_URL = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
    
    public static void main(String[] args) {
        // Initialize the APIs
        
        RouteAPI routeAPI = new DigitransitRouteAPI(API_URL);
        StopAPI stopAPI = new DigitransitStopAPI(API_URL);
        
        // Create and launch a textual UI
        
        Scanner scanner = new Scanner(System.in);
        
        TextUI ui = new TextUI(scanner, System.out, routeAPI, stopAPI);
        ui.launch();
    }
}
