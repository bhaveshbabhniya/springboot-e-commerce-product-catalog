package com.springboot.ecommerce_product_catalog.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce_product_catalog.entity.Category;
import com.springboot.ecommerce_product_catalog.service.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "Product Category Fetch & Insert APIs")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	@GetMapping
	public List<Category> allCategories() {
		return categoryService.getAll();
	}
	
	@PreAuthorize("hasRole('SELLER')")
	@PostMapping
	public void addCategory(@RequestParam String name) {
		categoryService.add(name);
	}
}
