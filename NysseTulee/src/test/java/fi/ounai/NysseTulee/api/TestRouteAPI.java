package fi.ounai.NysseTulee.api;

import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.domain.Route;

/**
 * Dummy implementation of RouteAPI for testing purposes
 */

public class TestRouteAPI implements RouteAPI {

    @Override
    public Route[] getRoutes(String name) {
        Route route1 = new Route("1", name + "1");
        Route route2 = new Route("2", name + "2");
        Route route3 = new Route("3", name + "3");
        
        return new Route[] {
            route1, route2, route3
        };
    }

    @Override
    public Route[] getRoutes(String name, String modes) {
        Route route1 = new Route("1", name + "1");
        Route route2 = new Route("2", name + "2");
        Route route3 = new Route("3", name + "3");
        
        return new Route[] {
            route1, route2, route3
        };
    }
    
}
