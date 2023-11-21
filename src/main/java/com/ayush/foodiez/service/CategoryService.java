package com.ayush.foodiez.service;

import com.ayush.foodiez.model.Category;


import java.util.List;
import java.util.Optional;

public interface CategoryService {
     Category createUpdate(Category category,int vendorId);
     Optional<Category> findById(int id,int vendorId);

     void deleteById(int id,int vendorId);

     boolean existsById(int id,int vendorId);

     List<Category> findAll(int vendorId);
}
