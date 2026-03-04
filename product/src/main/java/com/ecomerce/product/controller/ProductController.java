package com.ecomerce.product.controller;

import com.ecomerce.product.dto.ProductRequest;
import com.ecomerce.product.dto.ProductResponse;
import com.ecomerce.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;


  @GetMapping
  public ResponseEntity<List<ProductResponse>> getProducts() {
    return ResponseEntity.ok().body(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
    ProductResponse productResponse = productService.getProductById(id);
    if (productResponse == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(productResponse);
  }

  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
    return ResponseEntity.ok().body(productService.createProduct(productRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,
      @PathVariable Long id) {
    ProductResponse productResponse = productService.updateProduct(productRequest, id);
    if (productResponse == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(productResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    Boolean isDeleted = productService.deleteProduct(id);
    if (isDeleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/search")
  public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
    return ResponseEntity.ok().body(productService.searchProduct(keyword));
  }


}
