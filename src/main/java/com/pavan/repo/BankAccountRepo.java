package com.pavan.repo;

import com.pavan.models.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Integer> {
    
}