package com.ayush.foodiez;

import com.ayush.foodiez.model.Category;
import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.service.CategoryService;
import com.ayush.foodiez.service.VendorService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CategoriesTest {
    private final CategoryService categoryService;
    private final VendorService vendorService;
    @Autowired
    public CategoriesTest(CategoryService categoryService, VendorService vendorService) {
        this.categoryService = categoryService;
        this.vendorService = vendorService;
    }
    @Test
    public void testThatCreate(){
        Category category = new Category();
        category.setName("Cake");
        List<Vendor> vendors = vendorService.findAll();


        categoryService.createUpdate(category,vendors.get(1).getId());
    }
    @Test
    public void testThatUpdate(){
        Category category = categoryService.findById(52,5).get();
        category.setName("ice-cream");
        categoryService.createUpdate(category,5);
    }
    @Test
    public void testThatFindAll(){
        Vendor vendor = vendorService.findById(5).get();
        Category category = new Category();
        category.setName("milk");
        categoryService.createUpdate(category,vendor.getId());
        System.out.println(categoryService.findAll(5));
    }
    @Test
    public void testThatDel(){

        Category category = new Category();
        category.setName("milk");
        categoryService.deleteById(52,5);

    }

}
