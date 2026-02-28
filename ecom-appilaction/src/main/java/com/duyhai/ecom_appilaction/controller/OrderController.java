package com.duyhai.ecom_appilaction.controller;

import com.duyhai.ecom_appilaction.dto.response.OrderResponse;
import com.duyhai.ecom_appilaction.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping("/create")
  public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User-ID") String userId) {
    return orderService.createOrder(userId)
        .map(orderResponse -> new ResponseEntity<>(orderResponse, HttpStatus.CREATED))
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }
}
