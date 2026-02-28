package com.duyhai.ecom_appilaction.service;

import com.duyhai.ecom_appilaction.dto.request.CartItemRequest;
import com.duyhai.ecom_appilaction.models.CartItem;
import com.duyhai.ecom_appilaction.models.Product;
import com.duyhai.ecom_appilaction.models.User;
import com.duyhai.ecom_appilaction.repository.CartItemRepository;
import com.duyhai.ecom_appilaction.repository.ProductRepository;
import com.duyhai.ecom_appilaction.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
    Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProductId());
    if (productOpt.isEmpty()) {
      return false;
    }
    Product product = productOpt.get();
    if(product.getStockQuantity() < cartItemRequest.getQuantity()) {
      return  false;
    }
    Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
    if (userOpt.isEmpty()) {
      return false;
    }
    User user = userOpt.get();
    CartItem exsitingCartItem = cartItemRepository.findByUserAndProduct(user, product);
    if(exsitingCartItem != null){
      exsitingCartItem.setQuantity(exsitingCartItem.getQuantity() + cartItemRequest.getQuantity());
      exsitingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(exsitingCartItem.getQuantity())));
      cartItemRepository.save(exsitingCartItem);
    } else {
      CartItem cartItem = new CartItem();
      cartItem.setUser(user);
      cartItem.setProduct(product);
      cartItem.setQuantity(cartItemRequest.getQuantity());
      cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
      cartItemRepository.save(cartItem);
    }
    return true;
  }

  public boolean deleteItemFromCart(String userId, Long productId) {
    Optional<Product> productOpt = productRepository.findById(productId);
    Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
    if(productOpt.isPresent() && userOpt.isPresent()) {
      cartItemRepository.deleteByUserAndProduct(userOpt.get(), productOpt.get());
      return true;
    }
    return false;
  }

  public List<CartItem> getCartItems(String userId) {
    return userRepository.findById(Long.parseLong(userId))
        .map(cartItemRepository::findByUser)
        .orElse(List.of());
  }

  public void clearCart(String userId) {
    userRepository.findById(Long.parseLong(userId))
        .ifPresent(cartItemRepository::deleteByUser);
  }
}
