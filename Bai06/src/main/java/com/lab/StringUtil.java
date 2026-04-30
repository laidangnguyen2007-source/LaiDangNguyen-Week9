package com.lab;

public class StringUtil {
    public boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
