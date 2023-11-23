package com.ayush.foodiez.service.fileservice.impl;

import com.ayush.foodiez.service.fileservice.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String addImage(String path, MultipartFile file)throws IOException {
        boolean flag = true;
        // 1) name of file,2)path where the file must be saved, 3)copy the img
        String fileNameToBeUploaded = file.getOriginalFilename();
        String fileName =  UUID.randomUUID() + fileNameToBeUploaded;
        String filePath =  path + '/' + fileName;
        System.out.println(filePath);

        File file1 = new File(path);

        if (!file1.exists()){
            System.out.println("insidde mkdir");
            file1.mkdirs();
        }
        try {
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException exception){
            exception.printStackTrace();
            flag = false;
        }
        if (flag) return fileName;

        return "";
    }

    @Override
    public String updateImage(String path, MultipartFile file, String oldName) throws IOException {

        String pathFile = path + "/" +oldName;
        System.out.println("updateimgg "+pathFile);

        try {
            Files.copy(file.getInputStream(), Paths.get(pathFile), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        return oldName;
    }

    @Override
    public void deleteImage(String path, String fileName) {
        String filePath = path + "/" +fileName;
        try{
            Files.delete(Paths.get(filePath));
        }
        catch (IOException fileNotFoundException){
            System.out.println("No such file found");
        }

    }
}
