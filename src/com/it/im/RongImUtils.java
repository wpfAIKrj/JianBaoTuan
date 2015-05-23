package com.it.im;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ConnectCallback.ErrorCode;

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


	
	
	
	
	private Context mContext;
	private static RongImUtils mInstance=null;
	
	private boolean isconnect=false;
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
	
//	/**
//	 * 获取聊天的taken
//	 * @param userid 用户标志符
//	 * @param name 用户昵称
//	 * @param photourl 用户头像
//	 */
//	public void getToken(String userid,String name,String photourl){
//		HttpUtils httpUtils=new HttpUtils(10000);
//		String url=IMURL+GETTOKEN+FORMAT;
//		RequestParams params=new RequestParams(HTTP.UTF_8);
//		params.addBodyParameter(USERID, userid);
//		params.addBodyParameter(NAME, name);
//		params.addBodyParameter(ICON, photourl);
//		params.addHeader("Host", "api.cn.rong.io");
//		params.addHeader("App-Key", NetConst.IM_KEY);
//		params.addHeader("Nonce", "1316580241");
//		params.addHeader("Timestamp", "1431519478");
//		params.addHeader("Signature", "2f51ed72e9faa7c1c76a66aacc1e978952607501");
//		params.addHeader("Content-Type", "application/x-www-form-urlencoded");
//
//		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
//
//			@Override
//			public void onSuccess(ResponseInfo<String> responseInfo) {
//				// TODO Auto-generated method stub
//				try {
//					JSONObject json=new JSONObject(responseInfo.result);
//					int code=json.getInt(NetConst.CODE);
//					if(code==200){
//						user_id=json.getString(USERID);
//						token=json.getString(TOKEN);
//						LogUtils.d("用户id："+user_id+",token:"+token);
//						getToken=true;
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					getToken=false;
//				}
//				
//				
//			}
//
//			@Override
//			public void onFailure(HttpException error, String msg) {
//				// TODO Auto-generated method stub
//				LogUtils.d("获取token失败"+",失败原因："+msg);
//				getToken=false;
//			}
//		});
//	}
//	
	
	/**
	 * 创建连接
	 */
	public void connect(){
		try {
			RongIM.connect(NetConst.IMTOKEN, new ConnectCallback() {
				
				@Override
				public void onSuccess(String arg0) {
					// TODO Auto-generated method stub
					isconnect=true;
					//连接成功后注册处理事件
					RongCloudEvent.getInstance().setOtherListener();
				}
				
				@Override
				public void onError(ErrorCode arg0) {
					// TODO Auto-generated method stub
					isconnect=false;
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动单独聊天页面
	 * @param context 
	 * @param targetUserId 用户id（手机号码）
	 * @param title  聊天标题
	 */
	public void startPrivateChat(Context context, String targetUserId, String title){
		if(isconnect){
			RongIM.getInstance().startPrivateChat(context, targetUserId, title);
		}
	}

	

	
	
	
	
	
}
