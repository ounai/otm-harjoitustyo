package fi.ounai.nyssetulee;

import fi.ounai.nyssetulee.api.AlertAPI;
import fi.ounai.nyssetulee.api.DigitransitAlertAPI;
import fi.ounai.nyssetulee.api.DigitransitRouteAPI;
import fi.ounai.nyssetulee.api.DigitransitStopAPI;
import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import fi.ounai.nyssetulee.database.Database;
import fi.ounai.nyssetulee.database.DatabaseProfileDao;
import fi.ounai.nyssetulee.database.DatabaseProfileStopDao;
import fi.ounai.nyssetulee.database.DatabaseStopDao;
import fi.ounai.nyssetulee.database.ProfileDao;
import fi.ounai.nyssetulee.database.ProfileStopDao;
import fi.ounai.nyssetulee.database.StopDao;
import fi.ounai.nyssetulee.ui.TextUI;
import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NysseTulee {
    
    private static String apiUrl = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
    
    private static String createTableStopQuery = "CREATE TABLE IF NOT EXISTS Stop ("
                    + "gtfsid varchar(20) PRIMARY KEY,"
                    + "code varchar(10),"
                    + "name varchar(200),"
                    + "desc varchar(200),"
                    + "url varchar(200)"
                    + ")";
    
    private static String createTableProfileQuery = "CREATE TABLE IF NOT EXISTS Profile ("
                    + "name varchar(20) PRIMARY KEY"
                    + ")";
    
    private static String createTableProfileStopQuery = "CREATE TABLE IF NOT EXISTS ProfileStop ("
                    + "stop_gtfsid varchar(20),"
                    + "profile_name varchar(20),"
                    + "FOREIGN KEY (stop_gtfsid) REFERENCES Stop(gtfsid),"
                    + "FOREIGN KEY (profile_name) REFERENCES Profile(name)"
                    + ")";
    
    public static void main(String[] args) {
        // Initialize database
        
        File databaseDirectory = new File("db");
        if (!databaseDirectory.exists()) databaseDirectory.mkdir();
        
        File databaseFile = new File("db", "nyssetulee.db");
        Database database = new Database("jdbc:sqlite:" + databaseFile.getAbsolutePath());
        
        // Create database tables if they aren't already present
        
        try {
            database.executeUpdate(createTableStopQuery);
            database.executeUpdate(createTableProfileQuery);
            database.executeUpdate(createTableProfileStopQuery);
        } catch (SQLException ex) {
            Logger.getLogger(NysseTulee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Create DAOs
        
        ProfileDao profileDao = new DatabaseProfileDao(database);
        StopDao stopDao = new DatabaseStopDao(database);
        ProfileStopDao profileStopDao = new DatabaseProfileStopDao(database, profileDao, stopDao);
        
        // Initialize the APIs
        
        RouteAPI routeAPI = new DigitransitRouteAPI(apiUrl);
        StopAPI stopAPI = new DigitransitStopAPI(apiUrl);
        AlertAPI alertAPI = new DigitransitAlertAPI(apiUrl);
        
        // Create and launch a textual UI
        
        Scanner scanner = new Scanner(System.in);
        
        TextUI ui = new TextUI(scanner, System.out, routeAPI, stopAPI, alertAPI, database, profileStopDao);
        ui.launch();
    }
    
}
