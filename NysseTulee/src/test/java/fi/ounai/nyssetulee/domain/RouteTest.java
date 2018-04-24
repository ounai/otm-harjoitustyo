package fi.ounai.nyssetulee.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class RouteTest {
    
    private Route route;
    
    public RouteTest() {
        route = new Route("shortName test", "longName test");
    }
    
    @Test
    public void gettersReturnCorrectValues() {
        assertEquals("shortName test", route.getShortName());
        assertEquals("longName test", route.getLongName());
    }
    
    @Test
    public void toStringReturnsCorrectString() {
        assertEquals("shortName test longName test", route.toString());
    }
    
}
