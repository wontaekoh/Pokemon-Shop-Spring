package com.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Integer> {
    
//    @Query("SELECT p FROM Products p WHERE p.id = ?1")
//    public Optional<Products> findById(Integer id);
}