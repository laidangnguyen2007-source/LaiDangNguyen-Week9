package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(20, calc.multiply(4, 5));
    }
}
