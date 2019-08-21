package com.jitihn.dao;

import java.util.List;

import com.jitihn.domains.Ads;

public interface AdsDAO {
	List<Ads> getAllAds(int userId);
	
	public void saveAds(Ads ads);

	public List<Ads> getMyAds(int userId);
	
	public Ads getAd(int addId);
}
