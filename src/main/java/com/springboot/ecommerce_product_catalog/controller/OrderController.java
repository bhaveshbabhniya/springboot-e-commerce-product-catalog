package com.springboot.ecommerce_product_catalog.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce_product_catalog.dto.OrderRequestDTO;
import com.springboot.ecommerce_product_catalog.dto.OrderResponseDTO;
import com.springboot.ecommerce_product_catalog.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Product Orders APIs")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PreAuthorize("hasRole('BUYER')")
	@PostMapping
	public void placeOrder(@RequestBody OrderRequestDTO request) {
		orderService.placeOrder(request);
	}
	
	@PreAuthorize("hasRole('BUYER')")
	@GetMapping
	public List<OrderResponseDTO> myOrders() {
		return orderService.getOrdersforBuyer();
	}
}
