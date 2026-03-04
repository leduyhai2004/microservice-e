package com.ecomerce.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressRequest {
  private String street;
  private String city;
  private String state;
  @JsonProperty("postal_code")
  private String postalCode;
  private String country;
}
