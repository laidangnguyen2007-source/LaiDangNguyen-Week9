package com.lab;

import java.io.File;

public class FileHandler {

    public String getSafeFilePath(String folder, String filename) {
        // Cách an toàn: Luôn dùng File.separator để tự động sinh đúng dấu gạch chéo theo HĐH
        return folder + File.separator + filename;
    }
}