package com.ayush.foodiez.controller;

import com.ayush.foodiez.Constants.Constants;
import com.ayush.foodiez.model.Category;
import com.ayush.foodiez.model.Product;
import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.service.CategoryService;
import com.ayush.foodiez.service.ProductService;
import com.ayush.foodiez.service.VendorService;
import com.ayush.foodiez.service.fileservice.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/foodieez")
public class VendorController {
    private final VendorService vendorService;
    private final CategoryService categoryService;

    private final ProductService productService;

    public VendorController(VendorService vendorService,CategoryService categoryService,
                            ProductService productService) {
       this.vendorService = vendorService;
       this.categoryService = categoryService;
       this.productService = productService;
    }

    @RequestMapping(path = "/{vendorId}/categories")
    public String returnAddCategoriesPage(@PathVariable("vendorId")int vendorId,Model model){
        if(vendorService.existsById(vendorId)) {
            List<Category> categories = categoryService.findAll(vendorId);
            model.addAttribute("categories",categories);
            model.addAttribute("idVendor",vendorId);
            Vendor vendor = vendorService.findById(vendorId).get();
            model.addAttribute("vendor",vendor);
            return "createCategory";
        }
        return "error";
    }
    @RequestMapping(path = "/{vendorId}/categories", method = RequestMethod.POST)
    public String addCategories(@PathVariable("vendorId")int vendorId, @ModelAttribute Category category){
        if(vendorService.existsById(vendorId)){
            categoryService.createUpdate(category,vendorId);
            return String.format("redirect:/foodieez/%d/categories", vendorId);
        }
        return "error";
    }
    @RequestMapping(path = "/{vendorId}/categories/{id}", method = RequestMethod.DELETE)
    public String deleteCategories(@PathVariable("vendorId")int vendorId,@PathVariable("id") int id){
        if(vendorService.existsById(vendorId)){
            categoryService.deleteById(id,vendorId);
            return String.format("redirect:/foodieez/%d/categories", vendorId);
        }
        return "error";
    }
    @RequestMapping(path = "/{vendorId}/categories/{id}", method = RequestMethod.PUT)
    public String updateCategories(@PathVariable("vendorId")int vendorId,@PathVariable("id") int id,Model model){
        if(vendorService.existsById(vendorId)){
            Category categoryToUpdated = categoryService.findById(id,vendorId).orElseThrow(
                    ()->new RuntimeException("Abb toh exception banale bhai")
            );
            model.addAttribute("categoryToBeUpdated",categoryToUpdated);
            model.addAttribute("idVendor",vendorId);
            return "updateCategory";
        }
        return "error";
    }
//------------------------------------------------------------------------------------------------------------------------------------

    @RequestMapping(path="/{vendorId}/create-product")
    public String returnCreateProductPage(@PathVariable("vendorId")int vendorId,Model model){
        if(vendorService.existsById(vendorId)){
            List<Category> categories = categoryService.findAll(vendorId);
            model.addAttribute("categories",categories);
            model.addAttribute("idVendor",vendorId);
            Vendor vendor = vendorService.findById(vendorId).get();
            model.addAttribute("vendor",vendor);
            return "createProduct";
        }
        return "error";
    }
    @RequestMapping(path="/{vendorId}/products",method = RequestMethod.POST)
    public String addProduct(@PathVariable("vendorId")int vendorId, @ModelAttribute Product product,
                             @RequestParam("categoryId")int categoryId, Model model, @RequestParam("pathImage")MultipartFile file){
        if(vendorService.existsById(vendorId)){
            productService.createUpdate(product,categoryId,vendorId,file,Constants.FILE_SAVE);
            return String.format("redirect:/foodieez/%d/products",vendorId);
        }
        return "error";
    }

    @RequestMapping(path = "/{vendorId}/products",method = RequestMethod.GET)
    public String getAllProducts(@PathVariable("vendorId")int vendorId,
            @RequestParam(value = "pageNumber",defaultValue = "0" ) int pageNumber
            ,Model model){
        Pageable pageable = PageRequest.of(pageNumber,Constants.DEFAULT_PAGE_SIZE);
        Page<Product> products = productService.findAll(vendorId,pageable);
        System.out.println(pageNumber);
        List<Product> products1 = products.getContent();
//        for (Product product:
//             products1) {
//            System.out.println(product.getId());
//        }
//        System.out.println(products.getTotalElements());
        model.addAttribute("idVendor",vendorId);
        model.addAttribute("products",products);
        Vendor vendor = vendorService.findById(vendorId).get();
        model.addAttribute("vendor",vendor);
        return "products";
    }
    @RequestMapping(path = "/{vendorId}/{categoryId}/{productId}",method = RequestMethod.GET)
    public String renderUpdateProductPage(@PathVariable("categoryId")int categoryId,
                                          @PathVariable("productId")int productId, Model model, @PathVariable int vendorId){

        Product productToBeUpdated = productService.findById(productId,categoryId,vendorId).orElseThrow(()->new RuntimeException("kdsks"));
        List<Category> categories = categoryService.findAll(vendorId);
        model.addAttribute("productToBeUpdated",productToBeUpdated);
        model.addAttribute("categories",categories);
        model.addAttribute("idVendor",vendorId);
        System.out.println(productToBeUpdated.getImagePath());
        Vendor vendor = vendorService.findById(vendorId).get();
        model.addAttribute("vendor",vendor);
        model.addAttribute("imagepath",productToBeUpdated.getImagePath());
        return "updateProduct";
    }

    @RequestMapping(path="/{vendorId}/{categoryId}/{productId}/update",method = RequestMethod.PUT)
    public String updateProduct(@PathVariable("vendorId")int vendorId, @ModelAttribute Product product,
                                @PathVariable("productId") int productId,
                             @RequestParam("categoryId")int categoryIdNew, Model model, @RequestParam("pathImage")MultipartFile file,
    @RequestParam("oldName")String oldName){

        product.setImagePath(oldName);
        if(vendorService.existsById(vendorId)){
            productService.createUpdate(product,categoryIdNew,vendorId,file,Constants.FILE_UPDATE);
            return String.format("redirect:/foodieez/%d/products",vendorId);
        }
        return "error";
    }
    @RequestMapping(path="/{vendorId}/{categoryId}/{productId}",method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable int vendorId,@PathVariable int categoryId,@PathVariable int productId){
        productService.deleteById(productId,categoryId,vendorId);
        return String.format("redirect:/foodieez/%d/products",vendorId);
    }

}
