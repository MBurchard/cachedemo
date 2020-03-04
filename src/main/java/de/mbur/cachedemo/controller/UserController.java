package de.mbur.cachedemo.controller;

import de.mbur.cachedemo.domain.User;
import de.mbur.cachedemo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class UserController {

	final UserService userService;

	UserController(final UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	ResponseEntity<Object> createUser(@RequestBody final User user) {
		return ResponseEntity.ok(userService.create(user));
	}

	@GetMapping("/users")
	ResponseEntity<Object> getUsers(@RequestParam("cid") final String cid) {
		userService.asyncMethod();
		return ResponseEntity.ok(userService.getUsersByCustomer(cid));
	}

}
