package fi.ounai.nyssetulee.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlertTest {
    
    private Alert alert1, alert2, alert3;
    
    public AlertTest() {
        alert1 = new Alert("alertHeaderText test", "alertDescriptionText test");
        alert2 = new Alert("header", null);
        alert3 = new Alert(null, "description");
    }
    
    @Test
    public void gettersReturnCorrectValues() {
        assertEquals("alertHeaderText test", alert1.getAlertHeaderText());
        assertEquals("alertDescriptionText test", alert1.getAlertDescriptionText());
    }
    
    @Test
    public void toStringProducesCorrectString() {
        assertEquals("alertHeaderText test: alertDescriptionText test", alert1.toString());
        assertEquals("header", alert2.toString());
        assertEquals("description", alert3.toString());
    }
    
}
