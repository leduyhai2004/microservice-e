package com.ecomerce.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.Data;

@Entity(name = "cart_item_table")
@Data
public class CartItem extends BaseEntity{
  private Long userId;
  private Long productId;

  private Integer quantity;
  private BigDecimal price;


}
