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
     * Fetch a single route based on gtfsId and return it.
     * 
     * @param gtfsId The gtfsId of the route
     * @return a route matching the gtfsId
     * @throws Exception 
     */
    Route getRoute(String gtfsId) throws Exception;
    
}
