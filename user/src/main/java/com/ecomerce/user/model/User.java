package com.ecomerce.user.model;

import com.ecomerce.user.dto.UserRole;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * orphanRemoval = true
 * Nếu Address không còn được User tham chiếu → nó sẽ bị XÓA khỏi DB
 * user.setAddress(null);
 * userRepository.save(user);
 * Address cũ bị DELETE khỏi database
 */

@Data
@Document(collection = "users")
public class User extends BaseEntity{
  private String username;

  @Indexed(unique = true)
  private String email;
  private String password;
  private String phoneNumber;
  private UserRole role = UserRole.CUSTOMER;

  private Address address;

}
