package com.cts.userauthendication.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message="id can not be null")
	private int id;
	@NotNull(message="email can not be null")
	private String email;
	private String password;
}
