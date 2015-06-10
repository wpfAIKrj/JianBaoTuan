package com.it.presenter;

import com.it.inter.onBasicView;
import com.it.model.ExitModel;
import com.it.model.collectInfoModel;
import com.it.model.sendFeedModel;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
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
