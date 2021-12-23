package fr.lernejo.navy_battle.utils;

import java.net.URI;
import java.util.HashMap;
import java.util.Set;

public final class URIParser implements IURIParser {
    private final HashMap<String, String> _map;

    public URIParser( URI uri ) {
        _map = new HashMap<>();
        _Parse(uri);
    }

    private void _Parse( URI uri ) {
        String query = uri.getQuery();

        String[] tokens = query.split("&");
        for (String token : tokens) {
            String key, value;
            String[] keyValue = token.split("=");
            key = keyValue[0];
            value = keyValue[1];
            _map.put(key, value);
        }

    }

    @Override
    public Set<String> GetParameters() {
        return _map.keySet();
    }

    @Override
    public String GetValueOf( String parameter ) {
        return _map.get(parameter);
    }
}
