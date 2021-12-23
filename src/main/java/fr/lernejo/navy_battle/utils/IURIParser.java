package fr.lernejo.navy_battle.utils;

import java.util.Set;

public interface IURIParser {
    Set<String> GetParameters();
    String GetValueOf(String parameter);
}
