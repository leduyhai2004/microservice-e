package com.duyhai.ecom_appilaction.service;

import com.duyhai.ecom_appilaction.dto.AddressMapper;
import com.duyhai.ecom_appilaction.dto.UserMapper;
import com.duyhai.ecom_appilaction.dto.request.UserRequest;
import com.duyhai.ecom_appilaction.dto.response.UserResponse;
import com.duyhai.ecom_appilaction.models.User;
import com.duyhai.ecom_appilaction.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
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
