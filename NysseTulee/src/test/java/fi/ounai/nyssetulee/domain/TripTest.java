package fi.ounai.nyssetulee.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class TripTest {
    
    private Trip trip;
    
    public TripTest() {
        Route route = new Route("route shortName", "route longName");
        trip = new Trip("gtfsId test", "tripHeadsign test", "0", route);
    }
    
    @Test
    public void toStringReturnsCorrectString() {
        assertEquals("route shortName -> tripHeadsign test", trip.toString());
    }
    
}
