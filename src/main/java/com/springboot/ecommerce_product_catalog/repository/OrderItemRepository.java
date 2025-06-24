package com.springboot.ecommerce_product_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce_product_catalog.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByProductSellerId(Long sellerId);
}
