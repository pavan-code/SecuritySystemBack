package com.pavan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.pavan.models.*;
import com.pavan.repo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

	@Autowired
	BankRepo bankRepo;

	@Autowired
	MediaRepo mediaRepo;

	@Autowired
	BankAccountRepo bankAccRepo;

	@GetMapping("/hello")
	public String sayHello() {
		return "Welcome to Security System";
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User u = userRepo.save(user);
		System.out.println(u);
		return ResponseEntity.ok(new AuthResponse(null, "Registration successfull", null));
	}
	
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest)
			throws Exception {
		System.out.println("authenticated");

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.ok(new AuthResponse(null, "Invalid Username or Password", null));
		}
		final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());
		System.out.println("got it: "+ userDetails);
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(jwt, "Login Successfull!", userRepo.findByEmail(authenticationRequest.getEmail())));
	}

	@GetMapping("/all-users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/isBankLocked")
	public Boolean isBankLocked(@RequestParam("email") String email) {
		BankLockerCredentials cred = bankRepo.findByEmail(email);
		System.out.println(cred);
		if(cred != null)
			return true;
		else
			return false;
	}

	@PostMapping("/setBankPassword")
	public ResponseEntity<?> setBankPassword(@RequestBody BankLockerCredentials details) {
		// System.out.println(details);
		details.setPassword(passwordEncoder.encode(details.getPassword()));
		BankLockerCredentials saved = bankRepo.save(details);
		// System.out.println(saved);
		if(saved != null)
			return ResponseEntity.ok(new AuthResponse(null, "Password Set Successfully", null));
		else
			return ResponseEntity.ok(new AuthResponse(null, "Failed to set password", null));
	}

	@PostMapping("/checkBankPassword")
	public Boolean checkBankPassword(@RequestBody BankLockerCredentials details) {
		String password = details.getPassword();
		BankLockerCredentials dbdetails = bankRepo.findByEmail(details.getEmail());
		String dbPassword = dbdetails.getPassword();
		if (passwordEncoder.matches(password, dbPassword)) {
			return true;
		} else {
			return false;
		}		
	}
	@GetMapping("/isMediaLocked")
	public Boolean isMediaLocked(@RequestParam("email") String email) {
		MediaLockerCredentials cred = mediaRepo.findByEmail(email);
		System.out.println(cred);
		if(cred != null)
			return true;
		else
			return false;
	}
	
	@PostMapping("/setMediaPassword")
	public ResponseEntity<?> setMediaPassword(@RequestBody MediaLockerCredentials details) {
		// System.out.println(details);
		details.setPassword(passwordEncoder.encode(details.getPassword()));
		MediaLockerCredentials saved = mediaRepo.save(details);
		System.out.println(saved);
		if(saved != null)
			return ResponseEntity.ok(new AuthResponse(null, "Password Set Successfully", null));
		else
			return ResponseEntity.ok(new AuthResponse(null, "Failed to set password", null));
	}
	@PostMapping("/checkMediaPassword")
	public Boolean checkMediaPassword(@RequestBody MediaLockerCredentials details) {
		String password = details.getPassword();
		MediaLockerCredentials dbdetails = mediaRepo.findByEmail(details.getEmail());
		String dbPassword = dbdetails.getPassword();
		if (passwordEncoder.matches(password, dbPassword)) {
			return true;
		} else {
			return false;
		}		
	}
	@GetMapping(value="/getBankAccounts")
	public List<BankAccount> getbankAccounts(@RequestParam String email) {
		return bankAccRepo.findAll();
	}

	@PostMapping(value="/addBankAccount")
	public ResponseEntity<?> addBankAccount(@RequestBody BankAccount account) {
		BankAccount acc = bankAccRepo.save(account);
		if(acc != null)
			return ResponseEntity.ok(new AuthResponse("success", "Bank Account Added Successfully", null));
		else
			return ResponseEntity.ok(new AuthResponse(null, "Failed to Add Bank account", null));
	}	

	@DeleteMapping("/deleteAccount")
	public ResponseEntity<?> deleteAccount(@RequestParam int id) {
		bankAccRepo.deleteById(id);
		return ResponseEntity.ok(new AuthResponse(null, "Account Deleted Successfully", null));
	}

	@PostMapping("/updateAccount")
	public ResponseEntity<?> updateAccount(@RequestBody BankAccount account) {
		BankAccount acc = bankAccRepo.save(account);
		if(acc != null) 
			return ResponseEntity.ok(new AuthResponse("success", "Account Updated Successfully", null));
		else
			return ResponseEntity.ok(new AuthResponse(null, "Failed to update details", null));
	}
}
