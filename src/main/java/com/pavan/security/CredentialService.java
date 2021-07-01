package com.pavan.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pavan.models.User;
import com.pavan.repo.UserDetail;
import com.pavan.repo.UserRepo;

@Service
public class CredentialService implements UserDetailsService{
	
	@Autowired
	UserRepo repo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
		User model=repo.findByEmail(email);
		if(model==null) {
			System.out.println("model is null");
			throw new UsernameNotFoundException("Not Found...");
		}
		System.out.println("model found " + model);
		return new UserDetail(model);
	}
}
