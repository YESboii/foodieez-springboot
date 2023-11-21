package com.ayush.foodiez.repository;

import com.ayush.foodiez.model.Category;
import com.ayush.foodiez.model.Vendor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByIdAndVendor(int id, Vendor vendor);


    @Modifying
    void deleteByIdAndVendor(@Param("id") int id, @Param("vendor") Vendor vendor);

    boolean existsByIdAndVendor(int id, Vendor vendor);

    List<Category> findAllByVendor(Vendor vendor);
}
