package com.it.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.bean.CollectionEntity;
import com.it.bean.HomeEntity;
import com.it.config.NetConst;
import com.it.config.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author ytmfdw 获取我的足迹
 *
 */
public class getMyFootPrintsModel extends BaseModel {

	private List<CollectionEntity> list = null;

	public getMyFootPrintsModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getMyFootPrintsURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	public void sendHttp(final CommonCallBack callBack) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
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
		try {
			Gson gson = new Gson();
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get my foot print:" + data);
			list = gson.fromJson(data, new TypeToken<List<CollectionEntity>>() {
			}.getType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public List<CollectionEntity> getResult() {
		return list;
	}

}
