package com.pavan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
}
