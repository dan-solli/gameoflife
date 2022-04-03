package com.sollisar.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class MyPointTests {
    @Test
    public void testPointToString() {
        MyPoint p = new MyPoint(3, 1);

        assertTrue(p.toString().compareTo("(3, 1)") == 0);
    }
}
