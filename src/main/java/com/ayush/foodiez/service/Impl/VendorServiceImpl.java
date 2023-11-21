package com.ayush.foodiez.service.Impl;

import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.repository.RoleRepository;
import com.ayush.foodiez.repository.VendorRepository;
import com.ayush.foodiez.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, RoleRepository roleRepository) {
        this.vendorRepository = vendorRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public Vendor createUpdate(Vendor vendor) {
        vendor.setRole(roleRepository.findById(2).get());
        return vendorRepository.save(vendor);
    }

    @Override
    public Optional<Vendor> findById(int id) {
        return vendorRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return vendorRepository.existsById(id);
    }

    //Use pagination
    @Override
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }
}


