package com.duyhai.ecom_appilaction.models;

import com.duyhai.ecom_appilaction.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * orphanRemoval = true
 * Nếu Address không còn được User tham chiếu → nó sẽ bị XÓA khỏi DB
 * user.setAddress(null);
 * userRepository.save(user);
 * Address cũ bị DELETE khỏi database
 */

@Data
@Entity(name = "user_table")
//@Entity
//@AllArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
  private String username;
  private String email;
  private String password;
  private String phoneNumber;
  private UserRole role = UserRole.CUSTOMER;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

}
