package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

    @Test
    void testSubtract() {
        MathUtils utils = new MathUtils();
        assertEquals(5, utils.subtract(10, 5));
    }
}
