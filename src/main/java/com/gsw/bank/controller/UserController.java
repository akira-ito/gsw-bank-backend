package com.gsw.bank.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gsw.bank.model.User;
import com.gsw.bank.services.UserService;
import com.gsw.bank.view.UserView;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/users", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<User>> listUsers() {

		List<User> users = userService.listUsers();
		if (CollectionUtils.isEmpty(users)) {
			return ResponseEntity.noContent().build();
		}

		ResponseEntity<List<User>> resp = new ResponseEntity<>(users, OK);
		return resp;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> createUser(@Valid @RequestBody(required = true) UserView user) {
		System.out.println(user);

		User createUser = userService.createUser(user);

		ResponseEntity<User> resp = new ResponseEntity<>(createUser, OK);
		return resp;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {

		boolean deleted = userService.deleteUser(userId);

		if (!deleted)
			return ResponseEntity.noContent().build();

		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable("userId") String userId) {
		User user = userService.updateUser(userId);
		if (ObjectUtils.isEmpty(user)) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().body(user);
	}
}
