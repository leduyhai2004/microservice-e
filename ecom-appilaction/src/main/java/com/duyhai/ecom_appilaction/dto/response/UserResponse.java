package com.duyhai.ecom_appilaction.dto.response;

import com.duyhai.ecom_appilaction.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  private String username;
  private String email;
  private String phoneNumber;
  private AddressResponse address;
}
