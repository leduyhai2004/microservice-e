package com.duyhai.ecom_appilaction.service;

import com.duyhai.ecom_appilaction.dto.OrderItemDTO;
import com.duyhai.ecom_appilaction.dto.response.OrderResponse;
import com.duyhai.ecom_appilaction.enums.OrderStatus;
import com.duyhai.ecom_appilaction.models.CartItem;
import com.duyhai.ecom_appilaction.models.Order;
import com.duyhai.ecom_appilaction.models.OrderItem;
import com.duyhai.ecom_appilaction.models.User;
import com.duyhai.ecom_appilaction.repository.OrderRepository;
import com.duyhai.ecom_appilaction.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final CartService cartService;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  /**
   * validate for cart item validate for user calculate price create order clear cart
   *
   * @param userId
   * @return
   */
  public Optional<OrderResponse> createOrder(String userId) {
    List<CartItem> cartItems = cartService.getCartItems(userId);
    if (cartItems.isEmpty()) {
      return Optional.empty();
    }
    Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
    if (userOptional.isEmpty()) {
      return Optional.empty();
    }
    User user = userOptional.get();

    BigDecimal totalPrice = cartItems.stream()
        .map(CartItem::getPrice).
        reduce(BigDecimal.ZERO, BigDecimal::add);

    Order order = new Order();
    order.setUser(user);
    order.setStatus(OrderStatus.CONFIRMED);
    order.setTotalAmount(totalPrice);
    List<OrderItem> orderItems = cartItems.stream()
        .map(cartItem ->
            new OrderItem(
                cartItem.getProduct(),
                order,
                cartItem.getQuantity(),
                cartItem.getPrice()
        ))
    .toList();

    order.setOrderItems(orderItems);
    Order savedOrder = orderRepository.save(order);
    cartService.clearCart(userId);
    return Optional.of(mapToOrderResponse(savedOrder));
  }

  private OrderResponse mapToOrderResponse(Order order) {
    return new OrderResponse(
        order.getId(),
        order.getTotalAmount(),
        order.getStatus(),
        order.getOrderItems().stream()
            .map(orderItem -> new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
            )).toList(),
        order.getCreateDate()
    );
  }
}
