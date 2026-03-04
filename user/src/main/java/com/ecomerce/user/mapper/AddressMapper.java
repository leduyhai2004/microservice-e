package com.ecomerce.user.mapper;

import com.ecomerce.user.dto.AddressRequest;
import com.ecomerce.user.model.Address;

public class AddressMapper {

  public static Address toAddressEntity(AddressRequest addressRequest) {
    return new Address(
        addressRequest.getStreet(),
        addressRequest.getCity(),
        addressRequest.getState(),
        addressRequest.getPostalCode(),
        addressRequest.getCountry()
    );
  }
}
