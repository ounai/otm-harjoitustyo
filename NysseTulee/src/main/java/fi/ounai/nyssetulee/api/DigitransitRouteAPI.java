package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Route;

public class DigitransitRouteAPI implements RouteAPI {
    
    private final String API_URL;

    public DigitransitRouteAPI(String API_URL) {
        this.API_URL = API_URL;
    }

    @Override
    public Route[] getRoutes(String name) throws Exception {
        String query = "{\n"
                    + "routes(name: \"" + name + "\") {\n"
                        + "shortName\n"
                        + "longName\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(API_URL, query).execute();
        
        // TODO find the correct way to do this
        json = json.substring(18, json.length()-2);
        
        Route[] serialized = new GsonBuilder().create().fromJson(json, Route[].class);
        
        return serialized;
    }

    @Override
    public Route[] getRoutes(String name, String modes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
