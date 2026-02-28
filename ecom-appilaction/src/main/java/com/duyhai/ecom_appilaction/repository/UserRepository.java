package com.duyhai.ecom_appilaction.repository;

import com.duyhai.ecom_appilaction.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
