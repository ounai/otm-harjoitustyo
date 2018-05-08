package fi.ounai.nyssetulee.ui;

import fi.ounai.nyssetulee.NysseTulee;
import fi.ounai.nyssetulee.api.AlertAPI;
import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import fi.ounai.nyssetulee.database.ProfileStopDao;
import fi.ounai.nyssetulee.domain.Alert;
import fi.ounai.nyssetulee.domain.Profile;
import fi.ounai.nyssetulee.domain.Route;
import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A textual UI for the application.
 */

public class TextUI implements UI {
    
    private Scanner scanner;
    private PrintStream out;
    private RouteAPI routeAPI;
    private StopAPI stopAPI;
    private AlertAPI alertAPI;
    private ProfileStopDao profileStopDao;
    
    public TextUI(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
        
        routeAPI = NysseTulee.getRouteAPI();
        stopAPI = NysseTulee.getStopAPI();
        alertAPI = NysseTulee.getAlertAPI();
        profileStopDao = NysseTulee.getProfileStopDao();
    }
    
    /**
     * Launches the UI, shows a help message on its usage, then starts listening for commands.
     */
    @Override
    public void launch() {
        showHelp();
        
        while (true) {
            String[] command = getCommand();
            String parameters = String.join(" ", Arrays.copyOfRange(command, 1, command.length));
            
            boolean continueExecution = handleCommand(command[0], parameters);
            
            if (!continueExecution) {
                break;
            }
        }
    }
    
    private boolean handleCommand(String command, String parameters) {
        if (command.equals("help")) {
            showHelp();
        } else if (command.equals("routesearch")) {
            routeSearch(parameters);
        } else if (command.equals("stopsearch")) {
            stopSearch(parameters);
        } else if (command.equals("alerts")) {
            showAlerts();
        } else if (command.equals("profile")) {
            showProfile(parameters);
        } else if (command.equals("exit")) {
            return false;
        } else {
            showUnknownCommand();
        }
        
        return true;
    }
    
    private void showProfile(String profileName) {
        if (profileName.length() > 20) {
            out.println("Profile name too long.");
        } else if (profileName.contains(" ")) {
            out.println("Invalid profile name.");
        } else {
            try {
                List<Stop> stops = profileStopDao.findStopsByProfile(new Profile(profileName));
                
                if (stops.isEmpty()) {
                    out.println("No stops in profile " + profileName);
                } else {
                    out.println(stops.size() + " stop" + (stops.size() == 1 ? "" : "s") + "\n");
                    
                    for (Stop stop : stops) {
                        showStopInformation(stop);
                        out.println();
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void showAlerts() {
        try {
            Alert[] alerts = alertAPI.getAlerts();
            
            if (alerts.length == 0) {
                out.println("No alerts.");
            } else {
                out.println(alerts.length + " alert" + (alerts.length == 1 ? "" : "s"));
                
                for (Alert alert : alerts) {
                    out.println(alert.toString());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void stopSearch(String searchTerm) {
        try {
            Stop[] stops = stopAPI.getStops(searchTerm);
            
            if (stops.length == 0) {
                out.println("No stops found.");
            } else {
                out.println("Found " + stops.length + " stop" + (stops.length == 1 ? "" : "s"));
                
                for (int i = 0; i < stops.length; i++) {
                    out.println("[" + i + "] " + stops[i].toString());
                }
                
                handleCommandAfterStopSearch(stops);
            }
        } catch (Exception ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void routeSearch(String searchTerm) {
        try {
            Route[] routes = routeAPI.getRoutes(searchTerm);

            if (routes.length == 0) {
                out.println("No routes found.");
            } else {
                out.println("Found " + routes.length + " route" + (routes.length == 1 ? "" : "s"));

                for (Route route : routes) {
                    out.println(route.toString());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showHelp() {
        out.println("NysseTulee");
        out.println();
        out.println("Available commmands:");
        out.println("\troutesearch <search term> - search for routes");
        out.println("\tstopsearch <search term> - search for stops");
        out.println("\talerts - display transit alerts");
        out.println("\tprofile <profile name> - show list of stops associated with a profile");
        out.println("\thelp - display this help");
        out.println("\texit - exit from the application");
    }
    
    private void showUnknownCommand() {
        // TODO help not always available
        out.println("Unknown command. Type \"help\" for a list of commands.");
    }
    
    private void showStopInformation(Stop stop) {
        try {
            out.println("Stop " + stop.getName());
            
            Stoptime[] stoptimes = stopAPI.getStoptimes(stop.getGtfsId());
            
            if (stoptimes.length == 0) {
                out.println("No departures.");
            } else {
                out.println("Next departures:");
                
                for (Stoptime stoptime : stoptimes) {
                    out.println(stoptime.toString());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handleCommandAfterStopInformation(Stop stop) throws Exception {
        out.println("Type \"refresh\" to refresh stop information, "
                + "\"add <profile name>\" to add this stop to a profile, "
                + "or \"back\" to go back to the main menu.");
        
        String[] command = getCommand();
        
        if (command[0].equals("refresh")) {
            showStopInformation(stop);
            handleCommandAfterStopInformation(stop);
        } else if (command[0].equals("add")) {
            String profileName = command[1];
            
            if (profileName.length() > 20) {
                out.println("Profile name too long. Please try something shorter.");
                handleCommandAfterStopInformation(stop);
            } else {
                profileStopDao.addStopToProfile(stop, new Profile(profileName));
            }
        } else if (!command[0].equals("back")) {
            handleCommandAfterStopInformation(stop);
        }
    }
    
    private void handleCommandAfterStopSearch(Stop[] stops) throws Exception {
        out.println("Choose stop by index or type \"back\" to go back.");
        
        while (true) {
            String[] command = getCommand();
            
            if (command[0].equals("back")) {
                break;
            } else {
                try {
                    int index = Integer.parseInt(command[0]);

                    if (index < 0 || index >= stops.length) {
                        out.println("Enter a number between 0 and " + (stops.length - 1) + " or \"back\" to go back.");
                    } else {
                        showStopInformation(stops[index]);
                        handleCommandAfterStopInformation(stops[index]);
                        
                        break;
                    }
                } catch (NumberFormatException e) {
                    showUnknownCommand();
                    break;
                }
            }
        }
    }
    
    private String[] getCommand() {
        out.print("> ");
        
        String command = scanner.nextLine();
        return command.split(" ");
    }
    
}
