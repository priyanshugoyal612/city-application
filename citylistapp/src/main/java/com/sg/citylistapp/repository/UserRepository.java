package com.sg.citylistapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.citylistapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserById(Long id);
	User findByUsername(String username);
}
