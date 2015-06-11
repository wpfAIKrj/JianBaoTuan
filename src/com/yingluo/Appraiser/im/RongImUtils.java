package com.yingluo.Appraiser.im;


import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ErrorCode;
import io.rong.imlib.model.UserInfo;

import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpCache;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.utils.SqlDataUtil;

import android.content.Context;
import android.net.Uri;

public class RongImUtils {


	

	
	
	private Context mContext;
	private static RongImUtils mInstance=null;
	
	private boolean isconnect=false;
	public static void init(Context context) {
		// TODO Auto-generated method stub
		mInstance=new RongImUtils(context);
		RongIM.init(context);
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
	 * 创建连接
	 */
	public void connect(String token){
		RongIM.connect(token, new ConnectCallback() {
			
			@Override
			public void onSuccess(String arg0) {
				// TODO 连接成功
				LogUtils.d("聊天连接成功！");
				isconnect=true;
			    RongCloudEvent.getInstance().setOtherListener();
			    setUserInfo();
			}
			
			@Override
			public void onError(ErrorCode arg0) {
				// TODO 连接失败
				isconnect=false;
				LogUtils.d("聊天连接失败！");
			}
			
			@Override
			public void onTokenIncorrect() {
				// TODO 自动生成的方法存根
				LogUtils.d("触发改方法！");
			}
		});
		
	}
	/**
	 * 连接创建成功后，将用户的
	 */
	protected void setUserInfo() {
		// TODO Auto-generated method stub
	    //设置用户信息提供者。
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            //App 返回指定的用户信息给 IMKit 界面组件。

			@Override
			public UserInfo getUserInfo(String arg0) {
				// TODO 获取本地用户转化的
				return getUserInfoFromLocalCache(arg0);
			}
        }, false);
	}

	/**
	 * 获取当前登录用户的基本信息
	 * @param arg0
	 * @return
	 */
	protected UserInfo getUserInfoFromLocalCache(String arg0) {
		// TODO Auto-generated method stub
		UserInfo user =null;
		if(ItApplication.currnUser!=null){
		 try {
			 if(ItApplication.currnUser.getAvatar()!=null){
				 user=new UserInfo(arg0, ItApplication.currnUser.getNickname(), Uri.parse(ItApplication.currnUser.getAvatar())); 
			 }else{
				 user=new UserInfo(arg0, ItApplication.currnUser.getNickname(),null);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return user;
	}

	/**
	 * 启动单独聊天页面
	 * @param context 
	 * @param targetUserId 用户id（用户编号id）
	 * @param title  聊天对象名字
	 */
	public void startPrivateChat(Context context, String targetUserId, String title){
		if(isconnect){
			RongIM.getInstance().startPrivateChat(context, targetUserId, title);
		}
	}

	

	
	
	
	
	
}
