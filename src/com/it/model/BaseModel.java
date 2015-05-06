package com.it.model;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public abstract class BaseModel {
	private String url="www.baidu.com";
	private int connTimeout=10000;
	protected RequestParams params;
	private HttpMethod httpmodel;
	
	//json解析异常跑出为-2
	public static final String HTTP_ERROR="-2";
	
	public BaseModel() {
		// TODO Auto-generated constructor stub
		httpmodel=HttpMethod.POST;
	}
	
	
	public  void sendHttp(){
		HttpUtils httpUtils=new HttpUtils(connTimeout);
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
	}
	
	
    public 	abstract void addRequestParams();
	
    public abstract void onSuccessForString(String jsonstring);
	
	
    public abstract void onFailureForString(String error,String msg);
	
}
