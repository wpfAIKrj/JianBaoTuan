package com.yingluo.Appraiser.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.utils.NetUtils;

public class GetImageTokenModel extends BaseModel {
	

	private onBasicView<String> lis;

	public void GetIMageToken(onBasicView<String> lis){
		this.lis=lis;
		this.url=UrlUtil.getImageTokenUrl();
		httpmodel=HttpMethod.GET;
		sendHttp();
	}
	
	
	@Override
	public void onSuccessForString(String jsonstring) {
		// TODO Auto-generated method stub
		try {
			JSONObject obj=new JSONObject(jsonstring);
			String uptoken=obj.getString("uptoken");
			lis.onSucess(uptoken);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lis.onFail(HTTP_ERROR,e.getMessage());
		}
		
	}
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lis.onFail(error, msg);
	}

}
