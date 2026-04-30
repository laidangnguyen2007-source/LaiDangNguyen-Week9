package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {
    MathUtils utils = new MathUtils();

    @Test
    void testAdd() {
        assertEquals(5, utils.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(1, utils.subtract(3, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(6, utils.multiply(2, 3));
    }
}