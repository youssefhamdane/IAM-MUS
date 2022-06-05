package com.mus.iam.config.auth;

import com.mus.iam.entities.User;
import com.mus.iam.repositories.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService implements UserDetailsService{

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) {
		
		try {
			User user = userRepository
					.findByLogin(login)
					.orElseThrow(() -> new UsernameNotFoundException("User with login " + login + " not founded"));
			return new UserPrincipal(user);
			
		} catch (UsernameNotFoundException e) {
			log.error("Error Username not found method loadUserByUsername in class AuthenticationService: ", e);
		} catch (Exception e) {
			log.error("Error method loadUserByUsername in class AuthenticationService: ", e);
		}
		
		return null;
	}

}
