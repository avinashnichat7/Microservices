package com.lcwd.user.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lcwd.user.service.entity.User;

@Service
public interface UserService {

	User saveUser(User user);

	List<User> getAllUser();

	User getUser(String userId);

	User deleteUser(String userId);

	User updateUser(String user);
}
