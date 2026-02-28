package com.duyhai.ecom_appilaction.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
  private String name;
  private String description;
  private BigDecimal price;
  private Integer stockQuantity;
  private String category;
  private String imageUrl;
}
