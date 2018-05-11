package fi.ounai.nyssetulee.api;

import com.google.gson.GsonBuilder;
import fi.ounai.nyssetulee.domain.Route;
import fi.ounai.nyssetulee.domain.TransitDataJsonDeserializer;

public class DigitransitRouteAPI implements RouteAPI {
    
    private String apiUrl;
    
    private String getRouteQuery(String gtfsId) {
        return "{\n"
                    + "route(id: \"" + gtfsId + "\") {\n"
                        + "gtfsId\n"
                        + "shortName\n"
                        + "longName\n"
                        + "mode\n"
                        + "stops {\n"
                            + "name\n"
                            + "desc\n"
                            + "url\n"
                            + "code\n"
                            + "gtfsId\n"
                        + "}\n"
                    + "}\n"
                + "}";
    }
    
    public DigitransitRouteAPI(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    
    /**
     * Fetch a list of transit routes that match a given name.
     * 
     * @param name The name (or a part of it) to search for
     * @return An array of route objects, that match the search
     * @throws Exception 
     */
    @Override
    public Route[] getRoutes(String name) throws Exception {
        String query = "{\n"
                    + "routes(name: \"" + name + "\") {\n"
                        + "gtfsId\n"
                        + "shortName\n"
                        + "longName\n"
                        + "mode\n"
                    + "}\n"
                + "}";
        
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Route[] deserialized = new GsonBuilder()
                .registerTypeAdapter(Route[].class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Route[].class);
        
        return deserialized;
    }
    
    /**
     * Fetch a single route based on gtfsId and return it.
     * 
     * @param gtfsId The gtfsId of the route
     * @return a route matching the gtfsId
     * @throws Exception 
     */
    @Override
    public Route getRoute(String gtfsId) throws Exception {
        String query = getRouteQuery(gtfsId);
        String json = new GraphQLAPIQuery(apiUrl, query).execute();
        
        Route deserialized = new GsonBuilder()
                .registerTypeAdapter(Route.class, new TransitDataJsonDeserializer())
                .create()
                .fromJson(json, Route.class);
        
        return deserialized;
    }
    
}
