package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public void deactivateVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        if (vendor != null) {
            vendor.setActive(false);
            vendorRepository.save(vendor);
        }
    }
}
