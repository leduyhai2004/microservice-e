package com.duyhai.ecom_appilaction.repository;

import com.duyhai.ecom_appilaction.models.CartItem;
import com.duyhai.ecom_appilaction.models.Product;
import com.duyhai.ecom_appilaction.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  CartItem findByUserAndProduct(User user, Product product);

  void deleteByUserAndProduct(User user, Product product);

  List<CartItem> findByUser(User user);

  void deleteByUser(User user);
}
