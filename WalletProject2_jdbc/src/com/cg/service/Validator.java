package com.cg.service;

public interface Validator {

	String aidpattern="[1-9][0-9][0-9]";
	String mobilepattern="[1-9]{1}[0-9]{9}";

	String namepattern="([A-Z]{1}[a-z]{2}([a-z])*) ([A-Z]{1}[a-z]{2}([a-z])*)";
	public static boolean validatedata(String data, String pattern) {
		return data.matches(pattern);
		
	}
	
	
}
