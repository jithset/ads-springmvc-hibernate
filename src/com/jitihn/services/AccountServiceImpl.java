package com.jitihn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jitihn.dao.AccountDAO;
import com.jitihn.domains.User;
import com.jitihn.domains.UserDetail;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	

	@Override
	@Transactional
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return accountDAO.createUser(user);
	}


	@Override
	@Transactional
	public User loginUser(User user) {
		return accountDAO.loginUser(user);
	}


	@Override
	@Transactional
	public User getUser(int userId) {
		return accountDAO.getUser(userId);
	}


	@Override
	@Transactional
	public UserDetail addUserDetails(UserDetail userDetail, int userId) {
		return accountDAO.addUserDetails(userDetail, userId);
	}


	@Override
	@Transactional
	public boolean userNameExist(String username) {
		return accountDAO.userNameExist(username);
	}


	@Override
	@Transactional
	public UserDetail getUserDetails(int userId) {
		return accountDAO.getUserDetail(userId);
	}
}
