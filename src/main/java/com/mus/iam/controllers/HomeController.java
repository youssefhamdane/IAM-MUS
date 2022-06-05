package com.mus.iam.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@Api(tags = "Home", description = "This is about Home Test")
@RestController
public class HomeController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("API Online");
	}

}
