package com.ayush.foodiez.service.Impl;

import com.ayush.foodiez.model.Category;
import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.repository.CategoryRepository;
import com.ayush.foodiez.repository.VendorRepository;
import com.ayush.foodiez.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }
    @Override
    public Category createUpdate(Category category,int vendorId) {

        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if(vendor.isEmpty()) throw new RuntimeException("No such vendor");
        category.setVendor(vendor.get());
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(int id, int vendorId) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if(vendor.isEmpty()) return Optional.empty();

        return categoryRepository.findByIdAndVendor(id,vendor.get());
    }

    @Override
    @Transactional
    public void deleteById(int id, int vendorId) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if(vendor.isEmpty()) throw new RuntimeException("Vendor not found");//highly unlikely//maybe problem with url

        categoryRepository.deleteByIdAndVendor(id,vendor.get());
    }

    @Override
    public boolean existsById(int id,int vendorId) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if(vendor.isEmpty()) return false;//highly unlikely//maybe problem with url

        return categoryRepository.existsByIdAndVendor(id,vendor.get());
    }

    @Override
    public List<Category> findAll(int vendorId) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if(vendor.isEmpty()) throw new RuntimeException("Vendor not found");//highly unlikely//maybe problem with url

        return categoryRepository.findAllByVendor(vendor.get());
    }
}
