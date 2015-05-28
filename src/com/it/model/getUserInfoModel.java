package com.it.model;

import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.presenter.OnBasicDataLoadListener;
import com.it.presenter.OnStringDataLoadListener;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class getUserInfoModel extends BaseModel{
	
	private OnStringDataLoadListener listener;
	
	public getUserInfoModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public  void getUserinfo(OnStringDataLoadListener lis) {
		listener=lis;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.MINEACTION);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}
		url=sb.toString();
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		listener.onBaseDataLoaded(data);
	}
	

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		listener.onBaseDataLoadErrorHappened(error, msg);
	}

}