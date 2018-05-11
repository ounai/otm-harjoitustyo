package fi.ounai.nyssetulee.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class RouteTest {
    
    private Route route;
    
    public RouteTest() {
        try {
            route = new Route("HSL:test", "shortName test", "longName test", "mode test");
        } catch (UnsupportedAgencyException ex) {
            Logger.getLogger(RouteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void gettersReturnCorrectValues() {
        assertEquals("shortName test", route.getShortName());
        assertEquals("longName test", route.getLongName());
        assertEquals("HSL:test", route.getGtfsId());
    }
    
    @Test
    public void toStringReturnsCorrectString() {
        assertEquals("shortName test | mode test | longName test", route.toString());
    }
    
    @Test(expected=UnsupportedAgencyException.class)
    public void constructorThrowsUnsupportedAgencyExceptionWhenNotInHSLRegion() throws UnsupportedAgencyException {
        new Route("NOTHSL:test", null, null, null);
    }
    
}
