package com.it.model;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.presenter.OnBasicDataLoadListener;
import com.it.presenter.UploadLogoPresenter;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class UpLoadLogoModel extends BaseModel {
	
	
	private String portrait;
	private String qq;
	private String email;
	private String name;
	private OnBasicDataLoadListener<UserInfo> listener;

	public void startUpload(String key, String qq, String email, String name, OnBasicDataLoadListener<UserInfo> listener) {
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
	}
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter(NetConst.EXTRA_LOGO, portrait);
		params.addBodyParameter(NetConst.EXTRA_NAME, name);
		params.addBodyParameter(NetConst.EXTRA_QQ, qq);
		params.addBodyParameter(NetConst.EXTRA_EMAIL, email);
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		listener.onBaseDataLoadErrorHappened(error, msg);
	}

	@Override
	public void analyzeData(String data) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		UserInfo user=gson.fromJson(data, UserInfo.class);
		listener.onBaseDataLoaded(user);
	}

	

	

}
