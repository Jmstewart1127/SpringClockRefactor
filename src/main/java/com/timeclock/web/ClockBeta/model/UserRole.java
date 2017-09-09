package com.timeclock.web.ClockBeta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRole {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    @Column(name="user_name")
    private String userName;
    @Column(name="role")
    private String role;
    
    public UserRole() {
    	super();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
	
}
