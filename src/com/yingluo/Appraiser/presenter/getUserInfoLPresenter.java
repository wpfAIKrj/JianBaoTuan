package com.yingluo.Appraiser.presenter;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.getUserInfoModel;


/**
 * 获取我的入口信息
 * @author Administrator
 *
 */
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
