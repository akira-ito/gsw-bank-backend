package com.gsw.bank.services;

import java.util.List;

import com.gsw.bank.model.User;
import com.gsw.bank.view.UserView;

public interface UserService {
	public User createUser(UserView user);

	public List<User> listUsers();

	public boolean deleteUser(String userId);

	public User updateUser(String userId);
}
