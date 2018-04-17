package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Stop;

public class DigitransitStopAPI implements StopAPI {
    
    private final String apiUrl;

    public DigitransitStopAPI(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Stop[] getStops(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
