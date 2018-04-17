package fi.ounai.nyssetulee.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class StopTest {
    
    private Stop stop;
    
    public StopTest() {
        stop = new Stop("code test", "name test", "desc test", "url test");
    }
    
    @Test
    public void gettersReturnCorrectValues() {
        assertEquals(stop.getCode(), "code test");
        assertEquals(stop.getName(), "name test");
        assertEquals(stop.getDesc(), "desc test");
        assertEquals(stop.getUrl(), "url test");
    }

}
