package com.cartrawler.assessment.util;

public class Utility {

	public static Integer getGroupValue(String supplierName) {
		Integer val = switch (supplierName.toUpperCase()) {
		case "AVIS", "BUDGET", "ENTERPRISE" ,"FIREFLY" ,"HERTZ" ,"SIXT" ,"THRIFTY"-> 1;
		default -> 0;
		};
		return val;
	}
	
	public static Integer getCarType(String sipp) {
		String  sippInitial = sipp != null ? String.valueOf(sipp.charAt(0)) : "";
		Integer val = switch (sippInitial.toUpperCase()) {
		case "M"-> 4;
		case "E"-> 3;
		case "C"-> 1;
		default -> 1;
		};
		return val;
	}
	
	
	public static boolean isGroupPresent(String supplierName) {
		boolean val = switch (supplierName.toUpperCase()) {
		case "AVIS", "BUDGET", "ENTERPRISE" ,"FIREFLY" ,"HERTZ" ,"SIXT" ,"THRIFTY"-> true;
		default -> false;
		};
		return val;
	}

}
