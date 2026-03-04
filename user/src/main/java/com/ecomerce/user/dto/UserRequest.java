package com.ecomerce.user.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRequest {
  private String username;
  private String email;
  private String password;
  private String phone;
  @JsonProperty("address_request")
  private AddressRequest addressRequest;
}
