package com.timeclock.web.ClockBeta.configuration;

import com.timeclock.web.ClockBeta.logistics.TimeLogic;
import com.timeclock.web.ClockBeta.logistics.MaterialCostLogic;
import com.timeclock.web.ClockBeta.logistics.PaymentLogic;
import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MaterialCostLogic materialCostLogic() {
        return new MaterialCostLogic();
    }

    @Bean
    public TimeLogic clockLogic() {
        return new TimeLogic();
    }

    @Bean
    public PaymentLogic paymentLogic() {
        return new PaymentLogic();
    }
    
    @Bean 
    public UserAuthDetails userAuthData() {
    	return new UserAuthDetails();
    }
}
