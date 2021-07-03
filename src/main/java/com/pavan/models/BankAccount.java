package com.pavan.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String holderName;
    String bankName;
    String accountNumber;
    String ifscCode;
    String email;
    String mobile;
    String branch;
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankAccount() {
        super();
    }

    public BankAccount(int id, String holderName, String bankName, String accountNumber, String ifscCode, String email,
            String mobile, String branch, String address) {
        this.id = id;
        this.holderName = holderName;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.email = email;
        this.mobile = mobile;
        this.branch = branch;
        this.address = address;
    }

    @Override
    public String toString() {
        return "BankAccount [accountNumber=" + accountNumber + ", address=" + address + ", bankName=" + bankName
                + ", branch=" + branch + ", email=" + email + ", holderName=" + holderName + ", id=" + id
                + ", ifscCode=" + ifscCode + ", mobile=" + mobile + "]";
    }
    
    
}