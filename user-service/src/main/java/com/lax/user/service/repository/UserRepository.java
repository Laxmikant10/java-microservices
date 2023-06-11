package com.lax.user.service.repository;

import com.lax.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    //we can write here if we want to implement any custom method or query
}
