package fr.lernejo.navy_battle.logic;

import java.util.Arrays;

public final class GameBoard implements Viewable {
    public final int ROW_NUMBER = 10;
    public final int COL_NUMBER = 10;
    private final Player _player1;
    private final Player _player2;

    public GameBoard() {
        _player1 = new Player("Player1");
        _player2 = new Player("Player2");
        _player1.GenerateShips('A', 0, (char) ('A' + 5), 10);
        _player2.GenerateShips((char) ('A' + 5), 0, (char) ('A' + 10), 10);
    }

    public String toString() {
        char[][] sea = new char[ROW_NUMBER][COL_NUMBER];
        for (int i = 0; i < sea.length; i++) {
            Arrays.fill(sea[i], ' ');
        }
        visualize(sea);
        String res = "";
        res = getUpAndDown(res);
        res = getMiddle(sea, res);
        res = getUpAndDown(res);
        return res;
    }

    private String getMiddle(char[][] sea, String res) {
        StringBuilder resBuilder = new StringBuilder(res);
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COL_NUMBER; j++) {
                resBuilder.append("|  ").append(sea[i][j]).append("  ");
            }
            resBuilder.append("|\n");
        }
        res = resBuilder.toString();
        return res;
    }

    private String getUpAndDown(String res) {
        res = res + " -----".repeat(COL_NUMBER);
        res += "\n";
        return res;
    }

    @Override
    public void visualize(char[][] sea) {
        _player1.visualize(sea);
        _player2.visualize(sea);
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
