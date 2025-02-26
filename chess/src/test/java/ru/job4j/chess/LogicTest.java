package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {
    Logic logic = new Logic();

    public void setUp() {
        logic = new Logic();
        logic.clean(); // Очищаем доску перед каждым тестом
    }

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    void whenMoveDiagonalThenReturnCorrectSteps() throws OccupiedCellException, FigureNotFoundException {
        Figure bishop = new BishopBlack(Cell.C1);

        Cell[] steps = bishop.way(Cell.G5);

        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};

        assertArrayEquals(expected, steps);

    }
    @Test
    void whenMoveNotDiagonalThenThrowException() {
        BishopBlack bishop = new BishopBlack(Cell.C1);

        Exception exception = assertThrows(ImpossibleMoveException.class, () -> {
            bishop.way(Cell.D3);
        });

        assertTrue(exception.getMessage().contains("Could not move by diagonal from C1 to D3"));
    }
}