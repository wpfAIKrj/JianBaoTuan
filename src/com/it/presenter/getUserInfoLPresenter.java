package com.it.presenter;

import com.it.bean.UserInfo;
import com.it.inter.onBasicView;
import com.it.model.getUserInfoModel;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;



public class getUserInfoLPresenter implements OnStringDataLoadListener {

	private onBasicView<String> mView;
	private getUserInfoModel model;
	
	public getUserInfoLPresenter(onBasicView<String> view) {
		// TODO Auto-generated constructor stub
		mView=view;
	}
	
	public void getUserInfo(){
		model=new getUserInfoModel();
		model.getUserinfo(this);
		model.setHTTPMODE(HttpMethod.GET);
		model.sendHttp();
	}
	
	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mView.onFail(errorCode, errorMsg);
	}

	@Override
	public void onBaseDataLoaded(String data) {
		// TODO Auto-generated method stub
		mView.onSucess(data);
	}

}
