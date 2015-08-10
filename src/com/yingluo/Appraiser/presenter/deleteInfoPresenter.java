package com.yingluo.Appraiser.presenter;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.collectInfoModel;
import com.yingluo.Appraiser.model.deleteInfoModel;
/**
 * 删除文章
 * @author Administrator
 *
 */
public class deleteInfoPresenter implements OnStringDataLoadListener{
	
	private onBasicView<String> mview;

	private deleteInfoModel model;
	
	public deleteInfoPresenter(onBasicView<String> view) {
		// TODO Auto-generated constructor stub
		mview=view;
	}
	
	
	public void deleteInfo(Long uid,String cids){
		model=new deleteInfoModel();
		model.deletetInfo(uid,cids, this);
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
