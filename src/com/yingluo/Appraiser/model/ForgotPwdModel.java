package com.yingluo.Appraiser.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;

/**
 * 找回密码
 * 
 * @author Administrator
 *
 */
public class ForgotPwdModel extends BaseModel {

	private OnBasicDataLoadListener<UserInfo> lisntenr;

	private String mobile;

	private String password;

	public void forgetPwd(String mobile, String password, OnBasicDataLoadListener<UserInfo> lis) {
		this.lisntenr = lis;
		this.mobile = mobile;
		this.password = password;
		StringBuffer sb = new StringBuffer(url);
		sb.append(NetConst.FORGETPWD);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params = new RequestParams();
		params.addBodyParameter("mobile", mobile);
		params.addBodyParameter("password", password);
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lisntenr.onBaseDataLoadErrorHappened(error, msg);
	}

	@Override
	public void setHTTPMODE(HttpMethod httpmodel) {
		// TODO Auto-generated method stub
		this.httpmodel = httpmodel;
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		UserInfo user = gson.fromJson(data, UserInfo.class);
		NetConst.SESSIONID = user.getSession_id();
		NetConst.UPTOKEN = user.getImage_token();
		NetConst.IMTOKEN = user.getMessage_token();
		lisntenr.onBaseDataLoaded(user);
	}

}
