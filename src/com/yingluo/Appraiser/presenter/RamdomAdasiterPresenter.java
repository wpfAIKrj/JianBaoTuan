package com.yingluo.Appraiser.presenter;

import java.util.ArrayList;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.model.getRandomInfoModel;
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
	
	public void startGet(Long id){
		this.model=new getRandomInfoModel();
		model.startGet(id,this);
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
