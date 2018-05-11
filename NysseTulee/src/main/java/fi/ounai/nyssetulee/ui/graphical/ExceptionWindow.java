package fi.ounai.nyssetulee.ui.graphical;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExceptionWindow {
    
    private Exception exception;
    private Stage window;

    public ExceptionWindow(GraphicalUI graphicalUI, Exception exception) {
        this.exception = exception;
        
        createWindow();
        
        graphicalUI.close();
    }
    
    private void createWindow() {
        window = new Stage();
        window.setTitle("Error!");
        window.setScene(createScene());
        window.show();
    }
    
    private Scene createScene() {
        VBox view = new VBox();
        
        Label errorLabel = new Label("An exception has stopped the program's execution.");
        errorLabel.setStyle("-fx-font-weight: bold;");
        
        Label stackTraceHeaderLabel = new Label("Technical information:");
        
        TextArea stackTraceTextArea = new TextArea(getStackTrace());
        stackTraceTextArea.setEditable(false);
        
        Button closeButton = new Button("Close");
        
        closeButton.setOnAction(event -> {
            window.close();
        });
        
        view.getChildren().addAll(errorLabel, stackTraceHeaderLabel, stackTraceTextArea, closeButton);
        
        return new Scene(view);
    }
    
    private String getStackTrace() {
        StringWriter stringWriter = new StringWriter();
        
        exception.printStackTrace(new PrintWriter(stringWriter));
        
        return stringWriter.toString();
    }
    
}
