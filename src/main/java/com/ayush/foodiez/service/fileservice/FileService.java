package com.ayush.foodiez.service.fileservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String addImage(String path, MultipartFile file)throws IOException;
}
