package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.service.PayRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayRollRestController {

    @Autowired
    PayRollService payRollService;
}
