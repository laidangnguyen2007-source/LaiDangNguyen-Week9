package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

    @Test
    void testAdd() {
        MathUtils utils = new MathUtils();
        assertEquals(5, utils.add(2, 3));
    }
}
