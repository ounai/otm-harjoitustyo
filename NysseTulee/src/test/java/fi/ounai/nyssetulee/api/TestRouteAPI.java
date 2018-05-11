package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.domain.Route;
import fi.ounai.nyssetulee.domain.UnsupportedAgencyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dummy implementation of RouteAPI for testing purposes
 */

public class TestRouteAPI implements RouteAPI {

    private Route[] getTestRoutes(String name) {
        try {
            Route route1 = new Route("HSL:test1", "1", name + "1", "testMode");
            Route route2 = new Route("HSL:test2", "2", name + "2", "testMode");
            Route route3 = new Route("HSL:test3", "3", name + "3", "testMode");
            
            return new Route[] {
                route1, route2, route3
            };
        } catch (UnsupportedAgencyException ex) {
            Logger.getLogger(TestRouteAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    public Route[] getRoutes(String name) {
        return getTestRoutes(name);
    }

    @Override
    public Route getRoute(String gtfsId) throws Exception {
        return new Route(gtfsId, "shortName test", "longName test", "mode test");
    }
    
}
