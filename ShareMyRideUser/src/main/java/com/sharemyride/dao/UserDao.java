package com.sharemyride.dao;

import com.sharemyride.dto.ResponseDto;
import com.sharemyride.dto.UserRequestDto;

public interface UserDao {
	ResponseDto saveUser(UserRequestDto userRequestDto);
}
