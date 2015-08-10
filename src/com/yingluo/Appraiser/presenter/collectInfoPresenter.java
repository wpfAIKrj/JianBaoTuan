package com.yingluo.Appraiser.presenter;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.collectInfoModel;
/**
 * 收藏文章
 * @author Administrator
 *
 */
public class collectInfoPresenter implements OnStringDataLoadListener{
	
	private onBasicView<String> mview;

	private collectInfoModel model;
	
	public collectInfoPresenter(onBasicView<String> view) {
		// TODO Auto-generated constructor stub
		mview=view;
	}
	
	
	public void collectInfo(Long user_id,long cid){
		model=new collectInfoModel();
		model.getcollectInfo(user_id,cid, this);
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
