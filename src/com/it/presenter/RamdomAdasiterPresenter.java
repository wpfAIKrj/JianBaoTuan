package com.it.presenter;

import java.util.ArrayList;

import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.model.GetRandomInfoModel;
import com.it.view.inter.onListView;
/**
 * 获取随机3个鉴定师
 * @author xy418
 *
 */
public class RamdomAdasiterPresenter implements OnListDataLoadListener<UserInfo> {
	
	private GetRandomInfoModel model;
	private onListView<UserInfo> mView;
	public RamdomAdasiterPresenter(onListView<UserInfo> view) {
		// TODO Auto-generated constructor stub
		this.model=new GetRandomInfoModel();
		this.mView=view;
	}
	
	public void startGet(){
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
