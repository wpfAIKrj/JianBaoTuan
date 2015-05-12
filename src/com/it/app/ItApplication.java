package com.it.app;

import io.rong.imkit.RongIM;

import com.it.bean.UserInfo;
import com.it.utils.SqlDataUtil;

import android.app.Application;

public class ItApplication extends Application {

	private UserInfo currnUser;

	public UserInfo getCurrnUser() {
		return currnUser;
	}

	public void setCurrnUser(UserInfo currnUser) {
		this.currnUser = currnUser;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		RongIM.init(this);
		SqlDataUtil.initSql(this);
	}
	
}
