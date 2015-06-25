package com.yingluo.Appraiser.model;

import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.utils.NetUtils;

public abstract class BaseModel {
	protected String url="http://123.57.251.101/";
	protected int connTimeout=50000;
	protected RequestParams params;
	protected HttpMethod httpmodel;
	
	
	//json解析异常跑出为-2
	public static final String HTTP_ERROR="-2";
	public static final String HTTP_NOTNET="-3";
	public BaseModel() {
		// TODO Auto-generated constructor stub
		httpmodel=HttpMethod.POST;
	}
	
	
	public  void sendHttp(){
		if(NetUtils.getInstance().isConnected()){
			final HttpUtils httpUtils=new HttpUtils(connTimeout);
			//设置当前请求的缓存时间
			httpUtils.configCurrentHttpCacheExpiry(0*1000);
			//设置默认请求的缓存时间
			httpUtils.configDefaultHttpCacheExpiry(0);
			httpUtils.send(httpmodel, url, params,new RequestCallBack<String>(){

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					// TODO Auto-generated method stub
					onSuccessForString(responseInfo.result);
					
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					// TODO Auto-generated method stub
					onFailureForString(error.getMessage(), msg);
				}
				
			});	
		}else{
			onFailureForString(HTTP_NOTNET, "请检查网络状态！");
		}
	}
	
    public  void onSuccessForString(String jsonstring){
    	try {
    		LogUtils.d(jsonstring);
			JSONObject json=new JSONObject(jsonstring);	
			int code=json.getInt(NetConst.CODE);
			String message=json.getString(NetConst.MESSAGE);
			if(code==NetConst.CODE_SUCCESS){
				String data=json.getString(NetConst.DATA);
				analyzeData(data);
				
			}else{
				if(code==NetConst.CODE_ERROR8){
					ItApplication.currnUser=null;
					NetConst.SESSIONID=null;
				}
				onFailureForString(String.valueOf(code),message);
			}
		} catch (Exception e) {
			// TODO: handle exception
			onFailureForString(HTTP_ERROR,e.getMessage());
		}
    }
	
    public abstract void analyzeData(String data) throws Exception;


	public 	abstract void addRequestParams();
	
	
	
    public abstract void onFailureForString(String error,String msg);
    
    public  void setHTTPMODE(HttpMethod httpmodel){
    	if(httpmodel==null){
    		return;
    	}
    	this.httpmodel=httpmodel;
    }
	
}
