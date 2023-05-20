package com.sg.citylistapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sg.citylistapp.security.jwt.JwtAuthRequest;
import com.sg.citylistapp.security.jwt.JwtAuthResponse;
import com.sg.citylistapp.security.jwt.JwtTokenHelper;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	@Autowired
	JwtTokenHelper jwtTokenHelper;

	@Autowired
	UserDetailsService userDetailService;

	@Autowired
	AuthenticationManager authenticateManager;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToke(@RequestBody JwtAuthRequest jwtRequest) throws Exception {
		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setToken(token);
		return ResponseEntity.ok(authResponse);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		
		try {

		authenticateManager.authenticate(authenticationToken);
		
		}
		catch(BadCredentialsException e)
		{
			System.out.println("Invalid Details");
			throw new Exception("Invalid Username");
		}
		
		
		
	}

}
