package com.sharemyride.dao;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharemyride.dto.ResponseDto;
import com.sharemyride.dto.UserRequestDto;
import com.sharemyride.model.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ResponseDto saveUser(UserRequestDto userRequestDto) {
		ResponseDto responseDto = new ResponseDto();
		User user = new User();
		user.setCompany(userRequestDto.getCompany());
		user.setCreatedDate(new Date());
		user.setEmail(userRequestDto.getEmail());
		user.setGender(userRequestDto.getGender());
		user.setName(userRequestDto.getName());
		user.setPhone(userRequestDto.getPhone());
		user.setRatingCount(0);
		user.setReviewCount(0);
		user.setUpdatedDate(new Date());
		user.setWallet(0);
		sessionFactory.getCurrentSession().save(user);
		responseDto.setMessage("success");
		responseDto.setStatus(200);
		return responseDto;
	}

}
