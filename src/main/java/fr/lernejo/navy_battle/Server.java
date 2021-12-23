package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.GameFireHandler;
import fr.lernejo.navy_battle.game.GameStartHandler;
import fr.lernejo.navy_battle.game.PingHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    public final String PATH_PING = "/ping";
    public final String PATH_GAME_START = "/api/game/start";
    public final String PATH_GAME_FIRE = "/api/game/fire";
    private final HttpServer _server;
    private final PingHandler _pingHandler;
    private final GameStartHandler _gameStartHandler;
    private final GameFireHandler _gameFireHandler;
    Server(int port) throws IOException {
        _server = HttpServer.create(new InetSocketAddress(port), 0);
        _server.setExecutor(Executors.newSingleThreadExecutor());
        _pingHandler = new PingHandler();
        _gameStartHandler = new GameStartHandler(port);
        _gameFireHandler = new GameFireHandler(_gameStartHandler.getBoard());
        _server.createContext(PATH_PING, _pingHandler);
        _server.createContext(PATH_GAME_START, _gameStartHandler);
        _server.createContext(PATH_GAME_FIRE, _gameFireHandler);
        _server.start();
    }

/*    public static JSONObject ToJson(InputStream stream) {
        try {
            JSONObject jsonObject = new JSONObject(new String(stream.readAllBytes()));
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/

}

