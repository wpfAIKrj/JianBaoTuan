package com.yingluo.Appraiser.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;

/**
 * @author ytmfdw 发表对宝贝的鉴定
 *
 */
public class sendTreasureIdentityModel extends BaseModel {

	private OnStringDataLoadListener listener=null;
	
	private String comment;

	private long tid;

	private long group_id;

	private String group_name;
	public sendTreasureIdentityModel(OnStringDataLoadListener listener) {
		// TODO Auto-generated constructor stub
		this.listener=listener;
		url = UrlUtil.sendTreasureIdentityURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}
	
	public void sendTreasureIdentity(long tid,long group_id,String group_name,String comment){
		this.tid=tid;
		this.group_id=group_id;
		this.group_name=group_name;
		this.comment=comment;
		setHTTPMODE(HttpMethod.POST);
		addRequestParams();
		sendHttp();
	}
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter("tid", String.valueOf(tid));
		params.addBodyParameter("group_id", String.valueOf(group_id));
		params.addBodyParameter("group_name", group_name);                                                                                                             
		params.addBodyParameter("content", comment);
		
	}
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		try {
			Gson gson = new Gson();
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get treasure by id :" + data);
	
			listener.onBaseDataLoaded("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listener.onBaseDataLoadErrorHappened("-1", "json error");
		}
	}


	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		listener.onBaseDataLoadErrorHappened(error, msg);
	}



}
