package com.jitihn.utils;

import javax.servlet.http.HttpServletRequest;

import com.jitihn.domains.User;

public class Utils {

	public static User getCurrentUser(HttpServletRequest request) {
		 return (User)request.getSession().getAttribute("currentuser");
	}

}
