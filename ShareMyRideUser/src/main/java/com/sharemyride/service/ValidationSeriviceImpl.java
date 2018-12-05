package com.sharemyride.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Service;

@Service
public class ValidationSeriviceImpl implements ValidtionSevice {

	public boolean StringOnlyValidation(String value) {
		return value.chars().allMatch(Character::isLetter);
	}

	public boolean dateFormatValidation(String date) {
		return false;
	}

	public boolean numberFormatValidation(String number) {
		return number.chars().allMatch(x -> Character.isDigit(x));
	}

	public boolean genderValidtion(String gender) {
		return gender.equals("male") || gender.equals("female") || gender.equals("others");
	}

	public boolean emptyNullValidation(String value) {
		try {
			return !value.equals("") && value != null;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean emailFormatValidation(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
