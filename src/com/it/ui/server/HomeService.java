package com.it.ui.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.it.app.ItApplication;
import com.it.model.CommonCallBack;
import com.it.model.HomeModel;
import com.it.model.IdentifyModel;
import com.lidroid.xutils.HttpUtils;
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
		LogUtils.i("ytmfdw" + "HomeService onCreate!");
		http = new HttpUtils();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtils.i("ytmfdw" + "HomeService onStart!");
		// TODO Auto-generated method stub
		final HomeModel homeModel = new HomeModel();
		homeModel.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				((ItApplication) getApplication()).setHomeEntity(homeModel
						.getResult());
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		});

		final IdentifyModel identify = new IdentifyModel();
		identify.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				((ItApplication) getApplication()).setHasIdentify(identify
						.getResult());
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		}, "1", 0);
		final IdentifyModel unIdentify = new IdentifyModel();
		unIdentify.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				((ItApplication) getApplication()).setUnIdentify(unIdentify
						.getResult());
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		}, "2", 0);
		return super.onStartCommand(intent, flags, startId);
	}
}
