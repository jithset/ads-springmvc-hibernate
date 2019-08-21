package com.jitihn.controllers;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jitihn.domains.User;
import com.jitihn.domains.UserDetail;
import com.jitihn.services.AccountService;
import com.jitihn.utils.Utils;
@Controller
@RequestMapping("/account")
public class AccountController {
	
	Logger logger = Logger.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	

	@GetMapping("/logout")
	public String logoutUser(Model model, HttpServletRequest request) {
		request.getSession().setAttribute("currentuser", null);
		return "redirect:login";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("user", new User());
		
		return "login";
	}

	@PostMapping("/login")
	public String checkLoginPage(@ModelAttribute("user") User user, HttpServletRequest request, Model model) {
		user = accountService.loginUser(user);
		if (user != null) {
			System.out.println("Login success");
			request.getSession().setAttribute("currentuser", user);;
			return "redirect:/";
		} 
		model.addAttribute("error", "Invalid User");
        
		System.out.println("Login failed");
		return "login";
	}
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute("user") User user, Model model) {
		boolean status = accountService.userNameExist(user.getUsername()); // status true if already exist
		
		if (!status) {
			user = accountService.createUser(user);
			System.out.println("Added User "+ user);
			return "redirect:login";
		} else {
			model.addAttribute("error", "Username already exists");
			return "register";
		}
	}

	

	@GetMapping("/userdetail")
	public String getDetailPage(Model model, HttpServletRequest request) {
		User user = Utils.getCurrentUser(request);
		if (user != null) {
			UserDetail detail = accountService.getUserDetails(user.getId());
			model.addAttribute("userdetail", detail);
			logger.debug("Detail of user "+detail);
			return "userdetail";
		} else {
			return "redirect:login";
		}
	}
	
	@PostMapping("/userdetail")
	public String addUserDetail(@ModelAttribute("userdetail") UserDetail userDetail, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = Utils.getCurrentUser(request);
		if (user != null) {
			logger.debug("user "+ user);
			userDetail.setUser(user);
			logger.debug("add user detail "+ userDetail);
			userDetail = accountService.addUserDetails(userDetail, user.getId());
			return "redirect:/";
		} else {
			return "redirect:login";			
		}
	}
}
