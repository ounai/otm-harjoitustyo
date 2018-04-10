package fi.ounai.nyssetulee.api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GraphQLAPIQuery {
    
    private String API_URL;
    private String queryString;

    public GraphQLAPIQuery(String API_URL, String queryString) {
        this.API_URL = API_URL;
        this.queryString = queryString;
    }
    
    public String execute() throws Exception {
        URL url = new URL(API_URL);
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.addRequestProperty("Content-Type", "application/graphql");
        connection.setFixedLengthStreamingMode(queryString.length());
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(queryString.getBytes(StandardCharsets.UTF_8));
            
            try (InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                
                StringBuilder response = new StringBuilder();
                String responseLine;
                
                while((responseLine = bufferedReader.readLine()) != null) {
                    response.append(responseLine);
                }
                
                return response.toString();
            }
        }
    }
    
}
