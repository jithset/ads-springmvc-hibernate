package com.jitihn.services;

import com.jitihn.domains.User;
import com.jitihn.domains.UserDetail;

public interface AccountService {

	public boolean userNameExist(String username);
	
	public User createUser(User user);

	public User getUser(int userId);
	
	public User loginUser(User user);
	
	public UserDetail addUserDetails(UserDetail userDetail, int userId);

	public UserDetail getUserDetails(int userId);
	
}
