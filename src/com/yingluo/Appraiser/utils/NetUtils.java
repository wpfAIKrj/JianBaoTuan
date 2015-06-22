package com.yingluo.Appraiser.utils;

import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
/**
 * 网络工具类
 * @author Administrator
 *
 */
public class NetUtils {
	
	private static  NetUtils mInstance=null;
	private Context mContext;
	private NetUtils(Context context) {
		// TODO Auto-generated constructor stub
		mContext=context;
	}
	
	public static NetUtils getInstance(){
		return mInstance;
	}
	
	
	
	public static void init(Context context){
		mInstance=new NetUtils(context);
	}
	
	
	 /** 
     * 判断网络是否连接 
     *  
     * @param context 
     * @return 
     */  
    public  boolean isConnected()  
    {  
  
        ConnectivityManager connectivity = (ConnectivityManager) mContext  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
  
        if (null != connectivity)  
        {  
  
            NetworkInfo info = connectivity.getActiveNetworkInfo();  
            if (null != info && info.isConnected())  
            {  
                if (info.getState() == NetworkInfo.State.CONNECTED)  
                {  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 判断是否是wifi连接 
     */  
    public  boolean isWifi()  
    {  
        ConnectivityManager cm = (ConnectivityManager) mContext  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
  
        if (cm == null)  
            return false;  
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;  
  
    }  
  
    /** 
     * 打开网络设置界面 
     */  
    public  void openSetting(Activity activity)  
    {  
        Intent intent = new Intent("/");  
        ComponentName cm = new ComponentName("com.android.settings",  
                "com.android.settings.WirelessSettings");  
        intent.setComponent(cm);  
        intent.setAction("android.intent.action.VIEW");  
        activity.startActivityForResult(intent, 0);  
    }  
	
	
}