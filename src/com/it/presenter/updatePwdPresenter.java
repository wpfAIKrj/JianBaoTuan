package com.it.presenter;

import com.it.bean.UserInfo;
import com.it.inter.onBasicView;
import com.it.model.updataPwdModel;

public class updatePwdPresenter implements OnBasicDataLoadListener<UserInfo> {

	
	private onBasicView<UserInfo> mview;

	private updataPwdModel model;
	public updatePwdPresenter(onBasicView<UserInfo> view) {
		// TODO 自动生成的构造函数存根
		this.mview=view;
	}
	
	public void updatePassword(String oldpwd,String newpwd){
		model=new updataPwdModel();
		model.updatePwd(oldpwd, newpwd, this);
		
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO 自动生成的方法存根
		mview.onFail(errorCode, errorMsg);
	}

	@Override
	public void onBaseDataLoaded(UserInfo data) {
		// TODO 自动生成的方法存根
		mview.onSucess(data);
	}
	
	


}
