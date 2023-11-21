package com.ayush.foodiez.TestObject;

import com.ayush.foodiez.model.Role;
import com.ayush.foodiez.model.Vendor;

public class DummyObject {
    public static Role getVendorRole(){
        return new Role(2,"VENDOR");
    }
    public static Vendor vendorA() {
        Vendor vendor = new Vendor();
        vendor.setId(1); // Replace with the actual ID for vendorA
        vendor.setFirstName("John");
        vendor.setLastName("Doe");
        vendor.setAge(30); // Replace with the actual age for vendorA
        vendor.setSpeciality("Electronics"); // Replace with the actual speciality for vendorA
        vendor.setRole(getVendorRole()); // Assuming you have a method to get the vendor role
        return vendor;
    }

    public static Vendor vendorB() {
        Vendor vendor = new Vendor();
        vendor.setId(22); // Replace with the actual ID for vendorB
        vendor.setFirstName("Jane");
        vendor.setLastName("Smith");
        vendor.setAge(35); // Replace with the actual age for vendorB
        vendor.setSpeciality("Clothing"); // Replace with the actual speciality for vendorB
        vendor.setRole(getVendorRole()); // Assuming you have a method to get the vendor role
        return vendor;
    }

    public static Vendor vendorC() {
        Vendor vendor = new Vendor();
        vendor.setId(3); // Replace with the actual ID for vendorC
        vendor.setFirstName("Bob");
        vendor.setLastName("Johnson");
        vendor.setAge(40); // Replace with the actual age for vendorC
        vendor.setSpeciality("Home Decor"); // Replace with the actual speciality for vendorC
        vendor.setRole(getVendorRole()); // Assuming you have a method to get the vendor role
        return vendor;
    }
}
