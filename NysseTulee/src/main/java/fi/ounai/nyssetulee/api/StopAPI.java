package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;

/**
 * Interface to an API for fetching information about transit stops.
 */

public interface StopAPI {
    
    /**
     * Fetch a list of transit stops that match a given name.
     * 
     * @param name The name (or a part of it) to search for
     * @return An array of stop objects, that match the search.
     * @throws Exception 
     */
    Stop[] getStops(String name) throws Exception;
    
    /**
     * Fetch a list of the next vehicles leaving from a stop, as expressed by stoptimes.
     * 
     * @param gtfsId The unique GTFS id of the stop
     * @return An array of stoptime objects for the stop
     * @throws Exception 
     */
    Stoptime[] getStoptimes(String gtfsId) throws Exception;
    
}
