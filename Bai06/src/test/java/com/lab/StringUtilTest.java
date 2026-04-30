package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {

    @Test
    void testIsBlank() {
        StringUtil util = new StringUtil();
        assertTrue(util.isBlank("   "));
        assertFalse(util.isBlank("abc"));
    }
}
