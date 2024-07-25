package com.cts.userauthendication.service;

import java.util.Map;
import java.util.Optional;

import com.cts.userauthendication.dto.User;

public interface UserAuthService {
	public Map<String, String> addNewUser(User user);
	public Optional<User> getUserByEmail(String email);
}
