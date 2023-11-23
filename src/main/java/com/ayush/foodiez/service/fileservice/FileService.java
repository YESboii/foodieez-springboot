package com.ayush.foodiez.service.fileservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String addImage(String path, MultipartFile file)throws IOException;
    String updateImage(String path, MultipartFile file,String oldName)throws IOException;
    void deleteImage(String path,String fileName);
}
