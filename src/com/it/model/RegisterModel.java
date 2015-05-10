package com.it.model;

import org.json.JSONObject;

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
	
	private UserInfo user;
	private OnBasicDataLoadListener<UserInfo> lisntenr;
	
	public void setUserInfo(String name,String pwd,OnBasicDataLoadListener<UserInfo> lis){
		user=new UserInfo();
		user.setNickname(name);
		user.setPassword(pwd);
		this.lisntenr=lis;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.REGISTER_URL);
		url=sb.toString();
	}
	
	
	
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter(NetConst.LOGIN_NAME, user.getNickname());
		params.addBodyParameter(NetConst.LOGIN_PWD, user.getPassword());
	}
	
	

	@Override
	public void onSuccessForString(String jsonstring) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsob=new JSONObject(jsonstring);			
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
