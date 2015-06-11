package com.yingluo.Appraiser.app;

import java.util.List;

import io.rong.imkit.RongIM;

import com.yingluo.Appraiser.bean.CollectionEntity;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.im.RongCloudEvent;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;

import android.app.Application;

public class ItApplication extends Application {

	private UserInfo currnUser;

	private HomeEntity homeEntity;

	private List<CollectionEntity> hasIdentify;
	private List<CollectionEntity> unIdentify;

	public UserInfo getCurrnUser() {
		return currnUser;
	}

	public void setCurrnUser(UserInfo currnUser) {
		this.currnUser = currnUser;
	}

	public HomeEntity getHomeEntity() {
		return homeEntity;
	}

	public void setHomeEntity(HomeEntity homeEntity) {
		this.homeEntity = homeEntity;
	}

	public List<CollectionEntity> getHasIdentify() {
		return hasIdentify;
	}

	public void setHasIdentify(List<CollectionEntity> hasIdentify) {
		this.hasIdentify = hasIdentify;
	}
	
	

	public List<CollectionEntity> getUnIdentify() {
		return unIdentify;
	}

	public void setUnIdentify(List<CollectionEntity> unIdentify) {
		this.unIdentify = unIdentify;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SqlDataUtil.initSql(this);
		FileUtils.init(this);
		FileUtils.getInstance().initFile();
		BitmapsUtils.init(this);
		// RongIM.init(this);
		// RongCloudEvent.init(this);
		// RongImUtils.init(this);
	}

}
