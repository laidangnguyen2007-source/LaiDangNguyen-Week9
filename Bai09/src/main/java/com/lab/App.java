package com.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        String user = "Admin";
        logger.info("User {} started the application.", user);
        
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("An error occurred while calculating.", e);
        }
    }
}
