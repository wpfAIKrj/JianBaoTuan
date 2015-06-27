package com.yingluo.Appraiser.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.SystemInfoEntity;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;
/**
 * 获取用户收藏文章列表
 * @author Administrator
 *
 */
public class GetSystemInfoModel extends BaseModel{
	

	private OnListDataLoadListener<SystemInfoEntity> lisntenr;
	
	public void getSystemInfoList(OnListDataLoadListener<SystemInfoEntity> lis){
		this.lisntenr=lis;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.SYSTEMNOTICE);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url=sb.toString();
		setHTTPMODE(HttpMethod.GET);
		sendHttp();
	}
	
	
	 
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

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
			ArrayList<SystemInfoEntity> infos = gson.fromJson(data, new TypeToken<ArrayList<SystemInfoEntity>>(){}.getType());
			if(infos==null){
				infos=new ArrayList<SystemInfoEntity>();
			}else{
				for (int i = 0; i < infos.size(); i++) {
					infos.get(i).mobile=ItApplication.currnUser.getMobile();
				}
			}
			lisntenr.onListDataLoaded(infos);
		}



	
	
}
