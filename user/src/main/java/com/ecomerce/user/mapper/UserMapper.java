package com.ecomerce.user.mapper;


import com.ecomerce.user.dto.AddressResponse;
import com.ecomerce.user.dto.UserRequest;
import com.ecomerce.user.dto.UserResponse;
import com.ecomerce.user.model.User;

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
