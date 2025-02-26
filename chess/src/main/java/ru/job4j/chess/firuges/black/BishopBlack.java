package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }

        int x = position().getX();
        int y = position().getY();

        int deltaX = Integer.compare(dest.getX(), x);
        int deltaY = Integer.compare(dest.getY(), y);

        int size = Math.abs(dest.getX() - x);

        Cell[] steps = new Cell[size];
        for (int index = 0; index < size; index++) {
            x += deltaX;
            y += deltaY;
            steps[index] = Cell.findBy(x, y);
            if (steps[index] == null) {
                throw new ImpossibleMoveException("Invalid cell on the way");
            }
        }

        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        if (source == null || dest == null) {
            return false;
        }
        int dx = Math.abs(dest.getX() - source.getX());
        int dy = Math.abs(dest.getY() - source.getY());
        return dx == dy;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
