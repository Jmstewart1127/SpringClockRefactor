package com.timeclock.web.ClockBeta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Business {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    @Column(name = "admin_id")
    private int adminId;
    @Column(name = "biz_name")
	private String bizName;
    @Column(name = "admin_name")
	private String adminName;
    @Column(name = "email")
    private String email;
    @Column(name = "confirmation_token")
    private String confirmationToken;
	@Column(name = "enabled")
	private boolean enabled;
    @Column(name = "password")
	private String password;
	
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getBizName() {
		return bizName;
	}
	
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getConfirmationToken() {
		return this.confirmationToken;
	}
	
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean value) {
		this.enabled = value;
	}
	
    
}
