package com.duyhai.ecom_appilaction.repository;

import com.duyhai.ecom_appilaction.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
