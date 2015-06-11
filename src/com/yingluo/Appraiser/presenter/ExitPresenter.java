package com.yingluo.Appraiser.presenter;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.ExitModel;
import com.yingluo.Appraiser.model.collectInfoModel;
import com.yingluo.Appraiser.model.sendFeedModel;
/**
 *	用户退出
 * @author Administrator
 *
 */
public class ExitPresenter implements OnStringDataLoadListener{
	
	private onBasicView<String> mview;

	private ExitModel model;
	
	public ExitPresenter(onBasicView<String> listener2) {
		// TODO Auto-generated constructor stub
		mview=listener2;
	}
	
	
	public void sendExit(){
		model=new ExitModel();
		model.sendExit(this);
	}
	
	
	
	@Override
	public void onBaseDataLoaded(String data) {
		// TODO Auto-generated method stub
		mview.onSucess(data);
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mview.onFail(errorCode, errorMsg);
	}

}
