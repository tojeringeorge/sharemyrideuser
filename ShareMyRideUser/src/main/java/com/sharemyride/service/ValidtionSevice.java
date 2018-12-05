package com.sharemyride.service;

public interface ValidtionSevice {
	public boolean StringOnlyValidation(String value);
	public boolean dateFormatValidation(String date);
	public boolean numberFormatValidation(String number);
	public boolean genderValidtion(String gender);
	public boolean emptyNullValidation(String value);
	public boolean emailFormatValidation(String email);
}
