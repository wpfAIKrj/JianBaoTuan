package com.it.presenter;

import com.it.inter.onBasicView;
import com.it.model.collectInfoModel;
import com.it.model.sendFeedModel;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 *	发送意见
 * @author Administrator
 *
 */
public class sendFeedPresenter implements OnStringDataLoadListener{
	
	private onBasicView<String> mview;

	private sendFeedModel model;
	
	public sendFeedPresenter(onBasicView<String> view) {
		// TODO Auto-generated constructor stub
		mview=view;
	}
	
	
	public void sendFeed(String context){
		model=new sendFeedModel();
		model.sendFeed(context, this);
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
