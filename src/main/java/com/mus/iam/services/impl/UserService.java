package com.mus.iam.services.impl;

import com.mus.iam.entities.User;
import com.mus.iam.repositories.IUserRepository;
import com.mus.iam.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Optional<User> findUserByUuid(UUID uuid) {
		try {
			Optional<User> user = userRepository.findByUuid(uuid);
			
			if (user.isPresent()) {
				return user;
			}
			
			return Optional.empty();
		} catch (NoSuchElementException exception) {
			return Optional.empty();
		}
	}

}
