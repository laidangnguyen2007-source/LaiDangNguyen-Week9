package com.lab;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @Test
    void testGetSafeFilePath() {
        FileHandler handler = new FileHandler();
        String result = handler.getSafeFilePath("docs", "readme.txt");
        
        String expected = "docs" + File.separator + "readme.txt";
        assertEquals(expected, result);
    }
}
