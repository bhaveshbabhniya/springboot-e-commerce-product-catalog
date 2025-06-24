package com.springboot.ecommerce_product_catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.ecommerce_product_catalog.dto.CartItemDTO;
import com.springboot.ecommerce_product_catalog.entity.Cart;
import com.springboot.ecommerce_product_catalog.entity.Product;
import com.springboot.ecommerce_product_catalog.entity.User;
import com.springboot.ecommerce_product_catalog.repository.CartRepository;
import com.springboot.ecommerce_product_catalog.repository.ProductRepository;
import com.springboot.ecommerce_product_catalog.repository.UserRepository;
import com.springboot.ecommerce_product_catalog.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	
	public void addToCart(Long productId, int quantity) {
		String username = SecurityUtil.getCurrentUsername();
		User buyer = userRepository.findByUsername(username).orElseThrow();
		Product product = productRepository.findById(productId).orElseThrow();
		
		Cart cart = cartRepository.findByBuyerAndProductId(buyer, productId)
				.orElse(Cart.builder()
						.buyer(buyer)
						.product(product)
						.quantity(0)
						.build());
		
		cart.setQuantity(cart.getQuantity() + quantity);
		cartRepository.save(cart);
 	}
	
	public List<CartItemDTO> getCart() {
		String username = SecurityUtil.getCurrentUsername();
		User buyer = userRepository.findByUsername(username).orElseThrow();
		
		List<Cart> cartItems = cartRepository.findByBuyer(buyer);
		List<CartItemDTO> result = new ArrayList<>();
		
		for(Cart cart : cartItems) {
			result.add(new CartItemDTO(
					cart.getProduct().getId(),
					cart.getProduct().getName(),
					cart.getQuantity(),
					cart.getProduct().getPrice()
			));
		}
		return result;
	}
	
	public void clearCart() {
		String username = SecurityUtil.getCurrentUsername();
		User buyer = userRepository.findByUsername(username).orElseThrow();
		cartRepository.deleteByBuyer(buyer);
	}
}
