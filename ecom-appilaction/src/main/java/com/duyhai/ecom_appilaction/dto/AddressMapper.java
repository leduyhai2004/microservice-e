package com.duyhai.ecom_appilaction.dto;

import com.duyhai.ecom_appilaction.dto.request.AddressRequest;
import com.duyhai.ecom_appilaction.models.Address;

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
