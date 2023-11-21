package com.ayush.foodiez.controller;

import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/foodieez")
public class AdminController {

    private final VendorService vendorService;

    public AdminController(VendorService vendorService){
        this.vendorService = vendorService;
    }

    @RequestMapping(path = "/admin")
    public String adminDashBoard(){
        return "adminDashBoard";
    }
    @RequestMapping(path = "/admin/create-vendor")
    public String createVendor(){
        return "createVendor";
    }
    @RequestMapping(path = "/admin/vendors",method = RequestMethod.GET)
    public String getVendorPage(Model model){
        List<Vendor> vendors = vendorService.findAll();
        String hello = "hello";
        model.addAttribute("vendors",vendors);
        return "adminVendors";
    }

    @RequestMapping(path = "/admin/vendors", method = RequestMethod.POST)
    public String vendorAdd(@ModelAttribute Vendor vendor, Model model){
        vendorService.createUpdate(vendor);
        return "redirect:/foodieez/admin/vendors";
    }
    @RequestMapping(path = "/admin/vendors/{vendorId}",method = RequestMethod.DELETE)
    public String vendorDelete(@PathVariable("vendorId")int vendorId){


            vendorService.deleteById(vendorId);
            return "redirect:/foodieez/admin/vendors";

        //throw new RuntimeException("Create custom exception for this");
    }
    @RequestMapping(path = "/admin/vendors/{vendorId}",method = RequestMethod.PUT)
    public String vendorUpdate(@PathVariable("vendorId")int vendorId,Model model){
        Vendor vendorToBeUpdated = vendorService.findById(vendorId).orElseThrow(
                ()->new RuntimeException("create exception")
        );
        model.addAttribute("vendorToBeUpdated",vendorToBeUpdated);
        return "updateVendors";

        //throw new RuntimeException("Create custom exception for this");
    }
}
