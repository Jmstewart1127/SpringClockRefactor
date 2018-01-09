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
    @Column(name = "business_name")
	private String businessName;
    @Column(name = "admin_name")
	private String adminName;
    @Column(name = "ytd_labor_cost")
	private double ytdLaborCost;
    @Column(name = "ytd_material_cost")
	private double ytdMaterialCost;
	
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

	public String getBusinessName() {
		return businessName;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public double getYtdLaborCost() {
		return ytdLaborCost;
	}

	public void setYtdLaborCost(double ytdLaborCost) {
		this.ytdLaborCost = ytdLaborCost;
	}

	public double getYtdMaterialCost() {
		return ytdMaterialCost;
	}

	public void setYtdMaterialCost(double ytdMaterialCost) {
		this.ytdMaterialCost = ytdMaterialCost;
	}
}
