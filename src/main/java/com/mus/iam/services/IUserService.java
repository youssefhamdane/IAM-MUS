package com.mus.iam.services;

import com.mus.iam.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {
	
	public Optional<User> findUserByUuid(UUID uuid);
	
}
