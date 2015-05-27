package com.it.presenter;

import com.it.inter.onBasicView;
import com.it.model.collectInfoModel;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class collectInfoPresenter implements OnStringDataLoadListener{
	
	private onBasicView<String> mview;

	private collectInfoModel model;
	
	public collectInfoPresenter(onBasicView<String> view) {
		// TODO Auto-generated constructor stub
		mview=view;
	}
	
	
	public void collectInfo(long cid){
		model=new collectInfoModel();
		model.getcollectInfo(cid, this);
		model.addRequestParams();
		model.setHTTPMODE(HttpMethod.GET);
		model.sendHttp();
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
