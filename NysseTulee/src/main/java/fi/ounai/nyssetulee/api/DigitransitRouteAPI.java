package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Route;

public class DigitransitRouteAPI implements RouteAPI {
    
    private final String API_URL;

    public DigitransitRouteAPI(String API_URL) {
        this.API_URL = API_URL;
    }

    @Override
    public Route[] getRoutes(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Route[] getRoutes(String name, String modes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
