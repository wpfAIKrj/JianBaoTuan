package com.it.presenter;

import com.it.bean.UserInfo;
import com.it.model.LoginModel;
import com.it.model.RegisterModel;
import com.it.view.inter.LoginView;
import com.it.view.inter.RegisterView;

public class RegisterPresenter implements OnBasicDataLoadListener<UserInfo> {
	
	
	private RegisterView mview;
	private RegisterModel mModel;
	
	public RegisterPresenter(RegisterView iview) {
		// TODO Auto-generated constructor stub
		mview=iview;
		mModel=new RegisterModel();
	}

	public void startRegister(String name,String pwd){
		mModel.setUserInfo(name, pwd, this);
		mModel.addRequestParams();
		mModel.sendHttp();
	}
	
	
	
	
	@Override
	public void onBaseDataLoaded(UserInfo data) {
		// TODO Auto-generated method stub
		if(data==null){
			mview.RegisterFail("-1", "服务器异常");
		}else{
			mview.RegisterSucess(data);
		}
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mview.RegisterFail(errorCode,errorMsg);
	}
	



}
