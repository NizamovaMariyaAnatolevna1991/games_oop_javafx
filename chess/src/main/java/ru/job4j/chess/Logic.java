package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import java.util.Arrays;

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        if (source == null
                || source.getX() < 1
                || source.getX() > 8
                || source.getY() < 1
                || source.getY() > 8) {
            throw new IllegalArgumentException("Invalid source cell: " + source);
        }
        if (dest == null
                || dest.getX() < 1
                || dest.getX() > 8
                || dest.getY() < 1
                || dest.getY() > 8) {
            throw new IllegalArgumentException("Invalid destination cell: " + dest);
        }

        int index = findBy(source);

        Cell[] steps = figures[index].way(dest);
        if (steps == null || steps.length == 0) {
            throw new ImpossibleMoveException("No way found from " + source + " to " + dest);
        }

        if (findBy(dest) != -1) {
            throw new OccupiedCellException();
        }

        free(steps);

        figures[index] = figures[index].copy(dest);
    }

    private boolean free(Cell[] steps) throws OccupiedCellException, FigureNotFoundException {
        if (steps == null || steps.length == 0) {
            throw new ImpossibleMoveException("No steps provided for the move.");
        }
        for (Cell step : steps) {
            if (findBy(step) != -1) {
                throw new OccupiedCellException();
            }
        }
        return true;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Figure not found on the board.");
    }
}
