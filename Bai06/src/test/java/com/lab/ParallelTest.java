package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParallelTest {
    @Test
    void test1() throws InterruptedException { Thread.sleep(2000); assertTrue(true); }
    @Test
    void test2() throws InterruptedException { Thread.sleep(2000); assertTrue(true); }
    @Test
    void test3() throws InterruptedException { Thread.sleep(2000); assertTrue(true); }
    @Test
    void test4() throws InterruptedException { Thread.sleep(2000); assertTrue(true); }
}