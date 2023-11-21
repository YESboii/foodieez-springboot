package com.ayush.foodiez.service;

import com.ayush.foodiez.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
     Vendor createUpdate(Vendor vendor);
     Optional<Vendor> findById(int id);

     void deleteById(int id);

     boolean existsById(int id);

     List<Vendor> findAll();
}
