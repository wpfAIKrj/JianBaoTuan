package com.yingluo.Appraiser.presenter;

import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.ForgotPwdModel;
import com.yingluo.Appraiser.model.LoginModel;
import com.yingluo.Appraiser.model.RegisterModel;

public class ForgetPresenter implements OnBasicDataLoadListener<UserInfo> {
	
	
	private onBasicView<UserInfo> mview;
	private ForgotPwdModel mModel;
	
	public ForgetPresenter(onBasicView iview) {
		// TODO Auto-generated constructor stub
		mview=iview;
	}

	public void startForgetPwd(String name,String pwd){
		mModel=new ForgotPwdModel();
		mModel.forgetPwd(name, pwd, this);
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
