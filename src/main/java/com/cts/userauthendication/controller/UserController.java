package com.cts.userauthendication.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.userauthendication.dto.User;
import com.cts.userauthendication.service.UserAuthService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserAuthService userAuthService;

	@PostMapping("/register")
	public ResponseEntity<?> userRegistration(@RequestBody User user) {
		try {
			Optional<User> userData  = userAuthService.getUserByEmail(user.getEmail());

			Map<String, String> map = new HashMap<String, String>();
			if(!userData.isEmpty()) {
				map.put("error", "Email already exists.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}

			if(user.getEmail().isEmpty() || user.getEmail().isBlank() || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
				map.put("error", "Email or Password is empty");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}

			if(user.getPassword().length() < 8) {
				map.put("error", "Password must be at least 8 characters long and contain a mix of letters, numbers, and special characters.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}
			
			Map<String, String> user1 = userAuthService.addNewUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("error", "Registration failed. Please try again later.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody User user) {
		try {
			Optional<User> userData = userAuthService.getUserByEmail(user.getEmail());
			Map<String, String> map = new HashMap<String, String>();
			if(userData.isEmpty()) {
				map.put("error", "Invalid email.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}

			if(!userData.get().getPassword().equals(user.getPassword())) {
				map.put("error", "Invalid password.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}
			
			map.put("message", "Login successful.");
			map.put("token", "");			return ResponseEntity.status(HttpStatus.OK).body(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("error", "Login failed. Please try again later.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
	}
}
