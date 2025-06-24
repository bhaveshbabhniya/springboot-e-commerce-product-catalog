package com.springboot.ecommerce_product_catalog.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.ecommerce_product_catalog.dto.OrderItemDTO;
import com.springboot.ecommerce_product_catalog.dto.OrderRequestDTO;
import com.springboot.ecommerce_product_catalog.dto.OrderResponseDTO;
import com.springboot.ecommerce_product_catalog.entity.Order;
import com.springboot.ecommerce_product_catalog.entity.OrderItem;
import com.springboot.ecommerce_product_catalog.entity.OrderStatus;
import com.springboot.ecommerce_product_catalog.entity.Product;
import com.springboot.ecommerce_product_catalog.entity.User;
import com.springboot.ecommerce_product_catalog.repository.CartRepository;
import com.springboot.ecommerce_product_catalog.repository.OrderRepository;
import com.springboot.ecommerce_product_catalog.repository.ProductRepository;
import com.springboot.ecommerce_product_catalog.repository.UserRepository;
import com.springboot.ecommerce_product_catalog.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final CartRepository cartRepository;
	
	public void placeOrder(OrderRequestDTO dto) {
		String username = SecurityUtil.getCurrentUsername();
		User buyer = userRepository.findByUsername(username).orElseThrow();
		
		List<OrderItem> orderItems = new ArrayList<>();
		for(OrderRequestDTO.Item item : dto.getItems()) {
			Product product = productRepository.findById(item.getProductId()).orElseThrow();
			
			OrderItem orderItem = OrderItem.builder()
					.product(product)
					.quantity(item.getQuantity())
					.price(product.getPrice())
					.build();
			orderItems.add(orderItem);
		}
		Order order = Order.builder()
				.buyer(buyer)
				.status(OrderStatus.PLACED)
				.createdAt(LocalDateTime.now())
				.items(orderItems)
				.build();
		
		orderItems.forEach(oi -> oi.setOrder(order));
		orderRepository.save(order);
		cartRepository.deleteByBuyer(buyer);
	}
	
	public List<OrderResponseDTO> getOrdersforBuyer() {
		String username = SecurityUtil.getCurrentUsername();
		User buyer = userRepository.findByUsername(username).orElseThrow();
		List<Order> orders = orderRepository.findByBuyer(buyer);
		
		return orders.stream().map(order -> {
			List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> 
					new OrderItemDTO(
							item.getId(),
							item.getProduct().getName(),
							item.getProduct().getPrice(),
							item.getQuantity()
					)
			).toList();
			return new OrderResponseDTO(
					order.getId(),
					order.getCreatedAt(),
					order.getStatus().name(),
					order.getBuyer().getUsername(),
					itemDTOs
			);
		}).toList();
	}
}
