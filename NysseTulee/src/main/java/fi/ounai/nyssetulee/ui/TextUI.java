package fi.ounai.nyssetulee.ui;

import fi.ounai.nyssetulee.api.AlertAPI;
import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import fi.ounai.nyssetulee.domain.Alert;
import fi.ounai.nyssetulee.domain.Route;
import fi.ounai.nyssetulee.domain.Stop;
import fi.ounai.nyssetulee.domain.Stoptime;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextUI {
    
    private final Scanner scanner;
    private final PrintStream out;
    private final RouteAPI routeAPI;
    private final StopAPI stopAPI;
    private final AlertAPI alertAPI;
    
    public TextUI(Scanner scanner, PrintStream out, RouteAPI routeAPI, StopAPI stopAPI, AlertAPI alertAPI) {
        this.scanner = scanner;
        this.out = out;
        this.routeAPI = routeAPI;
        this.stopAPI = stopAPI;
        this.alertAPI = alertAPI;
    }
    
    public void launch() {
        showHelp();
        
        while (true) {
            String[] command = getCommand();
            String parameters = String.join(" ", Arrays.copyOfRange(command, 1, command.length));
            
            if (command[0].equals("exit")) {
                break;
            }
            
            handleCommand(command[0], parameters);
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
        } else {
            showUnknownCommand();
        }
        
        return true;
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
        out.println("\thelp - display this help");
        out.println("\texit - exit from the application");
    }
    
    private void showUnknownCommand() {
        out.println("Unknown command. Type \"help\" for a list of commands. ");
    }
    
    private void showStopInformation(Stop stop) {
        try {
            out.println("Stop " + stop.getName());
            
            
            Stoptime[] stoptimes = stopAPI.getStoptimes(stop.getGtfsId());
            
            if(stoptimes.length == 0) {
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
    
    private void handleCommandAfterStopSearch(Stop[] stops) {
        out.println("Choose stop by index or type \"back\" to go back.");
        
        String[] command = getCommand();
        
        while (true) {
            if (!command[0].equals("back")) {
                try {
                    int index = Integer.parseInt(command[0]);

                    if (index < 0 || index >= stops.length) {
                        out.println("Enter a number between 0 and " + stops.length + " or \"back\" to go back.");
                    } else {
                        showStopInformation(stops[index]);
                        
                        break;
                    }
                } catch(NumberFormatException e) {
                    showUnknownCommand();
                    break;
                }
            }
        }
    }
    
    private String[] getCommand() {
        // Read a command from scanner
        
        out.print("> ");
        
        String command = scanner.nextLine();
        return command.split(" ");
    }
    
}
