package com.ecomerce.product.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Reconmended:
 * @Entity
 * @Table(name = "product_table")
 * @Query("""
 *     SELECT p
 *     FROM Product p
 *     WHERE p.active = true
 *       AND p.stockQuantity > 0
 *       AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
 * """)
 * List<Product> searchProduct(String keyword);
 */

@Entity(name = "product_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {
  private String name;
  private String description;
  private BigDecimal price;
  private Integer stockQuantity;
  private String category;
  private String imageUrl;
  private Boolean active = true;
}
