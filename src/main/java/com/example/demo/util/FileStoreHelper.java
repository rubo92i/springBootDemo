package com.example.demo.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@Component
public class FileStoreHelper {

    private String root = "C://users/ruben.manukyan/desktop/files/";

    public String buildName(String extension) {
        Calendar calendar = Calendar.getInstance();
        return root + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH)
                + "/" + UUID.randomUUID().toString() + "." + extension;
    }



    public String storeFile(File file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getName());
        String fileName = buildName(extension);
        FileUtils.copyFile(file,new File(fileName));
        return fileName;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String fileName = buildName(extension);
        FileUtils.writeByteArrayToFile(new File(fileName), multipartFile.getBytes());
        return fileName;
    }
}
