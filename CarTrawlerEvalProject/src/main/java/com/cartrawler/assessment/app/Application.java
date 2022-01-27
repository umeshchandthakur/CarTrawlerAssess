package com.cartrawler.assessment.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cartrawler.assessment.bean.CarResultProcessor;
import com.cartrawler.assessment.bean.CarResultProcessorImpl;
import com.cartrawler.assessment.car.CarResult;
import com.cartrawler.assessment.view.Display;
import com.cartrawler.assessment.view.DisplayGeneric;

@SpringBootApplication
public class Application {
	static Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		
		DisplayGeneric display = new DisplayGeneric();

		CarResultProcessor processResult = ctx.getBean(CarResultProcessorImpl.class);
		log.info("==================Elements sorted as per requirement================="); //Duplicate records removed and sorting done as per requirement (corporate group then carType, then cost) 
		List<CarResult> result = processResult.processCarResult(AssessmentRunner.CARS);
		//result.stream().forEach(System.out::println);
		display.render(result);
		
		
		log.info("==================Elements sorted as per requirement after removing (FuelType.FULLFULL) greater then median=================");
		result = processResult.processCartWithoutFuelTypeFullFull(result);
		//result.stream().forEach(System.out::println);
		display.render(result);
		
		ctx.close();
	}
}
