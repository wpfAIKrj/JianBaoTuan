package com.yingluo.Appraiser.model;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.presenter.UploadLogoPresenter;

public class UpLoadLogoModel extends BaseModel {
	
	
	private String portrait;
	private String qq;
	private String email;
	private String name;
	private OnBasicDataLoadListener<UserInfo> listener;

	public void startUpload(String key, String name, String qq, String email, OnBasicDataLoadListener<UserInfo> listener) {
		// TODO Auto-generated method stub
		this.portrait=key;
		this.qq=qq;
		this.email=email;
		this.name=name;
		this.listener=listener;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.EXTRAUSR);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}
		url=sb.toString();
		addRequestParams();
		sendHttp();
	}
	
	@Override
	public void addRequestParams() {
		params=new RequestParams();
		params.addBodyParameter(NetConst.EXTRA_LOGO, portrait);
		params.addBodyParameter(NetConst.EXTRA_NAME, name);
		params.addBodyParameter(NetConst.EXTRA_QQ, qq);
		params.addBodyParameter(NetConst.EXTRA_EMAIL, email);
	}

	@Override
	public void onFailureForString(String error, String msg) {
		listener.onBaseDataLoadErrorHappened(error, msg);
	}

	@Override
	public void analyzeData(String data) {
		Gson gson=new Gson();
		UserInfo user=gson.fromJson(data, UserInfo.class);
		listener.onBaseDataLoaded(user);
	}

	

	

}
