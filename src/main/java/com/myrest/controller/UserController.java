package com.myrest.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myrest.UserDaoService;
import com.myrest.exception.UserNotFoundException;
import com.myrest.model.User;

@RestController
public class UserController {

	@Autowired
	UserDaoService daoService;

	@GetMapping("/users/{id}")
	public Resource<User> findUser(@PathVariable("id") int id) {

		User user = daoService.getUser(id);
		if (user == null) {

			throw new UserNotFoundException("Not Found user with id " + id);
		}

		Resource<User> resource = new Resource<>(user);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable("id") int id) {

		User user = daoService.deleteUser(id);

		if (user == null) {
			throw new UserNotFoundException("id " + id);
		}

		return user;
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {

		return daoService.getAllUser();
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

		User users = daoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(users.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
