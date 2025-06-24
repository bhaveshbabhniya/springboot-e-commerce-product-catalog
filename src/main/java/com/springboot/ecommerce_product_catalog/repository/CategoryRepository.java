package com.springboot.ecommerce_product_catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce_product_catalog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
