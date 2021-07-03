package com.pavan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pavan.models.*;


public interface BankRepo extends JpaRepository<BankLockerCredentials, Integer> {
	
	BankLockerCredentials findByEmail(String email);
}
