package com.duyhai.ecom_appilaction.service;

import com.duyhai.ecom_appilaction.dto.ProductMapper;
import com.duyhai.ecom_appilaction.dto.request.ProductRequest;
import com.duyhai.ecom_appilaction.dto.response.ProductResponse;
import com.duyhai.ecom_appilaction.models.Product;
import com.duyhai.ecom_appilaction.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<ProductResponse> getAllProducts() {
    List<Product> productList = productRepository.findAllProductActive();
    return productList.stream().map(ProductMapper::mapToProductResponse).toList();
  }

  public ProductResponse getProductById(Long id) {
    Product product = productRepository.findById(id).orElse(null);
    if (product == null) {
      return null;
    }
    return ProductMapper.mapToProductResponse(product);
  }

  public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
    Product product = ProductMapper.mapToProduct(productRequest);
    return ProductMapper.mapToProductResponse(productRepository.save(product));
  }


  public ProductResponse updateProduct(@RequestBody ProductRequest productRequest, Long id) {
    return productRepository.findById(id).map(exsitingProduct -> {
      updateProductFromRequest(exsitingProduct, productRequest);
      return ProductMapper.mapToProductResponse(productRepository.save(exsitingProduct));
    }).orElse(null);
  }

  private void updateProductFromRequest(Product exsitingProduct, ProductRequest productRequest) {
    exsitingProduct.setName(productRequest.getName());
    exsitingProduct.setDescription(productRequest.getDescription());
    exsitingProduct.setPrice(productRequest.getPrice());
    exsitingProduct.setStockQuantity(productRequest.getStockQuantity());
    exsitingProduct.setCategory(productRequest.getCategory());
    exsitingProduct.setImageUrl(productRequest.getImageUrl());
  }

  public boolean deleteProduct(Long id) {
    return productRepository.findById(id).map(p -> {
      p.setActive(false);
      productRepository.save(p);
      return true;
    }).orElse(false);
  }

  public List<ProductResponse> searchProduct(String keyword) {
    return productRepository.searchProduct(keyword).stream()
        .map(ProductMapper::mapToProductResponse)
        .collect(Collectors.toList());
  }
}
