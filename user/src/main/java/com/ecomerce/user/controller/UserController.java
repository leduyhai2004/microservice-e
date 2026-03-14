package com.ecomerce.user.controller;

import com.ecomerce.user.dto.UserRequest;
import com.ecomerce.user.dto.UserResponse;
import com.ecomerce.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    // or ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PostMapping
  public ResponseEntity<List<UserResponse>> addUser(@RequestBody UserRequest userRequest) {
    userService.addUser(userRequest);
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable String user_id) {
    UserResponse user = userService.getUserById(user_id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(userService.getUserById(user_id));
  }

  @PutMapping("/{user_id}")
  public ResponseEntity<UserResponse> updateUser(
      @PathVariable String user_id,
      @RequestBody UserRequest userRequest) {
    UserResponse updatedUser = userService.updateUser(user_id, userRequest);
    if (updatedUser == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedUser);
  }
}
