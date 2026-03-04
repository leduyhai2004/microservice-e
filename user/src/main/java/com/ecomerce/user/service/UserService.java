package com.ecomerce.user.service;

import com.ecomerce.user.mapper.AddressMapper;
import com.ecomerce.user.mapper.UserMapper;
import com.ecomerce.user.dto.UserRequest;
import com.ecomerce.user.dto.UserResponse;
import com.ecomerce.user.model.User;
import com.ecomerce.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<UserResponse> getAllUsers() {
    List<User> userList =  userRepository.findAll();
    return userList.stream().map(UserMapper::toUserResponse).toList();
  }

  public void addUser(UserRequest userRequest) {
    User user = UserMapper.toUserEntity(userRequest);
    userRepository.save(user);
  }

  public UserResponse getUserById(Long id) {
    User user = userRepository.findById(id).orElse(null);
    return UserMapper.toUserResponse(user);
  }

  public UserResponse updateUser(Long id, UserRequest user) {
    User existingUser = userRepository.findById(id).orElse(null);
    if (existingUser != null) {
      existingUser.setUsername(user.getUsername());
      existingUser.setEmail(user.getEmail());
      existingUser.setPhoneNumber(user.getPhone());
      existingUser.setPassword(user.getPassword());
      existingUser.setAddress(AddressMapper.toAddressEntity(user.getAddressRequest()));
      return UserMapper.toUserResponse(userRepository.save(existingUser));
    } else {
      return null;
    }
  }
}
