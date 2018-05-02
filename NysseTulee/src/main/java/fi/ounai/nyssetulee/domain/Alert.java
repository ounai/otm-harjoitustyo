package fi.ounai.nyssetulee.domain;

/**
 * Represents an alert affecting transit.
 */

public class Alert {
    
    private final String alertHeaderText, alertDescriptionText;

    public Alert(String alertHeaderText, String alertDescriptionText) {
        this.alertHeaderText = alertHeaderText;
        this.alertDescriptionText = alertDescriptionText;
    }

    public String getAlertHeaderText() {
        return alertHeaderText;
    }

    public String getAlertDescriptionText() {
        return alertDescriptionText;
    }

    @Override
    public String toString() {
        String result = "";
        
        if (getAlertHeaderText() != null) {
            result += getAlertHeaderText();
            
            if (getAlertDescriptionText() != null) {
                result += ": ";
            }
        }
                    
        if (getAlertDescriptionText() != null) {
            result += getAlertDescriptionText();
        }

        return result;
    }
    
}
