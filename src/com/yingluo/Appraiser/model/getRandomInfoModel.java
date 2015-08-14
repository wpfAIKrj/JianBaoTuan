package com.yingluo.Appraiser.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
/**
 * 获取3个随机鉴定师
 * @author Administrator
 *
 */
public class getRandomInfoModel extends BaseModel{
	

	private OnListDataLoadListener<UserInfo> lisntenr;
	
	private long id;
	public void startGet(long id,OnListDataLoadListener<UserInfo> lis){
		this.lisntenr=lis;
		this.id=id;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.RAMDOMAPPRAISER);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&classify_id=").append(id);
		url=sb.toString();
		setHTTPMODE(HttpMethod.GET);
		sendHttp();
	}
	
	@Override
	public void addRequestParams() {
	
	}
	
	@Override
	public void onFailureForString(String error, String msg) {
		lisntenr.onListDataLoadErrorHappened(error, msg);
	}

	@Override
	public void setHTTPMODE(HttpMethod httpmodel) {
		this.httpmodel=httpmodel;
	}

	@Override
	public void analyzeData(String data) throws Exception {
		Gson gson=new Gson();
		ArrayList<UserInfo> users=new ArrayList<UserInfo>();
		JSONArray array=new JSONArray(data);
		for (int i = 0; i < array.length(); i++) {
			UserInfo user=gson.fromJson(array.getString(i), UserInfo.class);
			users.add(user);	
		}
		lisntenr.onListDataLoaded(users);
	}
	
}
