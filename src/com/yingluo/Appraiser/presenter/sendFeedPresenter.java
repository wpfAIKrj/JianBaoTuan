package com.yingluo.Appraiser.presenter;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.collectInfoModel;
import com.yingluo.Appraiser.model.sendFeedModel;
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
