package com.jitihn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jitihn.dao.AdsDAO;
import com.jitihn.dao.CustomerDAO;
import com.jitihn.domains.Ads;
import com.jitihn.domains.Customer;

@Service
public class AdsServiceImpl implements AdsService {

	@Autowired
	private AdsDAO adsDAO;

	@Override
	@Transactional	
	public List<Ads> getAds(int userId) {
		return adsDAO.getAllAds(userId);
	}

	@Override
	@Transactional
	public void saveAds(Ads ads) {
		adsDAO.saveAds(ads);
	}

	@Override
	@Transactional
	public List<Ads> getMyAds(int theId) {
		return adsDAO.getMyAds(theId);
	}

	@Override

	@Transactional
	public Ads getAd(int adId) {
		return adsDAO.getAd(adId);
	}
	
}
