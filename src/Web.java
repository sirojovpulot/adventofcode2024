package src;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Web {
    private final String url;
    private final String cookie;

    public Web(String url) {
        this.url = url;
        this.cookie = "_ga=GA1.2.1493749078.1732785177; session=53616c7465645f5f16b8da3aea4cfb3331b0a256f300c6d4a15624abda589af6f88df4dc9fb7dfead97acd066cb5d39f68e5a41b9edad498c2d02a14e5118086; _gid=GA1.2.521828887.1733019197; _ga_MHSNPJKWC7=GS1.2.1733033425.3.1.1733033430.0.0.0";
    }

    public String content() {
        try {
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Build the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(this.url))
                    .header("Cookie", cookie)
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response body
            return response.body();
        } catch (Exception e) {
            return "";
        }
    }
}
