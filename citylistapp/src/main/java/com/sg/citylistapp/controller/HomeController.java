package com.sg.citylistapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("home")
@RestController
public class HomeController {

	@GetMapping("/normal")
	public ResponseEntity<String> welcome() {
		String text = "This Page is not allowed to unauthenticate users";
		return new ResponseEntity<>(text, HttpStatus.OK);
	}

	@GetMapping("/admin")
	public ResponseEntity<String> welcomeAdmin() {
		String text = "ADMIN This Page is not allowed to unauthenticate users";
		return ResponseEntity.ok(text);
	}
}
