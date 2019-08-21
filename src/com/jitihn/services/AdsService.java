package com.jitihn.services;

import java.util.List;

import com.jitihn.domains.Ads;

public interface AdsService {

		public List<Ads> getAds(int userId);

		public void saveAds(Ads ads);

		public List<Ads> getMyAds(int theId);
		
		public Ads getAd(int adId);
		
		
		
	}
