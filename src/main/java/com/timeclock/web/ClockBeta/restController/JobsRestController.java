package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class JobsRestController {

    @Autowired
    JobsService jobsService;

    /*
    * Puts address into array for use with geolocation api
    */
    @RequestMapping("/rest/jobs/address/{id}")
    public String[] showAddressArray(@PathVariable int id) {
        return jobsService.getJobAddressArray(id);
    }


//    @RequestMapping("/rest/jobs/address/{id}")
//    public String[] showAddressArray(@PathVariable int id) {
//        return jobsService.getJobAddressArray(id);
//    }

}
