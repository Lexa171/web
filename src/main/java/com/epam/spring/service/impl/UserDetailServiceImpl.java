package com.epam.spring.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.spring.dao.UserDao;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.epam.spring.model.User user = userDao.getUserByName(username);
		if (user == null)
			throw new UsernameNotFoundException("User with name '" + username + "' not found");
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		return new User(user.getUserName(), user.getUserPassword(), authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(String userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
		SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		if (userRole.equals("ROLE_USER"))
			setAuths.add(userAuthority);
		else if (userRole.equals("ROLE_ADMIN")) {
			setAuths.add(userAuthority);
			setAuths.add(adminAuthority);
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}