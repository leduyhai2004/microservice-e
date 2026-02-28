package com.duyhai.ecom_appilaction.controller;

import com.duyhai.ecom_appilaction.dto.request.CartItemRequest;
import com.duyhai.ecom_appilaction.models.CartItem;
import com.duyhai.ecom_appilaction.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
  private final CartService cartService;

  @GetMapping
  public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-User-Id") String userId) {
    List<CartItem> cartItems = cartService.getCartItems(userId);
    return ResponseEntity.ok(cartItems);
  }

  @PostMapping("/add")
  public ResponseEntity<String> addToCart(@RequestHeader("X-User-Id") String userId, @RequestBody CartItemRequest cartItemRequest) {
    if(!cartService.addToCart(userId, cartItemRequest)){
     return ResponseEntity.badRequest().body("Cannot add to cart");
    }
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/item/{productId}")
  public ResponseEntity<Void> removeFromCart(@RequestHeader("X-User-Id") String userId, @PathVariable Long productId) {
    boolean isDeleted = cartService.deleteItemFromCart(userId, productId);
    if(isDeleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
