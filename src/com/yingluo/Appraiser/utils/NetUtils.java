package com.yingluo.Appraiser.utils;

import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetUtils {
	

	public static boolean checkNetWork(final Activity context){
		ConnectivityManager manager = (ConnectivityManager) 
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWorkInfo = manager.getActiveNetworkInfo();
		if(netWorkInfo == null ){
			LogUtils.d("info"+"没有网络连接");
			AlertDialog.Builder builder = new Builder(context)
			.setTitle("没有网络！")
			.setMessage("dakaiw")
			.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
					context.startActivityForResult(intent, 0x11);
				}
			}).setNegativeButton("取消", new OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			});
			builder.show();
			return false;
		}else{
			LogUtils.d("info"+"有网络连接！");
			return true;
		}
	}
	
	
}