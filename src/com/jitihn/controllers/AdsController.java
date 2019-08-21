package com.jitihn.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import com.jitihn.domains.Ads;
import com.jitihn.domains.Chat;
import com.jitihn.domains.Customer;
import com.jitihn.domains.User;
import com.jitihn.services.AccountService;
import com.jitihn.services.AdsService;
import com.jitihn.services.ChatService;
import com.jitihn.services.CustomerService;
import com.jitihn.utils.Utils;

@Controller
@RequestMapping("/ads")
public class AdsController {

	Logger logger = Logger.getLogger(AdsController.class);
	
	@Autowired
	private AdsService adsService;
	
	@Autowired
	private ChatService chatService;
	

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/myads")
	public String listMyAds(Model theModel, HttpServletRequest request) {
		User user = Utils.getCurrentUser(request);
		if (user != null) {
			List<Ads> ads = adsService.getMyAds(user.getId());
			System.out.println("My Ads list"+ ads);
			theModel.addAttribute("ads", ads);
			theModel.addAttribute("ischat", false);
			return "list-ads";
		} 
		return "redirect:/account/login";
	}
	
	@GetMapping("/list")
	public String listAds(Model theModel, HttpServletRequest request) {
		User user = Utils.getCurrentUser(request);
		if (user != null) {
			List<Ads> ads = adsService.getAds(user.getId());
			System.out.println("All Ads list"+ ads);
			theModel.addAttribute("ads", ads);
			theModel.addAttribute("ischat", true);
			return "list-ads";
		} 
		return "redirect:/account/login";
	}
	
	@GetMapping("/add")
	public String showFormForAdd(Model theModel) {		
		theModel.addAttribute("ads", new Ads());
		return "ads-form";
	}
	
	@PostMapping("/add")
	public String saveAds(@ModelAttribute("ads") Ads ads, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentuser");
		System.out.println("Save Ads "+ user);
		if (user != null) {
			ads.setUser(user);
			adsService.saveAds(ads);
			return "redirect:/ads/myads";
		}
		return "redirect:/account/login";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("adsId") int theId,
									Model theModel) {
		Ads ads = adsService.getAd(theId);
		theModel.addAttribute("ads", ads);
		return "ads-form";
	}
	
	@GetMapping("/chat") // chat for the add id given
	public String chatOnAdd(@RequestParam("id") int theId,
									Model theModel, HttpServletRequest request) {
		Ads ads = adsService.getAd(theId); //add model with receiver user
		User user = Utils.getCurrentUser(request); // logged in user
		
		if (user != null) {
		
			// get current chat 
			List<Chat> currentChat = chatService.getChatList(user.getId(), ads.getUser().getId(), ads.getId());
			logger.debug("Cureent chat items "+ currentChat);
			theModel.addAttribute("adsdetail", ads);
			theModel.addAttribute("currentchats", currentChat);
			theModel.addAttribute("chat", new Chat());
			//theModel.addAttribute("currentchatonadd", currentchatlist);
			return "chatonadd";
		}
		return "redirect:/account/login";
	}
	
	@PostMapping("/chat")
	public String sendMessage(@ModelAttribute("chat") Chat chat, @RequestParam("adsid") int adsId, HttpServletRequest request) {
		Ads ads = adsService.getAd(adsId); 
		chat.setAds(ads);
		User sender = Utils.getCurrentUser(request);
		if (sender != null) {
			chat.setSender(sender);
			User receiver = ads.getUser();
			chat.setReceiver(receiver);
			chat.setMessageTime(LocalDateTime.now());
			logger.debug("Send data "+ chat);
			
			// add message to db
			
			chatService.addAChat(chat);
			logger.debug("Success chat added ");
			return "redirect:/ads/chat?id="+adsId;
		}
		return "redirect:/account/login";
	}
	
	@GetMapping("/mychats")
	public String chatWithMe(Model model, HttpServletRequest request) {

		User currentUser = Utils.getCurrentUser(request);
		if (currentUser != null) {
			List<User> chatUsers = chatService.getUserChatWithMe(currentUser.getId());
			logger.debug("Currently chatted users "+ chatUsers);
			model.addAttribute("userchatted", chatUsers);
			return "mychats";
		}
		return "redirect:/account/login";
	}
	
	@GetMapping("/chatted")
	public String getAdsChatted(@RequestParam("userid") int userId, HttpServletRequest request, Model model) {
		logger.debug("get request getchatted ads "+ userId);
		User currentUser = Utils.getCurrentUser(request);
		logger.debug("get request currentuser "+ currentUser);
		if (currentUser != null) {
			List<Ads> adsChatted = chatService.getAdsChatted(userId, currentUser.getId());
			logger.debug("chatted ads "+ adsChatted);
			model.addAttribute("chattedads", adsChatted);
			return "adschatted";
		}
		return "redirect:/account/login";
	}
	
	@GetMapping("/chatsonads")
	public String getChatOnAds(@RequestParam("userid") int userId, @RequestParam("adid") int adId, HttpServletRequest request, Model model) {
		logger.debug("Success get"+ userId+ adId);
		User currentUser = Utils.getCurrentUser(request);
		if (currentUser != null) {
			List<Chat> adsChatted = chatService.getChatList(userId, currentUser.getId(), adId);
			logger.debug("chatted ads "+ adsChatted);
			model.addAttribute("adschats", adsChatted);
			model.addAttribute("receiverId", userId);
			model.addAttribute("adsId", adId);
			model.addAttribute("chat", new Chat());
			return "adschats";
		}
		return "redirect:/account/login";
	}
	
	@PostMapping("/addmychat")
	public String sendMyMessage(@ModelAttribute("chat") Chat chat, @ModelAttribute("receiverId") int receiverId, @ModelAttribute("adsid") int adsId, HttpServletRequest request) {
		logger.debug("test if control addmychat");
		Ads ads = adsService.getAd(adsId); 
		chat.setAds(ads);
		User sender = Utils.getCurrentUser(request);
		if (sender != null) {
			chat.setSender(sender);
			User receiver = accountService.getUser(receiverId);
			chat.setReceiver(receiver);
			chat.setMessageTime(LocalDateTime.now());
			logger.debug("Send data "+ chat);
			chatService.addAChat(chat);
			logger.debug("Success chat added ");
			return "redirect:/ads/chatsonads?userid="+receiverId+"&adid="+adsId;
		}
		return "redirect:/account/login";
	}
	
	
}
