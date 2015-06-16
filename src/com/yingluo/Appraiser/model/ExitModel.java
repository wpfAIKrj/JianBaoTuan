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
 * 发送意见
 * @author Administrator
 *
 */
public class ExitModel extends BaseModel{
	

	private OnStringDataLoadListener lisntenr;
	
	
	/**
	 * 发送意见
	 * @param cid 意见内容
	 * @param lis 回调
	 */
	public void sendExit(OnStringDataLoadListener lis){
		this.lisntenr=lis;
	
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.SENDEXIT);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url=sb.toString();
		setHTTPMODE(HttpMethod.POST);
		sendHttp();
	}
	
	
	
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
	}
	
	

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lisntenr.onBaseDataLoadErrorHappened(error, msg);
	}




	@Override
	public void setHTTPMODE(HttpMethod httpmodel) {
		// TODO Auto-generated method stub
		this.httpmodel=httpmodel;
	}




	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		lisntenr.onBaseDataLoaded(data);
	}



	
	
}
