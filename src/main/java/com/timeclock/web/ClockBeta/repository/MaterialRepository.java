package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.Material;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MaterialRepository extends CrudRepository<Material, Long> {

    Material findById(int id);

}
