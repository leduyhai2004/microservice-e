package com.duyhai.ecom_appilaction.dto;

import com.duyhai.ecom_appilaction.dto.request.UserRequest;
import com.duyhai.ecom_appilaction.dto.response.AddressResponse;
import com.duyhai.ecom_appilaction.dto.response.UserResponse;
import com.duyhai.ecom_appilaction.models.User;

public class UserMapper {
  public static UserResponse toUserResponse(User user) {
    AddressResponse address = null;
    if (user.getAddress() != null) {
      address = new AddressResponse(
          user.getAddress().getStreet(),
          user.getAddress().getCity(),
          user.getAddress().getState(),
          user.getAddress().getPostalCode(),
          user.getAddress().getCountry()
      );
    }

    return new UserResponse(
        user.getUsername(),
        user.getEmail(),
        user.getPhoneNumber(),
        address
    );
  }

  public static User toUserEntity(UserRequest userRequest) {
      User user = new User();
      user.setUsername(userRequest.getUsername());
      user.setEmail(userRequest.getEmail());
      user.setPassword(userRequest.getPassword());
      user.setPhoneNumber(userRequest.getPhone());
      if (userRequest.getAddressRequest() != null) {
        user.setAddress(AddressMapper.toAddressEntity(userRequest.getAddressRequest()));
      }
      return user;
  }
}
