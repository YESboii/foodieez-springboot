package com.ayush.foodiez.service;

import com.ayush.foodiez.model.Category;
import com.ayush.foodiez.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createUpdate(Product product, int categoryId, int vendorId, MultipartFile file,int type);
   Optional<Product> findById(int id,int categoryId,int vendorId);

   void deleteById(int id,int categoryId,int vendorId);

    boolean existsById(int id,int categoryId,int vendorId);

    Page<Product> findAll(int vendorId, Pageable pageable);
}
