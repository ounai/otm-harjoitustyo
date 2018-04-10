package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Stop;

public class DigitransitStopAPI implements StopAPI {
    
    private final String API_URL;

    public DigitransitStopAPI(String API_URL) {
        this.API_URL = API_URL;
    }

    @Override
    public Stop[] getStops(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
