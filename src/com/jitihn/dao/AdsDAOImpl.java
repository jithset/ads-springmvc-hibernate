package com.jitihn.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jitihn.domains.Ads;
import com.jitihn.domains.Customer;

@Repository
public class AdsDAOImpl implements AdsDAO {

	Logger logger = Logger.getLogger(AdsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Ads> getAllAds(int userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Ads> theQuery = 
				currentSession.createQuery("from Ads ads where ads.user.id != :user_id",
											Ads.class);

		theQuery.setParameter("user_id", userId);
		List<Ads> ads = theQuery.getResultList();
		return ads;
	}

	@Override
	public void saveAds(Ads ads) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(ads);
		logger.debug("SAved adds "+ ads);
	}

	@Override
	public List<Ads> getMyAds(int theId) {
		logger.debug("Getting my ads of userid "+theId);
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Ads> theQuery = 
				currentSession.createQuery("from Ads ads where ads.user.id= :user_id",
											Ads.class);
		theQuery.setParameter("user_id", theId);
		List<Ads> ads = theQuery.getResultList();
		return ads;
	}

	@Override
	public Ads getAd(int adId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Ads ads = currentSession.get(Ads.class, adId);
		
		return ads;
	}

}
