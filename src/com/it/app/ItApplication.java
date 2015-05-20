package com.it.app;

import io.rong.imkit.RongIM;

import com.it.bean.HomeEntity;
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
