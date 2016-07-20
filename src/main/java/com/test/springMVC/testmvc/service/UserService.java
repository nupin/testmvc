package com.test.springMVC.testmvc.service;

import java.util.List;

import com.test.springMVC.testmvc.model.User;

public interface UserService {

	User findById(long id);
	User findByName(String name);
	void saveUser(User user);
	void updateUser(User user);
	void deleteUserById(long id);
	List<User> getAllUsers();
	void deleteAllUsers();
	public boolean isUserExist(User user);

}
