package com.mus.iam.repositories;

import com.mus.iam.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByLogin(String login);
	
	public Optional<User> findByUuid(UUID uuid);
	
}
