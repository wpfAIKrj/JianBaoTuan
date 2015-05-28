package com.it.presenter;

import com.it.inter.onBasicView;
import com.it.model.collectInfoModel;
import com.it.model.deleteInfoModel;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
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
	
	
	public void deleteInfo(String cids){
		model=new deleteInfoModel();
		model.deletetInfo(cids, this);
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
