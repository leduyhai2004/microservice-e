package com.duyhai.ecom_appilaction.models;

import com.duyhai.ecom_appilaction.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "order_table")
@Data
@NoArgsConstructor
public class Order extends BaseEntity{
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private OrderStatus status = OrderStatus.PENDING;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems = new ArrayList<>();
}
