package com.springboot.ecommerce_product_catalog.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce_product_catalog.dto.ProductDTO;
import com.springboot.ecommerce_product_catalog.dto.ProductResponseDTO;
import com.springboot.ecommerce_product_catalog.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Products Add & Show APIs")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PreAuthorize("hasRole('SELLER')")
	@PostMapping
	public void addProducts(@RequestBody List<ProductDTO> productDTOs) {
		productService.addProducts(productDTOs);
	}
	
	@GetMapping
	public List<ProductResponseDTO> getALl() {
		return productService.getAll();
	}
	
	@PreAuthorize("hasRole('SELLER')")
	@GetMapping("/my")
	public List<ProductResponseDTO> getMyProducts() {
		return productService.getSellerProducts();
	}
}
