package com.pavan.models;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class MediaLockerCredentials {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;

    public MediaLockerCredentials() {
        super();
    }
    public MediaLockerCredentials(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return email + "  " + password;
    }
}