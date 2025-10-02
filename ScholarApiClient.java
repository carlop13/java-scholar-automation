import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScholarApiClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        String authorId = "z_vS-Y0AAAAJ"; //Alan Turing
        String apiKey = "40dc14212f3327df40583ec1fe5480f60422f5556db31e9ce27911a731b1bbb6";

        // Construct the request URL in a single line
        String url = String.format(
            "https://serpapi.com/search.json?engine=google_scholar_author&author_id=%s&api_key=%s",
            authorId, apiKey
        );

        // Create an HTTP client and request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response status and body
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}