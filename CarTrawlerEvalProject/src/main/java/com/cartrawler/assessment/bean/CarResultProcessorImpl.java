package com.cartrawler.assessment.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.cartrawler.assessment.car.CarResult;
import com.cartrawler.assessment.util.CarResultCustomComparator;
import com.cartrawler.assessment.util.Utility;

@Service
public class CarResultProcessorImpl implements CarResultProcessor {
	 Logger log = LoggerFactory.getLogger(CarResultProcessor.class);
	 Predicate<CarResult> corporateCust = x -> Utility.isGroupPresent(x.getSupplierName());
     Predicate<CarResult> nonCorporateCust = x -> !Utility.isGroupPresent(x.getSupplierName());
     
	public CarResultProcessorImpl() {};

	public List<CarResult> processCarResult(List<CarResult> carList) {
		return processCarResult(new HashSet<>(carList));
	}

	public List<CarResult> processCarResult(Set<CarResult> carSet) {
		// Code to remove the duplicates
		carSet = carSet.stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(
						Comparator.comparing(CarResult::getDescription).thenComparing(CarResult::getSupplierName)
								.thenComparing(CarResult::getSippCode).thenComparing(CarResult::getFuelPolicy))));

		// Code to sort based on custom logic (corporate group then car type and finally rental cost)
		CarResultCustomComparator carCustomComparator = new CarResultCustomComparator();
		List<CarResult> carList = new ArrayList<>(carSet);
		Collections.sort(carList, carCustomComparator);
		return carList;
	}

	@Override
	public List<CarResult> processCartWithoutFuelTypeFullFull(List<CarResult> carList) {
		
		   List<CarResult> corporateCarList = carList.stream().filter(corporateCust).collect(Collectors.toList());
		   List<CarResult> nonCorporateCarList = carList.stream().filter(nonCorporateCust).collect(Collectors.toList());
		   
	       Double medianForCorporateCust = corporateCarList.stream().map(x ->x.getRentalCost()).sorted().skip(Math.max(0, ((corporateCarList.size() + 1) / 2) - 1))
	        .limit(1 + (1 + corporateCarList.size()) % 2).mapToDouble(Double::doubleValue).average().getAsDouble();
	       
	       Double medianForNonCorporateCust = nonCorporateCarList.stream().map(x ->x.getRentalCost()).sorted().skip(Math.max(0, ((nonCorporateCarList.size() + 1) / 2) - 1))
	                .limit(1 + (1 + nonCorporateCarList.size()) % 2).mapToDouble(Double::doubleValue).average().getAsDouble();
	       
	       log.info("====================================================================================================================================");
	       log.info("Median for corporate customer  :: " + medianForCorporateCust + " Median for non corporate customer :: " + medianForNonCorporateCust);
	       log.info("====================================================================================================================================");
	        
	        List<CarResult> corporateCarListLessThenMedian  = corporateCarList.stream().filter(x -> x.getRentalCost() <= medianForCorporateCust).collect(Collectors.toList());
	        List<CarResult> nonCorporateCarListLessThenMedian  = corporateCarList.stream().filter(x -> x.getRentalCost() <= medianForNonCorporateCust).collect(Collectors.toList());
	       
	        List<CarResult> finalList = new ArrayList<>();
	        finalList.addAll(corporateCarListLessThenMedian);
	        finalList.addAll(nonCorporateCarListLessThenMedian);
	        
	         // Code to sort based on custom logic (corporate group then car type and finally rental cost)
			CarResultCustomComparator carCustomComparator = new CarResultCustomComparator();
			Collections.sort(finalList, carCustomComparator);
	        return finalList;
	}

}
