package fr.lernejo.navy_battle.game;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.logic.GameBoard;
import fr.lernejo.navy_battle.logic.IPlayer;
import fr.lernejo.navy_battle.logic.Shoot;
import fr.lernejo.navy_battle.utils.URIParser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public final class GameFireHandler implements HttpHandler {
    private final GameBoard _board;
    private final IPlayer _player1;
    private final IPlayer _player2;


    public GameFireHandler(GameBoard board) {
        _board = board;
        _player1 = board.getPlayer1();
        _player2 = board.getPlayer2();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URIParser parser = new URIParser(exchange.getRequestURI());
        String cell = parser.GetValueOf("cell");
        String res = "";
        res = getHitResult(cell);
        JSONObject json = createJSon(res);
        createResponse(exchange, json);
    }

    private String getHitResult(String cell) {
        Shoot hit = _player1.hit(cell.charAt(0), Integer.parseInt(cell.charAt(1) + ""));
        HashMap<Shoot, String> map = new HashMap<>();
        map.put(Shoot.Hit, "hit");
        map.put(Shoot.Sunk, "sunk");
        map.put(Shoot.Miss, "miss");
        return map.get(hit);
    }

    private JSONObject createJSon(String res) {
        JSONObject json = new JSONObject();
        json.put("consequence", res);
        json.put("shipLeft", _player2.hasShipLeft());
        json.put("Content-Type", "application/json");
        return json;
    }

    private void createResponse(HttpExchange exchange, JSONObject json) throws IOException {
        String response = json.toString(4) + "\n" + _board.toString();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
