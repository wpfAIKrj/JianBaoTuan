package com.it.presenter;

import com.it.bean.UserInfo;
import com.it.inter.onBasicView;
import com.it.model.LoginModel;

public class LoginPresenter implements OnBasicDataLoadListener<UserInfo> {
	
	
	private onBasicView<UserInfo> mview;
	private LoginModel mModel;
	
	public LoginPresenter(onBasicView iview) {
		// TODO Auto-generated constructor stub
		mview=iview;
	}

	public void startLogin(String name,String pwd){
		mModel=new LoginModel();
		mModel.setUserInfo(name, pwd, this);
		mModel.addRequestParams();
		mModel.sendHttp();
	}
	
	
	
	
	@Override
	public void onBaseDataLoaded(UserInfo data) {
		// TODO Auto-generated method stub
		if(data==null){
			mview.onFail("-1", "服务器异常");
		}else{
			mview.onSucess(data);
		}
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mview.onFail(errorCode,errorMsg);
	}
	



}
