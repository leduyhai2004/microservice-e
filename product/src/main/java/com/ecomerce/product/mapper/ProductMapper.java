package com.ecomerce.product.mapper;


import com.ecomerce.product.dto.ProductRequest;
import com.ecomerce.product.dto.ProductResponse;
import com.ecomerce.product.model.Product;

public class ProductMapper {
  public static ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stockQuantity(product.getStockQuantity())
        .category(product.getCategory())
        .imageUrl(product.getImageUrl())
        .build();
  }

  public static Product mapToProduct(ProductRequest productRequest) {
    return Product.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .stockQuantity(productRequest.getStockQuantity())
        .category(productRequest.getCategory())
        .imageUrl(productRequest.getImageUrl())
        .build();
  }
}
