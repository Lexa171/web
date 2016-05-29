package com.epam.spring.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.spring.dao.UserDao;
import com.epam.spring.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDaoImpl() {
		
	}
	
	protected Session getCurrentSession(){
	      return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

	@Override
	public void saveOrUpdateUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public User getUserById(Integer userId) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@Override
	public User getUserByName(String userName) {
		 if (userName != null) {
			   User user = null;
			   Criteria criteria = sessionFactory.getCurrentSession()
			     .createCriteria(User.class)
			     .add(Restrictions.eq("userName", userName));
			   user = (User) criteria.uniqueResult();
			   return user;
		 }
		 return null;
	}

	@Override
	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list(); 
        return listUser;
	}

	@Override
	public void deleteUser(User user) {		
		if (user != null)
        sessionFactory.getCurrentSession().delete(user);
	}	

}
