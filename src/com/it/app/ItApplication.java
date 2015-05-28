package com.it.app;

import java.util.List;

import io.rong.imkit.RongIM;

import com.it.bean.CollectionEntity;
import com.it.bean.HomeEntity;
import com.it.bean.TreasureEntity;
import com.it.bean.UserInfo;
import com.it.im.RongCloudEvent;
import com.it.im.RongImUtils;
import com.it.utils.BitmapsUtils;
import com.it.utils.FileUtils;
import com.it.utils.SqlDataUtil;

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
