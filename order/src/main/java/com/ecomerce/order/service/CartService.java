package com.ecomerce.order.service;

import com.ecomerce.order.clients.ProductServiceClient;
import com.ecomerce.order.clients.UserServiceClient;
import com.ecomerce.order.dto.CartItemRequest;
import com.ecomerce.order.dto.ProductResponse;
import com.ecomerce.order.dto.UserResponse;
import com.ecomerce.order.model.CartItem;
import com.ecomerce.order.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
  private final CartItemRepository cartItemRepository;
  private final ProductServiceClient productServiceClient;
  private final UserServiceClient userServiceClient;

  public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
    ProductResponse productResponse = productServiceClient.getProductById(cartItemRequest.getProductId());
    if (productResponse == null) {
      return false;
    }
    if (productResponse.getStockQuantity() < cartItemRequest.getQuantity()) {
      return false;
    }
    UserResponse userResponse = userServiceClient.getUserById(userId);
    if (userResponse == null) {
      return false;
    }
    CartItem exsitingCartItem = cartItemRepository.findByUserIdAndProductId(Long.parseLong(userId), Long.parseLong(cartItemRequest.getProductId()));
    if (exsitingCartItem != null) {
      exsitingCartItem.setQuantity(exsitingCartItem.getQuantity() + cartItemRequest.getQuantity());
      exsitingCartItem.setPrice(BigDecimal.ZERO);
      cartItemRepository.save(exsitingCartItem);
    } else {
      CartItem cartItem = new CartItem();
      cartItem.setUserId(Long.parseLong(userId));
      cartItem.setProductId(Long.parseLong(cartItemRequest.getProductId()));
      cartItem.setQuantity(cartItemRequest.getQuantity());
      cartItem.setPrice(BigDecimal.valueOf(10000.00));
      cartItemRepository.save(cartItem);
    }
    return true;
  }


  public boolean deleteItemFromCart(String userId, Long productId) {
    if (userId != null || productId != null) {
      cartItemRepository.deleteByUserIdAndProductId(userId, String.valueOf(productId));
      return true;
    }
    return false;
  }

  public List<CartItem> getCartItems(String userId) {
    return cartItemRepository.findByUserId(userId);
  }

  public void clearCart(String userId) {
    cartItemRepository.deleteByUserId(userId);
  }
}
