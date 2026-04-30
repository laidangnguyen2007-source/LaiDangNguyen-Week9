package com.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    public int multiply(int a, int b) {
        logger.info("Multiplying {} and {}", a, b);
        return a * b;
    }
}
