package com.sg.citylistapp.helper;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.sg.citylistapp.entity.Role;
import com.sg.citylistapp.entity.User;
import com.sg.citylistapp.repository.UserRepository;

@Component
public class PreSeededUser {

	@Autowired
	UserRepository userRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(PreSeededUser.class);

	@PostConstruct
	public void seedUser() {
		logger.info("Inserting admin data in database");
		User admin = adminCreation("admin", "admin");
		userRepo.save(admin);
		logger.info("Inserting User data in database");
		User normal = userCreation("normal", "normal");
		userRepo.save(normal);

	}

	private User adminCreation(String username, String password) {
		// Role role = new Role();
		// role.setName("ADMIN");
		// User admin = new User(username, passwordEncoder.encode(password), fullname,
		// Set.of(role));
		User admin = User.builder().username(username).password(passwordEncoder.encode(password)).role("ADMIN").build();
		return admin;
	}

	private User userCreation(String username, String password) {
		Role role = new Role();
		role.setName("USER");
		User user = User.builder().username(username).password(passwordEncoder.encode(password)).role("NORMAL").build();
		// User user = new User(username, passwordEncoder.encode(password), fullname,
		// Set.of(role));
		return user;
	}
}
