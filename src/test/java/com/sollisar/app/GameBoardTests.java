package com.sollisar.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GameBoardTests {

    @Test
    public void testCountNeighbours() {
        GameBoard b = new GameBoard(10);
        RuleEngine re = new RuleEngine(false);

        MyPoint p1 = new MyPoint(5, 5),
                p2 = new MyPoint(5, 6);

        MyPoint origin = new MyPoint(4, 5);

        b.flipEntity(p1);
        b.flipEntity(p2);
        int num = b.countNeighbours(origin, re);
        assertTrue(num == 2);
    }

    @Test
    public void testFlipEntity() {
        GameBoard b = new GameBoard(10);
        MyPoint p = new MyPoint(2, 2);

        b.flipEntity(p);
        assertTrue(b.getEntity(p) == true);
        b.flipEntity(p);
        assertTrue(b.getEntity(p.x, p.y) != true);
    }

    @Test
    public void testGetBoardSizeExact() {
        GameBoard b = new GameBoard(10);
        assertTrue(b.getBoardSize() == 10);
    }

    @Test
    public void testGetBoardSizeDefaultValue() {
        GameBoard x = new GameBoard(2);
        assertTrue(x.getBoardSize() == 80);
    }

    @Test
    public void testGameBoardTick() {
        GameBoard b = new GameBoard(10);

        b.flipEntity(new MyPoint(5, 4));
        b.flipEntity(new MyPoint(5, 5));
        b.flipEntity(new MyPoint(5, 6));
        b.tick(new RuleEngine(true));

        assertTrue(b.getEntity(5, 5) && b.getEntity(4, 5) && b.getEntity(6, 5) && !b.getEntity(5, 4) && !b.getEntity(5, 6));
    }

    @Disabled
    public void testRandomizeBoardFillage() {
        int size = 10;
        int count = 0;
        int percentage = 75;

        GameBoard b = new GameBoard(size);

        b.randomizeBoard(percentage);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (b.getEntity(x, y)) {
                    count++;
                }
            }
        }
        assertTrue(count >= 70 && count <= 80);
    }
}
