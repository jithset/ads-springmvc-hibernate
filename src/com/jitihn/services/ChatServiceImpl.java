package com.jitihn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jitihn.dao.ChatDAO;
import com.jitihn.dao.CustomerDAO;
import com.jitihn.domains.Ads;
import com.jitihn.domains.Chat;
import com.jitihn.domains.Customer;
import com.jitihn.domains.User;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;

	@Override
	@Transactional
	public void addAChat(Chat chat) {
		chatDAO.addAChat(chat);
		
	}

	@Override
	@Transactional
	public List<Chat> getChatList(int senderId, int receiverId, int adsId) {
		return chatDAO.getChatList(senderId, receiverId, adsId);
	}

	@Override
	@Transactional
	public List<User> getUserChatWithMe(int currentUserId) {
		return chatDAO.getUserChatWithMe(currentUserId);
	}

	@Override
	@Transactional
	public List<Ads> getAdsChatted(int userId, int loggedInUser) {
		return chatDAO.getAdsChatted(userId, loggedInUser);
	}
}
