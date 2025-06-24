package com.springboot.ecommerce_product_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.ecommerce_product_catalog.entity.Category;
import com.springboot.ecommerce_product_catalog.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	public void add(String name) {
		categoryRepository.save(Category.builder().name(name).build());
	}
}
