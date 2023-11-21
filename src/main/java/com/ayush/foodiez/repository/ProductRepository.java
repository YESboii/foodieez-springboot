package com.ayush.foodiez.repository;

import com.ayush.foodiez.model.Category;
import com.ayush.foodiez.model.Product;
import com.ayush.foodiez.model.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByIdAndCategory(int id,Category category);
    void deleteByIdAndCategory(int id, Category category);

    boolean existsByIdAndCategory(int id, Category category);

    @Query("SELECT p FROM Product p JOIN p.category c JOIN c.vendor v WHERE v.id = :vendorId")
    Page<Product> findAll(@Param("vendorId") int vendorId, Pageable pageable);
}
