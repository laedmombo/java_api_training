package fr.lernejo.navy_battle.logic;

public final class GameBoard {
    private final Player _player1;
    private final Player _player2;

    public GameBoard() {
        _player1 = new Player("Player1");
        _player2 = new Player("Player2");
        _player1.GenerateShips('A', 0, (char) ('A' + 5), 10);
        _player2.GenerateShips((char) ('A' + 5), 0, (char) ('A' + 10), 10);
    }


    public IPlayer getPlayer1() {
        return _player1;
    }

    public IPlayer getPlayer2() {
        return _player2;
    }
}

interface Viewable {
    void visualize(char[][] sea);
}
