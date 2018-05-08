package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Route;

/**
 * Interface to an API for fetching information about transit routes.
 */

public interface RouteAPI {
    
    /**
     * Fetch a list of transit routes that match a given name.
     * 
     * @param name The name (or a part of it) to search for
     * @return An array of route objects, that match the search
     * @throws Exception 
     */
    Route[] getRoutes(String name) throws Exception;
    
    /**
     * Fetch a list of transit routes that match a given name and mode(s).
     * 
     * @param name The name (or a part of it) to search for
     * @param modes A comma-separated list of transit modes to limit the search by
     * @return An array of route objects, that match the search
     * @throws Exception 
     */
    Route[] getRoutes(String name, String modes) throws Exception;
    
    Route getRoute(String gtfsId) throws Exception;
    
}
