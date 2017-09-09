package com.timeclock.web.ClockBeta.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.timeclock.web.ClockBeta.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	@Modifying
	@Transactional
	@Query("CREATE com.timeclock.web.ClockBeta.model.UserRole SET "
			+ "user_name=:userName, role=:role")
	void newUserRole( 
			  @Param("userName")String userName, 
			  @Param("role")String role);
	
}
