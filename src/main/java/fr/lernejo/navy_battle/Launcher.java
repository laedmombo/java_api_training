package fr.lernejo.navy_battle;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Launcher {

    public final int CLIENT_PORT = 8795;

    public static void main(String[] args) {
        if (args.length == 1) {
            LaunchServer(args[0]);
        } else {
            if (args.length == 2) {
                LaunchClient(args[0], args[1]);
            } else {
                System.out.println("Invalid number of parameters");
            }
        }
    }

    private static final void LaunchClient(String port, String url) {
        HttpClient client = HttpClient.newHttpClient();
        JSONObject json = createJson(port, "1", "Hello I'm client");
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json.toString(4)))
            .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject createJson(String port, String id, String message) {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("url", "http://localhost:" + port);
        json.put("message", message);
        return json;
    }

    private static void LaunchServer(String portValue) {
        int port;
        try {
            port = Integer.parseInt(portValue);
            try {
                Server server = new Server(port);
                System.out.println("Server Ready");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error invalid port : " + portValue);
        }
    }
}
