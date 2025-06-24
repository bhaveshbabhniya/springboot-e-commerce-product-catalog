package com.springboot.ecommerce_product_catalog.service;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce_product_catalog.dto.AuthRequest;
import com.springboot.ecommerce_product_catalog.dto.RegisterRequest;
import com.springboot.ecommerce_product_catalog.entity.User;
import com.springboot.ecommerce_product_catalog.repository.UserRepository;
import com.springboot.ecommerce_product_catalog.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authManager;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public void register(RegisterRequest request) {
		User user = User.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();
		userRepository.save(user);
	}
	
	public ResponseEntity<?> login(AuthRequest request) {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));	
		
			User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
			String token = jwtUtil.generateToken(user);
			return ResponseEntity.ok(Collections.singletonMap("token", token));
			
		}catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
		}
	}
}
