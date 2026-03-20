package com.ecomerce.user.model;


import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

@Data
public class BaseEntity {
  @Id
  private String id;

  @CreatedDate
  private LocalDate createDate;

  @LastModifiedDate
  private LocalDate updateDate;
}
