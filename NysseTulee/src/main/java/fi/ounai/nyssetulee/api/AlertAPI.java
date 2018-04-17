package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Alert;

public interface AlertAPI {
    
    Alert[] getAlerts() throws Exception;
    
}
