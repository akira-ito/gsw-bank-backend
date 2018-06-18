package com.gsw.bank.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsw.bank.model.User;
import com.gsw.bank.repository.UserRepository;
import com.gsw.bank.services.UserService;
import com.gsw.bank.view.UserView;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(UserView userView) {
		User user = new User();
		user.setName(userView.getName());
		user.setSaldo(userView.getSaldo());

		return userRepository.save(user);
	}
	
	@Override
	public User updateUser(String userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) 
			return null;
		
		return userRepository.save(user.get());
	}

	@Override
	public boolean deleteUser(String userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			return false;
		else
			userRepository.deleteById(userId);

		return true;
	}
}
