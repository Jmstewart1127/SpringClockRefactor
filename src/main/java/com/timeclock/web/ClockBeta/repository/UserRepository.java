package com.timeclock.web.ClockBeta.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.timeclock.web.ClockBeta.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findById(int id);
	
	@Query("SELECT id FROM com.timeclock.web.ClockBeta.model.User WHERE user_name= :username")
	int findIdByUserName(@Param("username")String username);
	
	@Query("SELECT id FROM com.timeclock.web.ClockBeta.model.User WHERE user_name= :username AND password= :password")
	int findIdByCredentials(@Param("username")String username,
							@Param("password")String password);
	
}
