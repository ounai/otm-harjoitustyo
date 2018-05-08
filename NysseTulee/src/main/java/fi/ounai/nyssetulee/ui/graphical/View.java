package fi.ounai.nyssetulee.ui.graphical;

import javafx.scene.Scene;

public interface View {
    
    Scene getScene() throws Exception;
    
    void generate() throws Exception;
    
}
