package com.pavan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.pavan.models.AuthRequest;
import com.pavan.models.AuthResponse;
import com.pavan.models.User;
import com.pavan.repo.UserRepo;

@CrossOrigin("*")
@RestController
public class MainController {

	@Autowired
	CredentialService service;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtTokenUtil;

	@Autowired
	UserRepo userRepo;

	@GetMapping("/hello")
	public String sayHello() {
		return "Welcome to Security System";
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User u = userRepo.save(user);
		System.out.println(u);
		return ResponseEntity.ok(new AuthResponse(null, "Registration successfull"));
	}
	
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest)
			throws Exception {
		System.out.println("authenticated");

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.ok(new AuthResponse(null, "Invalid Username or Password"));
		}
		final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());
		System.out.println("got it: "+ userDetails);
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(jwt, "Login Successfull!"));
	}

	@GetMapping("/all-users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}
}
