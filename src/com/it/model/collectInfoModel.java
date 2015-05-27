package com.it.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.it.bean.ContentInfo;
import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.presenter.OnBasicDataLoadListener;
import com.it.presenter.OnListDataLoadListener;
import com.it.presenter.OnStringDataLoadListener;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * 获取3个随机鉴定师
 * @author Administrator
 *
 */
public class collectInfoModel extends BaseModel{
	

	private OnStringDataLoadListener lisntenr;
	
	private long cid;
	
	/**
	 * 收藏文章
	 * @param cid 文章id
	 * @param lis 回调
	 */
	public void getcollectInfo(long cid,OnStringDataLoadListener lis){
		this.lisntenr=lis;
		this.cid=cid;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.COLLECTINFO);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}
		url=sb.toString();
	}
	
	
	
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter("cid", String.valueOf(cid));
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
