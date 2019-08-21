package com.jitihn.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jitihn.domains.Ads;
import com.jitihn.domains.Chat;
import com.jitihn.domains.Customer;
import com.jitihn.domains.User;

@Repository
public class ChatDAOImpl implements ChatDAO {

	Logger logger = Logger.getLogger(ChatDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAChat(Chat chat) {
		Session currentSession = sessionFactory.getCurrentSession();
		logger.debug("AddAChat is it last "+ chat);
		logger.debug("AddAChat is it last currentsession "+ currentSession);
		currentSession.clear();
		currentSession.save(chat);
		logger.debug("SAved chat "+ chat);
	}

	@Override
	public List<Chat> getChatList(int senderId, int receiverId, int adsId) {
		Session currSession = sessionFactory.getCurrentSession();
		List<Integer> ids = Arrays.asList(senderId, receiverId);
		Query<Chat>  theQuery = currSession.createQuery("from Chat ch where ch.sender.id in (:sender_id) AND ch.receiver.id in (:receiver_id) AND ch.ads.id=:ads_id order by date(messageTime)", Chat.class);
		theQuery.setParameterList("sender_id", ids);
		theQuery.setParameter("receiver_id", ids);
		theQuery.setParameter("ads_id", adsId);
		logger.debug("the query for chat "+ theQuery.getQueryString()); ;
		return theQuery.getResultList();
	}

	@Override
	public List<User> getUserChatWithMe(int currentUserId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Chat> theQuery = currentSession.createQuery("from Chat ch where ch.receiver.id = :currentuser");
		theQuery.setParameter("currentuser", currentUserId);
		
		 List<Chat> chatList = theQuery.getResultList();
		 
		 List<User> users = new ArrayList<User>();
		 for (Chat c: chatList) {
			 User user = c.getSender();
			 if(!checkUserExists(user, users)) {
				 users.add(user);
			 }
		 }
		 return users;
		
	}

	private boolean checkUserExists(User user, List<User> users) {
		for (User u: users) {
			if (user.getId() == u.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Ads> getAdsChatted(int userId, int loggedInUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Chat> theQuery = currentSession.createQuery("from Chat ch where ch.receiver.id = :currentuser");
		theQuery.setParameter("currentuser", loggedInUser);
		
		 List<Chat> chatList = theQuery.getResultList();
		 
		 List<Ads> adsList = new ArrayList<Ads>();
		 for (Chat c: chatList) {
			 Ads ads = c.getAds();
			 if(!checkAdsExists(ads, adsList)) {
				 adsList.add(ads);
			 }
		 }
		 return adsList;
	}

	private boolean checkAdsExists(Ads ads, List<Ads> adsList) {
		for (Ads a: adsList) {
			if (ads.getId() == a.getId()) {
				return true;
			}
		}
		return false;
	}
}
