package com.lax.user.service.services;

import com.lax.user.service.entity.User;

import java.util.List;

public interface UserService {

    //user operation

    //create
    User saveUser(User user);

    //Get all the users
    List<User> getAllUser();

    //get Single User of given UserID
    User getUserById(String userId);

    void deleteUser(String userId);

    User updateUser(User user);

}
