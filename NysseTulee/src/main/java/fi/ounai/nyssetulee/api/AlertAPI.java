package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Alert;

/**
 * Interface to an API for fetching information about transit alerts.
 */

public interface AlertAPI {
    
    /**
     * Fetch a list of current transit alerts.
     * 
     * @return An array of alert objects, representing the alerts.
     * @throws Exception 
     */
    Alert[] getAlerts() throws Exception;
    
}
