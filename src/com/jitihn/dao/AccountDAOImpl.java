package com.jitihn.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jitihn.domains.User;
import com.jitihn.domains.UserDetail;

@Repository
public class AccountDAOImpl implements AccountDAO {

	Logger logger = Logger.getLogger(AccountDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User createUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
		return user;
	}

	@Override
	public User loginUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from User where username = :username and password = :password");
		query.setParameter("username", user.getUsername());
		query.setParameter("password", user.getPassword());
		user = (User) query.uniqueResult();
		if(user != null) {
			return user;
		}
		return null;
	}

	@Override
	public UserDetail addUserDetails(UserDetail userDetail, int userid) {
		logger.debug("Updating userdetail "+ userDetail);
		
		
		Session currentSession = sessionFactory.getCurrentSession();
		UserDetail userDet = getUserDetail(userid);
		logger.debug("Get userdetail get from "+ userDet);
		if (userDet.getId()!=null) {
			logger.debug("Updating user detail" + userDet);
			userDetail.setId(userDet.getId());
			currentSession.clear();
			logger.debug("Updating value "+userDetail);
			currentSession.update(userDetail);
		} else {
			logger.debug("Adding user detail");
			currentSession.saveOrUpdate(userDetail);
		}
		return userDetail;
	}

	@Override
	public User getUser(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, theId);
		return user;
	}
	
	public User getUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from User where username = :username");
		query.setParameter("username", username);
		System.out.println("Get user with username");
		User user = (User) query.uniqueResult();
		System.out.println("User returned "+ user);
		if(user != null) {
			return user;
		}
		return null;
	}

	@Override
	public boolean userNameExist(String username) {
		User user = getUser(username);
		return (user != null) ? true: false;
	}

	@Override
	public UserDetail getUserDetail(int userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		logger.debug("Getting user detail");
		Query query = currentSession.createQuery("from UserDetail ud where ud.user.id = :user_id");
		query.setParameter("user_id", userId);
		UserDetail userDetail = (UserDetail) query.uniqueResult();
		logger.debug("Get current detail "+ userDetail);
		if(userDetail != null) {
			return userDetail;
		}
		return new UserDetail();
	}

}
