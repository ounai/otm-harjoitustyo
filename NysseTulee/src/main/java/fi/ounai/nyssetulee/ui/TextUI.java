package fi.ounai.nyssetulee.ui;

import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import fi.ounai.nyssetulee.domain.Route;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextUI {
    private Scanner scanner;
    private PrintStream out;
    private RouteAPI routeAPI;
    private StopAPI stopAPI;
    
    public TextUI(Scanner scanner, PrintStream out, RouteAPI routeAPI, StopAPI stopAPI) {
        this.scanner = scanner;
        this.out = out;
        this.routeAPI = routeAPI;
        this.stopAPI = stopAPI;
    }
    
    public void launch() {
        showHelp();
        
        while(true) {
            out.print("> ");
            
            String[] command = getCommand();
            
            if(command[0].equals("help")) {
                showHelp();
            } else if(command[0].equals("routesearch")) {
                String[] searchTermArray = Arrays.copyOfRange(command, 1, command.length);
                String searchTermString = String.join(" ", searchTermArray);
                
                try {
                    Route[] routes = routeAPI.getRoutes(searchTermString);
                    
                    if(routes.length == 0) {
                        System.out.println("No routes found.");
                    } else {
                        System.out.println("Found " + routes.length + " route" + (routes.length == 1 ? "" : "s"));
                        
                        for(Route route : routes) {
                            System.out.println(route.getShortName() + " " + route.getLongName());
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(command[0].equals("stopsearch")) {
                // TODO
            } else if(command[0].equals("alerts")) {
                // TODO
            } else if(command[0].equals("exit")) {
                break;
            } else {
                out.println("Unknown command. Type \"help\" for a list of commands. ");
            }
        }
    }
    
    private void showHelp() {
        out.println("NysseTulee");
        out.println();
        out.println("Available commmands:");
        out.println("\troutesearch <search term> - search for routes");
        //out.println("\tstopsearch <search term> - search for stops");
        //out.println("\talerts - display ongoing alerts");
        out.println("\thelp - display this help");
        out.println("\texit - exit from the application");
    }
    
    private String[] getCommand() {
        // Read a command from scanner
        
        String command = scanner.nextLine();
        return command.split(" ");
    }
}
