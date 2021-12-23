package fr.lernejo.navy_battle.logic;

import java.util.HashSet;
import java.util.Set;

public final class Player implements Viewable, IPlayer {
    private final Set<Ship> _ships = new HashSet<>();
    private final Set<Ship> _shipsLeft = new HashSet<>();
    private final String _name;

    public Player( String name ) {
        _name = name;
    }

    @Override
    public void GenerateShips( char beginRow, int beginCol, char endRow, int endCol ) {
        _ships.add(new Ship(beginRow, beginCol, 5, false));
        _ships.add(new Ship((char) (beginRow + 2), beginCol, 4, false));
        _ships.add(new Ship((char) (beginRow + 2), beginCol + 5, 3, true));
        _ships.add(new Ship((char) (beginRow + 2), beginCol + 7, 3, true));
        _ships.add(new Ship((char) (beginRow + 1), beginCol + 9, 2, true));
        for (Ship ship : _ships) {
            _shipsLeft.add(ship);
        }
    }

    @Override
    public boolean hasShipLeft() {
        return !_shipsLeft.isEmpty();
    }

    @Override
    public String name() {
        return _name;
    }

    @Override
    public Shoot hit( char row, int col ) {
        Shoot res = Shoot.Miss;
        for (Ship ship : _ships) {
            Shoot hit = ship.hit(row, col);
            if (hit != Shoot.Miss) {
                res = hit;
                if (res == Shoot.Sunk) {
                    _shipsLeft.remove(ship);
                }
                break;
            }
        }
        return res;
    }

    @Override
    public void visualize( char[][] sea ) {
        for (Ship ship : _ships) {
            ship.visualize(sea);
        }
    }

    @Override
    public String toString() {
        return _name;
    }
}
