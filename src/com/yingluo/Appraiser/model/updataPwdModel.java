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
import com.yingluo.Appraiser.presenter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.presenter.OnListDataLoadListener;
import com.yingluo.Appraiser.presenter.OnStringDataLoadListener;
/**
 * 更新文章
 * @author Administrator
 *
 */
public class updataPwdModel extends BaseModel{
	

	private OnBasicDataLoadListener<UserInfo> lisntenr;
	
	private String oldpwd;
	
	private String newpwd;
	
	
	
	/**
	 * 收藏文章
	 * @param cid 文章id
	 * @param lis 回调
	 */
	public void updatePwd(String oldpwd,String newpwd,OnBasicDataLoadListener<UserInfo> lis){
		this.lisntenr=lis;
		this.oldpwd=oldpwd;
		this.newpwd=newpwd;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.UPDATAPWD);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url=sb.toString();
		addRequestParams();
		setHTTPMODE(HttpMethod.POST);
		sendHttp();
	}
	
	
	
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter("password", newpwd);
		params.addBodyParameter("old_password", oldpwd);
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
		Gson gson=new Gson();
		UserInfo user=gson.fromJson(data, UserInfo.class);
		NetConst.SESSIONID=user.getSession_id();
		NetConst.UPTOKEN=user.getImage_token();
		NetConst.IMTOKEN=user.getMessage_token();
		lisntenr.onBaseDataLoaded(user);
	}



	
	
}
