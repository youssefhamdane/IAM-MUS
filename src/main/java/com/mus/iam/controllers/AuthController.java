package com.mus.iam.controllers;

import com.mus.iam.auth.AuthUserAndRolesAndAuthoritiesDTO;
import com.mus.iam.entities.User;
import com.mus.iam.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/auth")
@Api(tags="Authorities", description="This is about Authentication")
@RestController
@Slf4j
public class AuthController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping(value = "/authorities/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthUserAndRolesAndAuthoritiesDTO> getAuthorities(@PathVariable String uuid){
		try {
			UUID uuidUser = UUID.fromString(uuid);
			
			User user = userService.findUserByUuid(uuidUser)
					.orElseThrow(() -> new UsernameNotFoundException("Error -> User not found for UUID: " + uuidUser));
			
			return ResponseEntity.ok(new AuthUserAndRolesAndAuthoritiesDTO(user));
		} catch (IllegalArgumentException ie) {
			log.error("Error method getAuthorities in class AuthController: ", ie);
			return ResponseEntity.badRequest().build();//400
		} 
		catch (Exception ex) {
			log.error("Error method getAuthorities in class AuthController: ", ex);
			return ResponseEntity.badRequest().build();//400
		}
	}
	
	@ApiOperation(value = "", hidden = true)
	@PreAuthorize("hasPermission(returnObject, {'permissions', 'user_update', 'TESTE', 'abcd', 'user_read'})")
	@DeleteMapping("/test/permission")
	public ResponseEntity<String> testAuthorities(){
		return ResponseEntity.ok("Permission OK");
	}
	
	@ApiOperation(value = "", hidden = true)
	@GetMapping("/test/login")
	@ResponseBody
	public String testOAuth() {
		return "Login OK";
	}
	
}
