package com.it.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.presenter.OnBasicDataLoadListener;
import com.it.presenter.OnListDataLoadListener;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * 获取3个随机鉴定师
 * @author Administrator
 *
 */
public class GetRandomInfoModel extends BaseModel{
	

	private OnListDataLoadListener<UserInfo> lisntenr;
	
	public void startGet(OnListDataLoadListener<UserInfo> lis){
		this.lisntenr=lis;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.RAMDOMAPPRAISER);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}
		url=sb.toString();
	}
	
	
	
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
	}
	
	

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lisntenr.onListDataLoadErrorHappened(error, msg);
	}




	@Override
	public void setHTTPMODE(HttpMethod httpmodel) {
		// TODO Auto-generated method stub
		this.httpmodel=httpmodel;
	}




	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		ArrayList<UserInfo> users=new ArrayList<UserInfo>();
			JSONArray array=new JSONArray(data);
			for (int i = 0; i < array.length(); i++) {
				UserInfo user=gson.fromJson(array.getString(0), UserInfo.class);
				users.add(user);
			lisntenr.onListDataLoaded(users);
			}
	}



	
	
}
