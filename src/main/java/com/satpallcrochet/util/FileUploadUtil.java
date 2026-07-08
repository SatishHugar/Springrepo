package com.satpallcrochet.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Component
public class FileUploadUtil {

    @Value("${app.upload.dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file, String subDirectory) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String uploadDirPath = uploadDir + subDirectory;
        File dir = new File(uploadDirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = generateUniqueFileName(file.getOriginalFilename());
        Path filePath = Paths.get(uploadDirPath, fileName);
        Files.write(filePath, file.getBytes());

        log.info("File uploaded successfully: {}", filePath);
        return subDirectory + "/" + fileName;
    }

    public void deleteFile(String filePath) throws IOException {
        String fullPath = uploadDir + filePath;
        Path path = Paths.get(fullPath);
        if (Files.exists(path)) {
            Files.delete(path);
            log.info("File deleted successfully: {}", fullPath);
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String extension = "";
        if (originalFileName != null && originalFileName.lastIndexOf(".") > 0) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        return UUID.randomUUID().toString() + extension;
    }

}
