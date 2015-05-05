package com.it.app;

import com.it.bean.UserInfo;

import android.app.Application;

public class ItApplication extends Application {

	private UserInfo currnUser;

	public UserInfo getCurrnUser() {
		return currnUser;
	}

	public void setCurrnUser(UserInfo currnUser) {
		this.currnUser = currnUser;
	}
	
	
	
}
