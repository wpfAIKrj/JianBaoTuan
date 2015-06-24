package com.yingluo.Appraiser.presenter;

import java.util.ArrayList;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.SystemInfoEntity;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.model.GetSystemInfoModel;
import com.yingluo.Appraiser.model.getCollectArticleModel;

public class SystemNoticePresenter  implements OnListDataLoadListener<SystemInfoEntity> {
	
	
	private onListView<SystemInfoEntity> mView;
	private GetSystemInfoModel model;
	public SystemNoticePresenter(onListView<SystemInfoEntity> view) {
		// TODO Auto-generated constructor stub
		this.mView=view;
	}
	
	public void getArticleList(String type,int group_size){
		model=new GetSystemInfoModel();
		model.getSystemInfoList(this);
	}
	@Override
	public void onListDataLoaded(ArrayList<SystemInfoEntity> data) {
		// TODO Auto-generated method stub
		mView.onSucess(data);
	}

	@Override
	public void onListDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mView.onFail(errorCode, errorMsg);
	}
}
