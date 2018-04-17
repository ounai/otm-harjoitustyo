package fi.ounai.nyssetulee.domain;

import fi.ounai.nyssetulee.api.TestAlertAPI;
import fi.ounai.nyssetulee.api.TestRouteAPI;
import fi.ounai.nyssetulee.api.TestStopAPI;
import fi.ounai.nyssetulee.ui.TextUI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextUITest {
    
    private TextUI textUI;
    
    private String testWithInput(String input) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        
        TestRouteAPI routeAPI = new TestRouteAPI();
        TestStopAPI stopAPI = new TestStopAPI();
        TestAlertAPI alertAPI = new TestAlertAPI();
        
        TextUI textUI = new TextUI(scanner, out, routeAPI, stopAPI, alertAPI);
        
        textUI.launch();
        
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    @Test
    public void uiWillLaunchAndTerminate() {
        String output = testWithInput("exit");
        assertThat(output, CoreMatchers.containsString("NysseTulee"));
    }
    
}
