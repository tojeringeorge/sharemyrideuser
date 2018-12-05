package com.sharemyride.service;

import com.sharemyride.dto.ResponseDto;
import com.sharemyride.dto.UserRequestDto;

public interface UserService {
	ResponseDto saveUser(UserRequestDto userRequestDto);
}
