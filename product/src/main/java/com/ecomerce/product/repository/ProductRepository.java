package com.ecomerce.product.repository;

import com.ecomerce.product.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // Or can use findByActiveTrue
  @Query("SELECT p FROM product_table p WHERE p.active = true")
  List<Product> findAllProductActive();

  @Query("SELECT p from product_table p where p.active = true and p.stockQuantity > 0 and LOWER(p.name) like lower(concat('%', :keyword, '%') )")
  List<Product> searchProduct(@Param("keyword") String keyword);
}
