package com.cts.userauthendication.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.userauthendication.dto.User;
import com.cts.userauthendication.repo.UserRepository;

@Service
public class UserAuthServiceImpl implements UserAuthService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Map<String, String> addNewUser(User user) {
		userRepository.save(user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Registration successful. Verification email sent.");
		return map;
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		  return userRepository.findByEmail(email);
	}
}
