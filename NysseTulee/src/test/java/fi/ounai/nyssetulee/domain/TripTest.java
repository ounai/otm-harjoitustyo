package fi.ounai.nyssetulee.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class TripTest {
    
    private Trip trip;
    
    public TripTest() {
        try {
            Route route = new Route("HSL:test", "route shortName", "route longName", "route mode");
            trip = new Trip("gtfsId test", "tripHeadsign test", "0", route);
        } catch (UnsupportedAgencyException ex) {
            Logger.getLogger(TripTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void toStringReturnsCorrectString() {
        assertEquals("route shortName -> tripHeadsign test", trip.toString());
    }
    
}
