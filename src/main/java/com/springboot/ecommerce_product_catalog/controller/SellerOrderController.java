package com.springboot.ecommerce_product_catalog.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce_product_catalog.dto.SellerOrderDTO;
import com.springboot.ecommerce_product_catalog.service.SellerOrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seller/orders")
@Tag(name = "Seller Orders", description = "Seller Orders APIs")
@RequiredArgsConstructor
public class SellerOrderController {

	private final SellerOrderService sellerOrderService;
	
	@PreAuthorize("hasRole('SELLER')")
	@GetMapping
	public List<SellerOrderDTO> getOrdersForSeller() {
		return sellerOrderService.getOrdersForSeller();
	}
}
