package com.sollisar.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class RuleEngineTests {
    RuleEngine re_nowrap = new RuleEngine(false);
    RuleEngine re_wrap = new RuleEngine(true);

    @Test
    public void TestBoardWrapSettings01() {
        assertTrue(re_nowrap.getWrapping() == false);
    }

    @Test
    public void TestBoardWrapSettings02() {
        assertTrue(re_wrap.getWrapping() == true);
    }

    @Test
    public void TestdeadOrAliveRulesetForLivingCell() {
        assertTrue(re_wrap.deadOrAlive(1, true) == false);
        assertTrue(re_wrap.deadOrAlive(2, true) == true);
        assertTrue(re_wrap.deadOrAlive(3, true) == true);
        assertTrue(re_wrap.deadOrAlive(4, true) == false);
    }

    @Test
    public void TestdeadOrAliveRulesetForDeadCell() {
        assertTrue(re_wrap.deadOrAlive(1, false) == false);
        assertTrue(re_wrap.deadOrAlive(2, false) == false);
        assertTrue(re_wrap.deadOrAlive(3, false) == true);
        assertTrue(re_wrap.deadOrAlive(4, false) == false);
    }

    @Test public void NeighbourCoordsFromTheMiddle() {
        ArrayList<MyPoint> result = re_nowrap.getNeighbourCoordinates(new MyPoint(5, 5), 100);

        assertTrue(result.size() == 8);
    }

    // Wrap off
    @Test public void NeighbourCoordsNoWrapOnLeftSide() {
        ArrayList<MyPoint> result = re_nowrap.getNeighbourCoordinates(new MyPoint(0, 5), 100);

        assertTrue(result.size() == 5);
    }

    @Test public void NeighbourCoordsNoWrapRightSide() {
        ArrayList<MyPoint> result = re_nowrap.getNeighbourCoordinates(new MyPoint(99, 5), 100);

        assertTrue(result.size() == 5);
    }

    @Test
    public void NeighbourCoordsNoWrapCornerTopLeft() {
        ArrayList<MyPoint> result = re_nowrap.getNeighbourCoordinates(new MyPoint(0, 0), 100);

        assertTrue(result.size() == 3);
    }
    @Test
    public void NeighbourCoordsNoWrapCornerTopRight() {
        ArrayList<MyPoint> result = re_nowrap.getNeighbourCoordinates(new MyPoint(99, 0), 100);

        assertTrue(result.size() == 3);
    }
    @Test
    public void NeighbourCoordsNoWrapCornerBottomLeft() {
        ArrayList<MyPoint> result = re_nowrap.getNeighbourCoordinates(new MyPoint(0, 99), 100);

        assertTrue(result.size() == 3);
    }
    @Test
    public void NeighbourCoordsNoWrapCornerBottomRight() {
        ArrayList<MyPoint> result = re_nowrap.getNeighbourCoordinates(new MyPoint(99, 99), 100);

        assertTrue(result.size() == 3);
    }

    // Wrap on
    @Test public void NeighbourCoordsWrapOnLeftSide() {
        ArrayList<MyPoint> result = re_wrap.getNeighbourCoordinates(new MyPoint(0, 5), 100);

        assertTrue(result.size() == 8);
    }

    @Test public void NeighbourCoordsWrapRightSide() {
        ArrayList<MyPoint> result = re_wrap.getNeighbourCoordinates(new MyPoint(99, 5), 100);

        assertTrue(result.size() == 8);
    }

    @Test
    public void NeighbourCoordsWrapCornerTopLeft() {
        ArrayList<MyPoint> result = re_wrap.getNeighbourCoordinates(new MyPoint(0, 0), 100);

        assertTrue(result.size() == 8);
    }
    @Test
    public void NeighbourCoordsWrapCornerTopRight() {
        ArrayList<MyPoint> result = re_wrap.getNeighbourCoordinates(new MyPoint(99, 0), 100);

        assertTrue(result.size() == 8);
    }
    @Test
    public void NeighbourCoordsWrapCornerBottomLeft() {
        ArrayList<MyPoint> result = re_wrap.getNeighbourCoordinates(new MyPoint(0, 99), 100);

        assertTrue(result.size() == 8);
    }
    @Test
    public void NeighbourCoordsWrapCornerBottomRight() {
        ArrayList<MyPoint> result = re_wrap.getNeighbourCoordinates(new MyPoint(99, 99), 100);

        assertTrue(result.size() == 8);
    }
}
