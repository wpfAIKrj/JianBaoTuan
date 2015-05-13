package com.it.presenter;

import com.it.bean.UserInfo;
import com.it.model.ProfileModel;
import com.it.view.inter.ProfileView;


public class ProfilePresenter implements OnBasicDataLoadListener<UserInfo> {
	
	
	

	private ProfileView mview;
	private ProfileModel mModel;
	
	public ProfilePresenter(ProfileView iview) {
		// TODO Auto-generated constructor stub
		mview=iview;
		mModel=new ProfileModel();
	}

	public void updataInfo(String portrait,String qq,String email){
		mModel.setupdataInfo(portrait, qq,email, this);
		mModel.addRequestParams();
		mModel.sendHttp();
	}
	
	
	
	
	@Override
	public void onBaseDataLoaded(UserInfo data) {
		// TODO Auto-generated method stub
		if(data==null){
			mview.UpdataFail("-1", "服务器异常");
		}else{
			mview.UpdataSucess(data);
		}
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mview.UpdataFail(errorCode,errorMsg);
	}

}
