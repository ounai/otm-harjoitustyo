package fi.ounai.nyssetulee.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents the time a transit vehicle stops at a stop.
 */

public class Stoptime {
    
    private int scheduledArrival, scheduledDeparture, realtimeArrival, realtimeDeparture, arrivalDelay, departureDelay;
    private boolean timepoint, realtime;
    private Trip trip;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        
        return (realtime ? "[live] " : "") + trip.toString() + " @ " + dateFormat.format(getDate().getTime());
    }
    
    /**
     * Convert the stoptime's midnight-based time into a Calendar object.
     * 
     * @return A Calendar object, set on the time of the stoptime
     */
    public Calendar getDate() {
        Calendar startOfDay = Calendar.getInstance(),
                result = Calendar.getInstance();
        
        startOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startOfDay.set(Calendar.MINUTE, 0);
        startOfDay.set(Calendar.SECOND, 0);
        startOfDay.set(Calendar.MILLISECOND, 0);
        
        int offset = realtimeDeparture * 1000;
        
        result.setTimeInMillis(startOfDay.getTimeInMillis() + offset);
        
        return result;
    }
    
}
