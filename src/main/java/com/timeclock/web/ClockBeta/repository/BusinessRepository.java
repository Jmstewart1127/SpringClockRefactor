package com.timeclock.web.ClockBeta.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.timeclock.web.ClockBeta.model.Business;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface BusinessRepository extends CrudRepository <Business, Long> {
	
	Business findById(int id);
	
	Business findByBizName(String bizName);

	Iterable<Business> findByAdminId(int adminId);

	double findYtdLaborCostById(int id);

	double findYtdMaterialCostById(int id);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Business "
			+ "SET ytd_material_cost=:ytdMaterialCost WHERE id=:id")
	void updateYtdMaterialCost(
			@Param("id")int id,
			@Param("ytdMaterialCost")double ytdMaterialCost);

	@Modifying
	@Transactional
	@Query("UPDATE com.timeclock.web.ClockBeta.model.Business "
			+ "SET ytd_material_cost=:ytdMaterialCost WHERE id=:id")
	void updateYtdLaborCost(
			@Param("id")int id,
			@Param("ytdLaborCost")double ytdLaborCost);
}
