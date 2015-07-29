package com.yingluo.Appraiser.model;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;

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
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID).
			append("&user_id=").append(SharedPreferencesUtils.getInstance().getLoginUserID());
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
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
