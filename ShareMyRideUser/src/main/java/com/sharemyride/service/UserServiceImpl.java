package com.sharemyride.service;
import org.aspectj.weaver.IEclipseSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharemyride.dao.UserDao;
import com.sharemyride.dto.ResponseDto;
import com.sharemyride.dto.UserRequestDto;
import com.sharemyride.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public ResponseDto saveUser(UserRequestDto userRequestDto) {
		return userDao.saveUser(userRequestDto);
	}

}
