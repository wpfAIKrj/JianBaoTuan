package com.yingluo.Appraiser.model;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;

/**
 * 注册之后的设置昵称和头像
 * 
 * @author Administrator
 *
 */
public class SetNameModel extends BaseModel {

	private String name;
	private String pwd;

	private OnBasicDataLoadListener<UserInfo> lisntenr;

	public void setUserInfo(String name, String pwd, OnBasicDataLoadListener<UserInfo> lis) {
		this.name = name;
		this.pwd = pwd;
		this.lisntenr = lis;
		StringBuffer sb = new StringBuffer(url);
		sb.append(NetConst.REGISTER_URL);
		url = sb.toString();
	}

	@Override
	public void addRequestParams() {
		params = new RequestParams();
		params.addBodyParameter(NetConst.LOGIN_NAME, name);
		params.addBodyParameter(NetConst.LOGIN_PWD, pwd);
	}

	@Override
	public void onFailureForString(String error, String msg) {
		lisntenr.onBaseDataLoadErrorHappened(error, msg);
	}

	@Override
	public void setHTTPMODE(HttpMethod httpmodel) {
		this.httpmodel = httpmodel;
	}

	@Override
	public void analyzeData(String data) throws Exception {
		Gson gson = new Gson();
		UserInfo user = gson.fromJson(data, UserInfo.class);
		NetConst.SESSIONID = user.getSession_id();
		NetConst.UPTOKEN = user.getImage_token();
		NetConst.IMTOKEN = user.getMessage_token();
		RongImUtils.getInstance().connect(NetConst.IMTOKEN);
		lisntenr.onBaseDataLoaded(user);
	}

}
