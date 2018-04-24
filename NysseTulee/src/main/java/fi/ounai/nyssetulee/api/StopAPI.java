package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;

public interface StopAPI {
    
    Stop[] getStops(String name) throws Exception;
    public Stoptime[] getStoptimes(String gtfsId) throws Exception;
    
}
