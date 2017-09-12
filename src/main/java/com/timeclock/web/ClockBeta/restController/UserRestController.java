package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/rest/login/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password, Authentication auth) {

        return auth.getName();

    }

}
