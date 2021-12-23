package fr.lernejo.navy_battle.logic;

public class Ship implements Shootable, Viewable {
    private final String[] positions;
    private final char _beginRow;
    private final int _beginCol;
    private final boolean _isVertical;

    public Ship( char beginRow, int beginCol, int size, boolean isVertical ) {
        positions = new String[size];
        _beginRow = beginRow;
        _beginCol = beginCol;
        _isVertical = isVertical;
        computePositions(size);
    }

    @Override
    public void visualize( char[][] sea ) {
        int i0 = _beginRow - 'A';
        if (_isVertical) {
            for (int i = 0; i < positions.length; i++) {
                sea[i0 + i][_beginCol] = positions[i].equals("X") ? 'X' : 'O';
            }
        } else {
            for (int i = 0; i < positions.length; i++) {
                sea[i0][_beginCol + i] = positions[i].equals("X") ? 'X' : 'O';
            }
        }
    }

    private void computePositions( int size ) {
        if (_isVertical) {
            for (int i = 0; i < size; i++) {
                positions[i] = ((char) (_beginRow + i)) + "" + _beginCol;
            }

        } else {
            for (int i = 0; i < size; i++) {
                positions[i] = _beginRow + "" + (_beginCol + i);
            }
        }
    }

    @Override
    public Shoot hit( char row, int col ) {
        for (int i = 0; i < positions.length; i++) {
            String position = positions[i];
            if (position.equals(row + "" + col)) {
                if (!position.equals("X")) {
                    positions[i] = "X";
                    if (iSDead()) {
                        return Shoot.Sunk;
                    }
                    return Shoot.Hit;
                }
            }
        }
        return Shoot.Miss;
    }

    public boolean iSDead() {
        for (String position : positions) {
            if (!position.equals("X")) {
                return false;
            }
        }
        return true;
    }
}

interface Shootable {
    Shoot hit( char row, int col );
}

