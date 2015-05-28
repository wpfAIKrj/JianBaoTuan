package com.it.model;

import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.app.ItApplication;
import com.it.bean.CollectionEntity;
import com.it.config.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

public class IdentifyModel extends BaseModel {

	private List<CollectionEntity> list = null;

	public IdentifyModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getIdentifyPageURL();
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		// JSONObject json = new JSONObject(data);
		try {
			Gson gson = new Gson();
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get identify data:" + data);
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

	/**
	 * @param callBack
	 *            回调函数
	 * @param type
	 *            请求参数，1:已鉴定，2:未鉴定
	 * @param groupid
	 *            分组id
	 * */
	public void sendHttp(final CommonCallBack callBack, String type, int groupid) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		params = new RequestParams();
		 params.addBodyParameter("status",type);
		 params.addBodyParameter("group_id",String.valueOf(groupid));
		/*JSONObject json = new JSONObject();
		try {
			json.put("status", type);
			json.put("group_id", groupid);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.addBodyParameter("json", json.toString());*/
		httpUtils.send(httpmodel, url, new RequestCallBack<String>() {

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

	public List<CollectionEntity> getResult() {
		return list;
	}

}
