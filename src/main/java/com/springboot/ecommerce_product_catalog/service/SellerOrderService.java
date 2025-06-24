package com.springboot.ecommerce_product_catalog.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.ecommerce_product_catalog.dto.SellerOrderDTO;
import com.springboot.ecommerce_product_catalog.entity.OrderItem;
import com.springboot.ecommerce_product_catalog.entity.User;
import com.springboot.ecommerce_product_catalog.repository.OrderItemRepository;
import com.springboot.ecommerce_product_catalog.repository.UserRepository;
import com.springboot.ecommerce_product_catalog.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerOrderService {

	private final OrderItemRepository orderItemRepo;
	private final UserRepository userRepository;
	
	public List<SellerOrderDTO> getOrdersForSeller() {
		String username = SecurityUtil.getCurrentUsername();
		User seller = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Seller not found"));
		
		List<OrderItem> items = orderItemRepo.findByProductSellerId(seller.getId());
		
		Map<Long, SellerOrderDTO> orders = new LinkedHashMap<>();
		for(OrderItem item : items) {
			Long orderId = item.getOrder().getId();
			orders.putIfAbsent(orderId, new SellerOrderDTO(
					orderId,
					item.getOrder().getBuyer().getUsername(),
					item.getOrder().getCreatedAt(),
					new ArrayList<>()
			));
			orders.get(orderId).getItems().add(
					new SellerOrderDTO.ItemInfo(
							item.getProduct().getName(),
							item.getQuantity(),
							item.getPrice()
					)
			);
		}
		return new ArrayList<SellerOrderDTO>(orders.values());
	}
}
