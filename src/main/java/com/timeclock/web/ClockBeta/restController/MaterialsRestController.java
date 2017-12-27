package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaterialsRestController {

    @Autowired
    MaterialService materialService;

    /*
    * Gets materials by jobId
    */
    @RequestMapping("/rest/jobs/{jobId}/material")
    public Iterable<Material> getMaterials(@PathVariable int jobId) {
        return materialService.findByJobId(jobId);
    }

    /*
    * Gets total cost of materials for job
    */
    @RequestMapping("/rest/jobs/{jobId}/total/cost")
    public double getTotalCostOfAllMaterialsForJob(@PathVariable int jobId) {
        return materialService.totalPriceOfAllJobMaterials(jobId);
    }

}
