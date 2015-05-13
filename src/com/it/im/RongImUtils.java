package com.it.im;

import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.it.config.NetConst;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpCache;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

import android.content.Context;

public class RongImUtils {

	public static final String IMURL="https://api.cn.rong.io";
	public static final String GETTOKEN="/user/getToken";
	public static final String FORMAT=".json";
	
	
	public static final String USERID="userId";
	public static final String NAME="name";
	public static final String ICON="portraitUri";
	public static final String TOKEN="token";
	private Context mContext;
	private static RongImUtils mInstance=null;
	
	public static String user_id=null;
	public static String token=null;
	
	public boolean getToken=false;
	public static void init(Context context) {
		// TODO Auto-generated method stub
		mInstance=new RongImUtils(context);
	}
	
	private RongImUtils(Context context) {
		// TODO Auto-generated constructor stub
		mContext=context;
		mInstance=this;
	}

	public static RongImUtils getInstance(){
		return mInstance;
	}
	
	/**
	 * 获取聊天的taken
	 * @param userid 用户标志符
	 * @param name 用户昵称
	 * @param photourl 用户头像
	 */
	public void getToken(String userid,String name,String photourl){
		HttpUtils httpUtils=new HttpUtils(10000);
		String url=IMURL+GETTOKEN+FORMAT;
		RequestParams params=new RequestParams(HTTP.UTF_8);
		params.addBodyParameter(USERID, userid);
		params.addBodyParameter(NAME, name);
		params.addBodyParameter(ICON, photourl);
		params.addHeader("Host", "api.cn.rong.io");
		params.addHeader("App-Key", NetConst.IM_KEY);
		params.addHeader("Nonce", "1316580241");
		params.addHeader("Timestamp", "1431519478");
		params.addHeader("Signature", "2f51ed72e9faa7c1c76a66aacc1e978952607501");
		params.addHeader("Content-Type", "application/x-www-form-urlencoded");

		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				try {
					JSONObject json=new JSONObject(responseInfo.result);
					int code=json.getInt(NetConst.CODE);
					if(code==200){
						user_id=json.getString(USERID);
						token=json.getString(TOKEN);
						LogUtils.d("用户id："+user_id+",token:"+token);
						getToken=true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					getToken=false;
				}
				
				
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				LogUtils.d("获取token失败"+",失败原因："+msg);
				getToken=false;
			}
		});
	}
	
	
	
	
}
