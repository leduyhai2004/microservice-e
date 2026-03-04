package com.ecomerce.order.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
  private Long id;
  private BigDecimal price;
  private OrderStatus status;
  private List<OrderItemDTO> orderItems;
  private LocalDate createAt;
}
