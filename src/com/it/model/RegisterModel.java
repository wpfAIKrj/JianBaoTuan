package com.it.model;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.presenter.OnBasicDataLoadListener;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * 注册操作
 * @author Administrator
 *
 */
public class RegisterModel extends BaseModel{
	
	private String name;
	private String pwd;
	private OnBasicDataLoadListener<UserInfo> lisntenr;
	
	public void setUserInfo(String name,String pwd,OnBasicDataLoadListener<UserInfo> lis){
		this.name=name;
		this.pwd=pwd;
		this.lisntenr=lis;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.REGISTER_URL);
		url=sb.toString();
	}
	
	
	
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter(NetConst.LOGIN_NAME, name);
		params.addBodyParameter(NetConst.LOGIN_PWD, pwd);
	}
	
	

	@Override
	public void onSuccessForString(String jsonstring) {
		// TODO Auto-generated method stub
		try {
			JSONObject json=new JSONObject(jsonstring);	
			int code=json.getInt(NetConst.CODE);
			String message=json.getString(NetConst.MESSAGE);
			if(code==NetConst.CODE_SUCCESS){
				String data = json.getString(NetConst.DATA);
				Gson gson=new Gson();
				UserInfo user=gson.fromJson(data, UserInfo.class);
				NetConst.SESSIONID=user.getSession_id();
				lisntenr.onBaseDataLoaded(user);
			}else{
				lisntenr.onBaseDataLoadErrorHappened(String.valueOf(code),message);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			lisntenr.onBaseDataLoadErrorHappened(HTTP_ERROR,e.getMessage());
		}
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





	
	
}
