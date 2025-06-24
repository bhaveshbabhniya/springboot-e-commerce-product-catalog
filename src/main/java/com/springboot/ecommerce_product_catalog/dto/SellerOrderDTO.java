package com.springboot.ecommerce_product_catalog.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerOrderDTO {

	private Long orderId;
	private String buyerName;
	private LocalDateTime createdAt;
	private List<ItemInfo> items = new ArrayList<>();
	
	@Getter
	@Setter
	@AllArgsConstructor
	public static class ItemInfo {
		private String productName;
		private int quantity;
		private double price;
	}
}
