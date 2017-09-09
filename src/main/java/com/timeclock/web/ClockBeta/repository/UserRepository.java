package com.timeclock.web.ClockBeta.repository;

import org.springframework.data.repository.CrudRepository;

import com.timeclock.web.ClockBeta.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findById(int id);
	
	
}
