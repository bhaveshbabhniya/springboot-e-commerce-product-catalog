package com.springboot.ecommerce_product_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce_product_catalog.entity.Product;
import com.springboot.ecommerce_product_catalog.entity.User;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findBySeller(User seller);
	List<Product> findBySellerId(Long sellerId);
}
