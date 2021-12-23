package fr.lernejo.navy_battle.game;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PingHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String out = "OK";

        exchange.sendResponseHeaders(200, out.length());

        OutputStream os = exchange.getResponseBody();
        os.write(out.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
