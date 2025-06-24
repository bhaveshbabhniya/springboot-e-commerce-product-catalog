package com.springboot.ecommerce_product_catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce_product_catalog.entity.Cart;
import com.springboot.ecommerce_product_catalog.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findByBuyer(User buyer);
	Optional<Cart> findByBuyerAndProductId(User buyer, Long productId);
	void deleteByBuyer(User buyer);
}
