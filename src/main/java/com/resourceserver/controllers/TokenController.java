package com.resourceserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@GetMapping
	public Jwt getToken(@AuthenticationPrincipal Jwt jwt) {
		
		return jwt;
	}

}