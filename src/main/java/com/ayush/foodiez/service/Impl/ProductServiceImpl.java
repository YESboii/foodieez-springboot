package com.ayush.foodiez.service.Impl;

import com.ayush.foodiez.Constants.Constants;
import com.ayush.foodiez.model.Category;
import com.ayush.foodiez.model.Product;
import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.repository.ProductRepository;
import com.ayush.foodiez.repository.VendorRepository;
import com.ayush.foodiez.service.CategoryService;
import com.ayush.foodiez.service.ProductService;
import com.ayush.foodiez.service.fileservice.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final CategoryService categoryService;

    private final FileService fileService;


    public ProductServiceImpl(ProductRepository productRepository,VendorRepository vendorRepository
    ,CategoryService categoryService, FileService fileService) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.categoryService = categoryService;
        this.fileService = fileService;
    }

    @Override
    @Transactional
    public Product createUpdate(Product product, int categoryId, int vendorId, MultipartFile file,int type) {
         final String filePath = "foodiez/src/main/resources/static/productImages";
        Category category = categoryService.findById(categoryId,vendorId).orElseThrow(
                ()->new RuntimeException("Category doesnt exists")//highly unlikely..
        );
        String path = "";
        System.out.println(product.getImagePath() + "  service");
        if (type == Constants.FILE_SAVE) {
            try {
                path = fileService.addImage(filePath, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(type == Constants.FILE_UPDATE){
            try {
                System.out.println(product.getImagePath());
                path = fileService.updateImage(filePath, file,product.getImagePath());
                System.out.println("this is path " + path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        product.setImagePath(path);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(int id,int categoryId,int vendorId) {
        Optional<Category> category = categoryService.findById(categoryId,vendorId);
        if (category.isEmpty()) return Optional.empty();
        return productRepository.findByIdAndCategory(id,category.get());
    }
    @Override
    @Transactional
    public void deleteById(int id,int categoryId,int vendorId) {
        final String filePath = "foodiez/src/main/resources/static/productImages";
        Optional<Category> category = categoryService.findById(categoryId,vendorId);

        if (category.isEmpty()) throw new RuntimeException();
        Product product = productRepository.findByIdAndCategory(id,category.get()).orElse(null);
        if(product==null) return;

        fileService.deleteImage(filePath,product.getImagePath());
        productRepository.deleteByIdAndCategory(id,category.get());
    }

    @Override
    public boolean existsById(int id, int vendorId,int categoryId) {
        Optional<Category> category = categoryService.findById(categoryId,vendorId);

        if(category.isEmpty()) return false;//highly unlikely//maybe problem with url

        return productRepository.existsByIdAndCategory(id,category.get());
    }

    @Override
    public Page<Product> findAll(int vendorId, Pageable pageable) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if (vendor.isEmpty()) throw new RuntimeException("vendor not found");

        return productRepository.findAll(vendorId,pageable);
    }
}
