package com.ayush.foodiez.repository;

import com.ayush.foodiez.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {
////    @Query("delete from Vendor v where v.id=?1")
//    @Modifying
//    void deleteById(int id);
}
