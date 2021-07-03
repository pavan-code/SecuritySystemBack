package com.pavan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pavan.models.*;


public interface MediaRepo extends JpaRepository<MediaLockerCredentials, Integer> {
	
	MediaLockerCredentials findByEmail(String email);
}
