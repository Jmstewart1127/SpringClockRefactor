package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository extends CrudRepository<Material, Long> {

    Material findById(int id);

    Iterable<Material> findByJobId(int jobId);
//    @Query("SELECT Material FROM com.timeclock.web.ClockBeta.model.Material"
//            + " WHERE jobId= :jobId")
//    Material findMaterialsByJobId(@Param("jobId")int jobId);

}
