package com.cartrawler.assessment.util;

import java.util.Comparator;
import com.cartrawler.assessment.car.CarResult;

public class CarResultCustomComparator implements Comparator<CarResult> {

	@Override
	public int compare(CarResult carOne, CarResult carTwo) {
		int result = 0;
		Integer carOneGroup = Utility.getGroupValue(carOne.getSupplierName());
		Integer carTwoGroup = Utility.getGroupValue(carTwo.getSupplierName());
		
		if (carOneGroup.compareTo(carTwoGroup) == 0) {  // if the group are same then sort base on carType
			Integer carOneType = Utility.getCarType(carOne.getSippCode());
			Integer carTwoType = Utility.getCarType(carTwo.getSippCode());
			if (carOneType.compareTo(carTwoType) == 0) { // if car type is same then sort based on rental cost (low to high)
				result = Double.compare(carOne.getRentalCost(), carTwo.getRentalCost());
			} else {
				result = carTwoType.compareTo(carOneType); // car type different hence sort them based on type
			}
		} else {
			result =  carTwoGroup.compareTo(carOneGroup); // car group different hence sort them based on group
		}
		return result;
	}

}
