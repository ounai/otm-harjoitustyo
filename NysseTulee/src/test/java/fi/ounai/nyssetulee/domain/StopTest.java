package fi.ounai.nyssetulee.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class StopTest {
    
    private Stop stop, stopNoDesc, stopNullDesc;
    
    public StopTest() {
        try {
            stop = new Stop("HSL:test", "code test", "name test", "desc test", "url test");
            stopNoDesc = new Stop("HSL:test", "code test", "name test", "name test", "url test");
            stopNullDesc = new Stop("HSL:test", "code test", "name test", null, "url test");
        } catch (UnsupportedAgencyException ex) {
            Logger.getLogger(StopTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void gettersReturnCorrectValues() {
        assertEquals("code test", stop.getCode());
        assertEquals("name test", stop.getName());
        assertEquals("desc test", stop.getDesc());
        assertEquals("url test", stop.getUrl());
        assertEquals("HSL:test", stop.getGtfsId());
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
    
    @Test(expected=UnsupportedAgencyException.class)
    public void constructorThrowsUnsupportedAgencyExceptionWhenNotInHSLRegion() throws UnsupportedAgencyException {
        new Stop("NOTHSL:test", null, null, null, null);
    }

}
