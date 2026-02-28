package com.duyhai.ecom_appilaction.dto.request;

import lombok.Data;

@Data
public class CartItemRequest {
  private Long productId;
  private Integer quantity;
}
