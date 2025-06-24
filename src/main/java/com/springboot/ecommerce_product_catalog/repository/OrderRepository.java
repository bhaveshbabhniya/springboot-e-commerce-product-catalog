package com.springboot.ecommerce_product_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce_product_catalog.entity.Order;
import com.springboot.ecommerce_product_catalog.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByBuyer(User buyer);
}
