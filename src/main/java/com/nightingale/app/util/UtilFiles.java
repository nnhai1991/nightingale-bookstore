package com.nightingale.app.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UtilFiles {

    private static String generateRandomFilePath(String originalFileName, int retryCount) {

        String extension = FilenameUtils.getExtension(originalFileName);
        String baseName = FilenameUtils.getBaseName(originalFileName);

        StringBuilder sb = new StringBuilder();
        sb.append(baseName)
          .append("/")
          .append(baseName);

        if (retryCount > 0)
            sb.append("_").append(retryCount);

        sb.append(".")
          .append(extension);

        return sb.toString();
    }

    public static String getUniqueFilePath(MultipartFile file, String baseDir) {

        File newFile;

        String path = "";

        int retryCount = 0;

        do {
            path = generateRandomFilePath(file.getOriginalFilename(), retryCount++);
            newFile = new File(baseDir + path);
        } while (newFile.exists());

        return path;
    }

    public static String getUniqueFilePath(String fileName, String baseDir) {

        File newFile;

        String path = "";
        int retryCount = 0;

        do {
            path = generateRandomFilePath(fileName, retryCount++);
            newFile = new File(baseDir + path);
        } while (newFile.exists());

        return path;
    }


}
