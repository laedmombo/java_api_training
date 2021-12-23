package fr.lernejo.navy_battle.logic;

public interface IPlayer extends Shootable {
    void GenerateShips( char beginRow, int beginCol, char endRow, int endCol );
    boolean hasShipLeft();
    String name();
}
