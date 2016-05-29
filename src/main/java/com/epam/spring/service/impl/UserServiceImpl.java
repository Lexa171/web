package com.epam.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.spring.dao.UserDao;
import com.epam.spring.model.User;
import com.epam.spring.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

	@Override
	@Transactional
	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	@Transactional
	public  void saveOrUpdateUser(User user) {	
		userDao.saveOrUpdateUser(user);		
	}
	
	
}
