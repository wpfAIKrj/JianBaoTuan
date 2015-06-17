package com.yingluo.Appraiser.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
import com.yingluo.Appraiser.presenter.collectInfoPresenter;

public class IdentifyModel extends BaseModel {

	private List<CollectionTreasure> list = null;
	private long kind_id;
	private OnListDataLoadListener<CollectionTreasure> lis;
	private int type;
	
	
	public IdentifyModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getIdentifyPageURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	public void getIdentity(long kind_id, int type,
			OnListDataLoadListener<CollectionTreasure> lis) {
		// TODO Auto-generated method stub
		this.kind_id=kind_id;
		this.type=type;
		this.lis=lis;
		addRequestParams();
		sendHttp();
	}
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		// JSONObject json = new JSONObject(data);
		try {
			Gson gson = new Gson();
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get identify data:" + data);
			list = gson.fromJson(data, new TypeToken<List<CollectionTreasure>>() {
			}.getType());
			if(lis!=null){
				lis.onListDataLoaded((ArrayList<CollectionTreasure>) list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(lis!=null){
				lis.onListDataLoadErrorHappened("-1", "Json error");
			}
		}

	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(url);
		sb.append("&status=").append(type);
		sb.append("&group_id=").append(kind_id);
		url=sb.toString();
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		if(error.equals(String.valueOf(NetConst.CODE_ERROR16))){
			if(lis!=null){
				lis.onListDataLoaded(new ArrayList<CollectionTreasure>());
			}
			return;
		}
		if(lis!=null){
			lis.onListDataLoadErrorHappened(error, msg);
		}
	}

	/**
	 * @param callBack
	 *            回调函数
	 * @param type
	 *            请求参数，1:已鉴定，2:未鉴定
	 * @param groupid
	 *            分组id
	 * */
	public void sendHttp(final CommonCallBack callBack, int type,
			long groupid) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		StringBuffer sb = new StringBuffer(url);
		sb.append("&status=").append(type);
		sb.append("&group_id=").append(groupid);
		url=sb.toString();
		LogUtils.d("ytmfdw  url=" + url);
		/*
		 * JSONObject json = new JSONObject(); try { json.put("status", type);
		 * json.put("group_id", groupid);
		 * 
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } params.addBodyParameter("json",
		 * json.toString());
		 */
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
	/**
	 * @param callBack
	 *            回调函数
	 * @param type
	 *            请求参数，1:已鉴定，2:未鉴定
	 * */
	public void sendHttp(final CommonCallBack callBack, int type) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		StringBuffer sb = new StringBuffer(url);
		sb.append("&status=").append(type);
		url=sb.toString();
		LogUtils.d("ytmfdw  url=" + url);
		/*
		 * JSONObject json = new JSONObject(); try { json.put("status", type);
		 * json.put("group_id", groupid);
		 * 
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } params.addBodyParameter("json",
		 * json.toString());
		 */
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

	public List<CollectionTreasure> getResult() {
		if(list==null){
			list=new ArrayList<CollectionTreasure>();
		}
		return list;
	}

	

}
