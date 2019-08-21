package com.jitihn.dao;

import com.jitihn.domains.User;
import com.jitihn.domains.UserDetail;

public interface AccountDAO {
	public boolean userNameExist(String username);
	
	public User createUser(User user);
	
	public User loginUser(User user);
	
	public User getUser(int theId);
	
	public UserDetail getUserDetail(int userId);
	
	public UserDetail addUserDetails(UserDetail userDetail, int userid);
}
