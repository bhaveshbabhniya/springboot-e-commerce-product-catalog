package com.springboot.ecommerce_product_catalog.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
	private Long id;
	private LocalDateTime createdAt;
	private String status;
	private String buyerUsername;
	private List<OrderItemDTO> items;
}
