package ru.job4j.chess.firuges.black;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class BishopBlackTest {

    @Test
    void position() {
        BishopBlack bishopBlack = new BishopBlack(Cell.H5);
        assertThat(bishopBlack.position()).isEqualTo(Cell.H5);
    }

    @Test
    void copy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.H5);
        BishopBlack copiedbishopBlack = (BishopBlack) bishopBlack.copy(Cell.A2);
        assertThat(copiedbishopBlack.position()).isEqualTo(Cell.A2);
    }

    @Test
    void way() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] steps = bishopBlack.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertArrayEquals(expected, steps);
    }

    @Test
    void invalidDiagonalMove() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C3);
        Cell destination = Cell.D3;

        Exception exception = assertThrows(ImpossibleMoveException.class, () -> {
            bishopBlack.way(destination);
        });

        org.hamcrest.MatcherAssert.assertThat(exception.getMessage(), containsString("Could not move by diagonal from C3 to D3"));
    }
}