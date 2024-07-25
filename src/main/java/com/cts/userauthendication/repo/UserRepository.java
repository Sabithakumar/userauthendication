package com.cts.userauthendication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.userauthendication.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByEmail(String email);
}
