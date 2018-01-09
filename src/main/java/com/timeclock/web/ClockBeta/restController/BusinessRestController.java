package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessRestController {

    @Autowired
    BusinessService businessService;

    /*
    * Show businesses by user id
    */
    @CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping(value = "/rest/user/{id}/businesses")
    public Iterable<Business> getBusinessesByUserId(@PathVariable int id) {
        return businessService.findBusinessesByUserId(id);
    }

    /*
    * Show businesses by current logged in user
    */
    @CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping(value = "/rest/user/businesses")
    public Iterable<Business> getBusinessesByCurrentLogin(Authentication auth) {
        System.out.println(":: " + businessService.findByLoggedInUserId(auth));
        return businessService.findByLoggedInUserId(auth);
    }

}
