package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.User;
import com.timeclock.web.ClockBeta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;
    
    /*
     * Get user id 
     */
    @CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping(value="/rest/login/{username}/{password}")
    public User loginTest(@PathVariable String username, @PathVariable String password) {
        return userService.findIdByCredentials(username, password);
    }
    

}
