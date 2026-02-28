package com.duyhai.ecom_appilaction.dto.response;

import com.duyhai.ecom_appilaction.dto.OrderItemDTO;
import com.duyhai.ecom_appilaction.enums.OrderStatus;
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
