package fi.ounai.nyssetulee.ui;

import fi.ounai.nyssetulee.api.RouteAPI;
import fi.ounai.nyssetulee.api.StopAPI;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUI {
    private Scanner scanner;
    private PrintStream out;
    
    public TextUI(Scanner scanner, PrintStream out, RouteAPI routeAPI, StopAPI stopAPI) {
        this.scanner = scanner;
        this.out = out;
    }
    
    public void launch() {
        showHelp();
        
        while(true) {
            out.print("> ");
            
            String[] command = getCommand();
            
            if(command[0].equals("help")) {
                showHelp();
            } else if(command[0].equals("routesearch")) {
                // TODO
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
        out.println("\tstopsearch <search term> - search for stops");
        out.println("\talerts - display ongoing alerts");
        out.println("\thelp - display this help");
        out.println("\texit - exit from the application");
    }
    
    private String[] getCommand() {
        // Read a command from scanner
        
        String command = scanner.nextLine();
        return command.split(" ");
    }
}
