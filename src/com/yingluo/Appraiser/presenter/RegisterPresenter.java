package com.yingluo.Appraiser.presenter;

import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.LoginModel;
import com.yingluo.Appraiser.model.RegisterModel;

public class RegisterPresenter implements OnBasicDataLoadListener<UserInfo> {

	private onBasicView<UserInfo> mview;
	private RegisterModel mModel;

	public RegisterPresenter(onBasicView iview) {
		mview = iview;
	}

	public void startRegister(String name, String pwd) {
		mModel = new RegisterModel();
		mModel.setUserInfo(name, pwd, this);
		mModel.addRequestParams();
		mModel.sendHttp();
	}

	@Override
	public void onBaseDataLoaded(UserInfo data) {
		if (data == null) {
			mview.onFail("-1", "服务器异常");
		} else {
			mview.onSucess(data);
		}
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		mview.onFail(errorCode, errorMsg);
	}

}
