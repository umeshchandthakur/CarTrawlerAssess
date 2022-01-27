package com.cartrawler.assessment.app;

import org.springframework.context.annotation.Bean;

import com.cartrawler.assessment.bean.CarResultProcessor;
import com.cartrawler.assessment.bean.CarResultProcessorImpl;

public class AppConfig {
	
	@Bean
    public CarResultProcessor carResultProcessor() {
        return new CarResultProcessorImpl();
    }
}
