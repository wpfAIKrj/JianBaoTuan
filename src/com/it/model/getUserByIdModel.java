package com.it.model;

import org.json.JSONObject;

import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.config.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author ytmfdw 根据用户id获取用户详情
 *
 */
public class getUserByIdModel extends BaseModel {

	UserInfo user = null;

	public getUserByIdModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getUserById();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	public void sendHttp(final CommonCallBack callBack, long id) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		StringBuffer sb=new StringBuffer(url);
		sb.append("&user_id=").append(id);
		url=sb.toString();
		LogUtils.d("getuser by id:"+id);
		LogUtils.d("getuser by id: url="+url);
		httpUtils.send(httpmodel, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				onSuccessForString(responseInfo.result);
				callBack.onSuccess();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				onFailureForString(error.getMessage(), msg);
				callBack.onError();
			}

		});
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		// "data": {
		// "authName": "佚名",
		// "user_id": "1",
		// "authLevel": "0",
		// "authType": "0",
		// "authImage":
		// "http: //7xiz0t.com1.z0.glb.clouddn.com/6b18cb3a2dcfa980a3f4601f0b05ddb2.jpg",
		// "qq": "00",
		// "email": "99"
		// }
		user = new UserInfo();
		JSONObject json = new JSONObject(data);
		if (json != null) {
			user.setId(json.getLong("user_id"));
			user.setNickname(json.getString("authName"));
			user.setUser_level(json.getInt("authLevel"));
			user.setUser_type(json.getInt("authType"));
			user.setImage_token(json.getString("authImage"));
			user.setQq(json.getString("qq"));
			user.setEmail(json.getString("email"));
		}

	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub

	}

	public UserInfo getResult() {
		if(user==null){
			user=new UserInfo();
		}
		return user;
	}
}
