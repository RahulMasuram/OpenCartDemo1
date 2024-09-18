package utilities;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JiraUtils {
    private static final String JIRA_URL = "https://rahul-masuram.atlassian.net/";
    private static final String USERNAME = "rahul.masuram88@gmail.com";
    private static final String API_KEY = "";

    public static void createIssue(String summary, String description) {
        try {
            URL url = new URL(JIRA_URL + "/rest/api/2/issue");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((USERNAME + ":" + API_KEY).getBytes(StandardCharsets.UTF_8)));
            connection.setDoOutput(true);

            JSONObject requestBody = new JSONObject();
            requestBody.put("fields", new JSONObject()
                    .put("project", new JSONObject().put("key", "PROJ"))
                    .put("summary", summary)
                    .put("description", description)
                    .put("issuetype", new JSONObject().put("name", "Bug")));

            OutputStream os = connection.getOutputStream();
            os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == 201) {
                System.out.println("Issue created successfully.");
            } else {
                System.out.println("Failed to create issue. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}