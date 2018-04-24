package fi.ounai.nyssetulee.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class StopTest {
    
    private Stop stop, stopNoDesc, stopNullDesc;
    
    public StopTest() {
        stop = new Stop("code test", "name test", "desc test", "url test", "gtfsId test");
        stopNoDesc = new Stop("code test", "name test", "name test", "url test", "gtfsId test");
        stopNullDesc = new Stop("code test", "name test", null, "url test", "gtfsId test");
    }
    
    @Test
    public void gettersReturnCorrectValues() {
        assertEquals("code test", stop.getCode());
        assertEquals("name test", stop.getName());
        assertEquals("desc test", stop.getDesc());
        assertEquals("url test", stop.getUrl());
        assertEquals("gtfsId test", stop.getGtfsId());
    }
    
    @Test
    public void toStringReturnsCorrectString() {
        assertEquals("code test name test (desc test)", stop.toString());
        assertEquals("code test name test", stopNoDesc.toString());
        assertEquals("code test name test", stopNullDesc.toString());
    }
    
    @Test
    public void getStoptimesReturnsNullWhenNotSet() {
        assertNull(stop.getStoptimes());
    }

}
