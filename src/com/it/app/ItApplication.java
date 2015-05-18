package com.it.app;

import io.rong.imkit.RongIM;

import com.it.bean.UserInfo;
import com.it.im.RongCloudEvent;
import com.it.im.RongImUtils;
import com.it.utils.BitmapsUtils;
import com.it.utils.FileUtils;
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
		SqlDataUtil.initSql(this);
		FileUtils.init(this);
		FileUtils.getInstance().initFile();
		BitmapsUtils.init(this);
		//RongIM.init(this);
		//RongCloudEvent.init(this);
		//RongImUtils.init(this);
	}
	
}
