package com.duyhai.ecom_appilaction.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "order_item_table")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity{
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  private Integer quantity;

  private BigDecimal price;

}