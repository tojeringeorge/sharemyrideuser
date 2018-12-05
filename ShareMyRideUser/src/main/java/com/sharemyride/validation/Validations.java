package com.sharemyride.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sharemyride.dto.ResponseDto;
import com.sharemyride.dto.UserRequestDto;
import com.sharemyride.service.ValidtionSevice;

@Component
public class Validations {
	
	@Autowired
	private ValidtionSevice validtionSevice;
	
	
	public ResponseDto userValidation(UserRequestDto userRequestDto) {
		ResponseDto responseDto = new ResponseDto();
	if (!validtionSevice.emptyNullValidation(userRequestDto.getCompany())) {
		responseDto.setMessage("Company null or empty");
		responseDto.setStatus(100);
		return responseDto;
	} else if (!validtionSevice.emptyNullValidation(userRequestDto.getEmail())) {
		responseDto.setMessage("Email null or empty");
		responseDto.setStatus(100);
		return responseDto;
	} else if (!validtionSevice.emailFormatValidation(userRequestDto.getEmail())) {
		responseDto.setMessage("Email format invalid");
		responseDto.setStatus(101);
		return responseDto;
	} else if (!validtionSevice.emptyNullValidation(userRequestDto.getGender())) {
		responseDto.setMessage("Gender null or empty");
		responseDto.setStatus(100);
		return responseDto;
	} else if(!validtionSevice.genderValidtion(userRequestDto.getGender())){
		responseDto.setMessage("Gender Invalid");
		responseDto.setStatus(101);
		return responseDto;
	} else if(!validtionSevice.emptyNullValidation(userRequestDto.getName())){
		responseDto.setMessage("Name null or empty");
		responseDto.setStatus(100);
		return responseDto;
	} else if(!validtionSevice.StringOnlyValidation(userRequestDto.getName())){
		responseDto.setMessage("Name Invalid");
		responseDto.setStatus(101);
		return responseDto;
	} else if(!validtionSevice.emptyNullValidation(userRequestDto.getPhone())){
		responseDto.setMessage("Phone null or empty");
		responseDto.setStatus(100);
		return responseDto;
	} else if(!validtionSevice.numberFormatValidation(userRequestDto.getPhone())){
		responseDto.setMessage("Phone Invalid");
		responseDto.setStatus(101);
		return responseDto;
	}
	responseDto.setMessage("success");
	responseDto.setStatus(200);
	return responseDto;
	
	}
}
