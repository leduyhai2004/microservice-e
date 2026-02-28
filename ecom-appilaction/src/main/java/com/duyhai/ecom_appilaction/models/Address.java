package com.duyhai.ecom_appilaction.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "address_table")
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity{
  private String street;
  private String city;
  private String state;
  private String postalCode;
  private String country;
}
