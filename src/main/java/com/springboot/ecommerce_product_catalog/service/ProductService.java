package com.springboot.ecommerce_product_catalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.ecommerce_product_catalog.dto.ProductDTO;
import com.springboot.ecommerce_product_catalog.dto.ProductResponseDTO;
import com.springboot.ecommerce_product_catalog.entity.Category;
import com.springboot.ecommerce_product_catalog.entity.Product;
import com.springboot.ecommerce_product_catalog.entity.User;
import com.springboot.ecommerce_product_catalog.repository.CategoryRepository;
import com.springboot.ecommerce_product_catalog.repository.ProductRepository;
import com.springboot.ecommerce_product_catalog.repository.UserRepository;
import com.springboot.ecommerce_product_catalog.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	
	public void addProducts(List<ProductDTO> productDTOs) {
		String username = SecurityUtil.getCurrentUsername();
		User seller = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Seller not found."));
		
		List<Product> products = productDTOs.stream().map(dto -> {
			Product product = new Product();
			
			Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found."));
			
			product.setName(dto.getName());
			product.setDescription(dto.getDescription());
			product.setPrice(dto.getPrice());
			product.setStock(dto.getStock());
			product.setCategory(category);
			product.setSeller(seller);
			return product;
		}).toList();
		
		productRepository.saveAll(products);
	}
	
	public List<ProductResponseDTO> getAll() {
		return productRepository.findAll().stream()
				.map(product -> new ProductResponseDTO(
						product.getId(),
						product.getName(),
						product.getDescription(),
						product.getPrice(),
						product.getStock(),
						product.getCategory().getName(),
						product.getSeller().getUsername()
				))
				.collect(Collectors.toList());
	}
	
	public List<ProductResponseDTO> getSellerProducts() {
		String username = SecurityUtil.getCurrentUsername();
		User seller = userRepository.findByUsername(username).orElseThrow();
		return productRepository.findBySeller(seller).stream()
				.map(product -> new ProductResponseDTO(
						product.getId(),
						product.getName(),
						product.getDescription(),
						product.getPrice(),
						product.getStock(),
						product.getCategory().getName(),
						product.getSeller().getUsername()
				))
				.collect(Collectors.toList());
	}
}
