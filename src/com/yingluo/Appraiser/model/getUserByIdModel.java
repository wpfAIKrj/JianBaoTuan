package com.yingluo.Appraiser.model;

import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.inter.onBasicView;

/**
 * @author ytmfdw 根据用户id获取用户详情
 *
 */
public class getUserByIdModel extends BaseModel {

	UserInfo user = null;
	private onBasicView<UserInfo> lis;
	private long id;
	
	public getUserByIdModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
	}
	
	public void getUserInfoForId(long id,onBasicView<UserInfo> listener) {
		this.lis=listener;
		this.id=id;
		url = UrlUtil.getUserById();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}

		sb.append("&user_id=").append(id);
		url=sb.toString();
		sendHttp();
	}


	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject(data);
		if (json != null) {
			user = new UserInfo();
			user.setId(json.getLong("user_id"));
			user.setNickname(json.getString("nickname"));
			user.setUser_level(json.getInt("authLevel"));
			user.setUser_type(json.getInt("authType"));
			user.setAvatar(json.getString("authImage"));
			user.setQq(json.getString("qq"));
			user.setEmail(json.getString("email"));
			user.setIs_system(json.getInt("isSystem"));
			user.setDescription(json.getString("description"));
		}
		lis.onSucess(user);

	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lis.onFail(error, msg);
	}

	public UserInfo getResult() {
		return user;
	}
}
