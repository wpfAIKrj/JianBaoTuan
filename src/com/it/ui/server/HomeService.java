package com.it.ui.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.it.config.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

public class HomeService extends Service {

	HttpUtils http = null;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		LogUtils.i("ytmfdw"+"HomeService onCreate!");
		http = new HttpUtils();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtils.i("ytmfdw"+ "HomeService onStart!");
		// TODO Auto-generated method stub
		http.send(HttpMethod.GET, UrlUtil.getHomePageURL(),
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						LogUtils.i("ytmfdw"+"http onstart");

					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						LogUtils.i("ytmfdw"+"http onLoading");
						// TODO Auto-generated method stub
						super.onLoading(total, current, isUploading);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						LogUtils.i("ytmfdw"+"http onSuccess"
								+ responseInfo.result);

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						LogUtils.e("ytmfdw"+"http onFailure" + msg);
						// TODO Auto-generated method stub

					}
				});
		return super.onStartCommand(intent, flags, startId);
	}
}
