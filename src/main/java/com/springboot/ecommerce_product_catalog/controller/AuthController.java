package com.springboot.ecommerce_product_catalog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce_product_catalog.dto.AuthRequest;
import com.springboot.ecommerce_product_catalog.dto.RegisterRequest;
import com.springboot.ecommerce_product_catalog.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authorization", description = "User Registration & Login APIs")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/register")
	public void register(@RequestBody RegisterRequest request) {
		authService.register(request);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		return authService.login(request);
	}
}
