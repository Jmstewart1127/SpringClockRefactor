package com.timeclock.web.ClockBeta.configuration;

import com.timeclock.web.ClockBeta.logistics.ClockLogic;
import com.timeclock.web.ClockBeta.logistics.MaterialCostLogic;
import com.timeclock.web.ClockBeta.logistics.PaymentLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MaterialCostLogic materialCostLogic() {
        return new MaterialCostLogic();
    }

    @Bean
    public ClockLogic clockLogic() {
        return new ClockLogic();
    }

    @Bean
    public PaymentLogic paymentLogic() {
        return new PaymentLogic();
    }
}
