package fi.ounai.NysseTulee.domain;

import fi.ounai.NysseTulee.api.TestRouteAPI;
import fi.ounai.NysseTulee.api.TestStopAPI;
import fi.ounai.nyssetulee.ui.TextUI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
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
        
        TextUI textUI = new TextUI(scanner, out, routeAPI, stopAPI);
        
        textUI.launch();
        
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    @Test
    public void uiWillLaunchAndTerminate() {
        String output = testWithInput("exit");
        assertThat(output, CoreMatchers.containsString("NysseTulee"));
    }
}
