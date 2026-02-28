package com.duyhai.ecom_appilaction.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
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
