package com.cartrawler.assessment.bean;

import java.util.List;
import java.util.Set;

import com.cartrawler.assessment.car.CarResult;

public interface CarResultProcessor {
	
	public List<CarResult> processCarResult(List<CarResult> carList);
	
	public List<CarResult> processCarResult(Set<CarResult> carSet);
	
	public List<CarResult> processCartWithoutFuelTypeFullFull(List<CarResult> carList);
}
