package com.springboot.ecommerce_product_catalog.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce_product_catalog.dto.CartItemDTO;
import com.springboot.ecommerce_product_catalog.service.CartService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "Add to Cart & Check Cart APIs")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	
	@PreAuthorize("hasRole('BUYER')")
	@PostMapping("/add")
	public void addToCart(@RequestParam Long productId, @RequestParam int quantity) {
		cartService.addToCart(productId, quantity);
	}
	
	@PreAuthorize("hasRole('BUYER')")
	@GetMapping
	public List<CartItemDTO> viewCart() {
		return cartService.getCart();
	}
	
	@PreAuthorize("hasRole('BUYER')")
	@GetMapping("/clear")
	public void clearCart() {
		cartService.clearCart();
	}
}
