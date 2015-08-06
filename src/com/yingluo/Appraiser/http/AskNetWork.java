package com.yingluo.Appraiser.http;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.config.UrlUtil;

/**
 * 
 * @author wpf_pc
 *
 *	网络请求的封装
 */
public class AskNetWork {

	private Map<String, Object> map;
	private String json,md5;
	private String param;
	private AskNetWorkCallBack askCallback;
	private RequestParams params;
	/**
	 * 自定义接口，用于回调网络请求到的数据
	 */
	public interface AskNetWorkCallBack
	{
		/**
		 * 获得的网络请求数据
		 * @param msg
		 * @param param
		 * @throws JSONException 
		 */
		public void getNetWorkMsg(String msg,String param) throws JSONException;
		public void getNetWorkMsgError(String msg,String param) throws JSONException;
	}
	 
	public AskNetWork(AskNetWorkCallBack askCallBack) {
		this.askCallback = askCallBack;
	}
	
	public AskNetWork(String param,AskNetWorkCallBack askCallBack) 
	{
		this.param = param;
        this.askCallback = askCallBack;
	}
	
	public AskNetWork(Map<String, Object> map,String param,AskNetWorkCallBack askCallBack) 
	{
		this.map = map;
        this.param = param;
        this.askCallback = askCallBack;
        this.params = new RequestParams();
        for (String key : map.keySet()) {
        	params.addBodyParameter(key, map.get(key).toString());
        }
	}
	
	public void setParam(String param) {
		this.param = param;
	}
	
	public void ask(Map<String, Object> paramMap) 
	{
		StringBuffer httpurl = new StringBuffer(UrlUtil.BASE_URL +"/"+ this.param+"?");
		if(paramMap!=null)
		{
			for (String key : paramMap.keySet()) {
				httpurl.append(key);
				httpurl.append("=");
				httpurl.append(paramMap.get(key));
				httpurl.append("&");
	        }
		}
		String url = httpurl.substring(0, httpurl.length()-1);
		HttpUtils http = new HttpUtils();
		http.configTimeout(5000);
	    http.send(HttpRequest.HttpMethod.POST, url, this.params,new RequestCallBack<String>() {
	
	        @Override
	        public void onSuccess(ResponseInfo<String> responseInfo) {
	        	try {
					getResult(responseInfo.result,1,param);
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	
	        @Override
	        public void onFailure(HttpException error, String msg) {
	        	try {
					getResult(error.toString(),0,param);
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	    });
	}
	
	public void ask(HttpMethod msd,Map<String, Object> paramMap) 
	{
		StringBuffer httpurl = new StringBuffer(UrlUtil.BASE_URL +"/"+ this.param+"?");
		if(paramMap!=null)
		{
			for (String key : paramMap.keySet()) {
				httpurl.append(key);
				httpurl.append("=");
				httpurl.append(paramMap.get(key));
				httpurl.append("&");
	        }
		}
		String url = httpurl.substring(0, httpurl.length()-1);
		
		HttpUtils http = new HttpUtils();
		http.configTimeout(5000);
	    http.send(msd,url,new RequestCallBack<String>() {
	
	        @Override
	        public void onSuccess(ResponseInfo<String> responseInfo) {
	        	try {
					getResult(responseInfo.result,1,param);
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	
	        @Override
	        public void onFailure(HttpException error, String msg) {
	        	try {
					getResult(error.toString(),0,param);
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	    });
	}
	
	public void getResult(final String str,int res,String param) throws JSONException
	{
		if(res == 0)
		{
			this.askCallback.getNetWorkMsgError("ERROR",param);
		}
		else 
		{
			this.askCallback.getNetWorkMsg(str,param);
		}
		
	}
	
}
