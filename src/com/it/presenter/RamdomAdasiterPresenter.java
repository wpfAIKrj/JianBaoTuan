package com.it.presenter;

import java.util.ArrayList;

import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.model.getRandomInfoModel;
import com.it.view.inter.onListView;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * 获取随机3个鉴定师
 * @author xy418
 *
 */
public class RamdomAdasiterPresenter implements OnListDataLoadListener<UserInfo> {
	
	private getRandomInfoModel model;
	private onListView<UserInfo> mView;
	public RamdomAdasiterPresenter(onListView<UserInfo> view) {
		// TODO Auto-generated constructor stub
		this.mView=view;
	}
	
	public void startGet(){
		this.model=new getRandomInfoModel();
		model.startGet(this);
	}
	
	@Override
	public void onListDataLoaded(ArrayList<UserInfo> data) {
		// TODO Auto-generated method stub
		mView.onSucess(data);
	}

	@Override
	public void onListDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mView.onFail(errorCode, errorMsg);
	}



}
