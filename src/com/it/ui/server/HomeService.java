package com.it.ui.server;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.it.app.ItApplication;
import com.it.bean.ContentType;
import com.it.model.CommonCallBack;
import com.it.model.HomeModel;
import com.it.model.IdentifyModel;
import com.it.model.getAllKindsModel;
import com.it.presenter.OnListDataLoadListener;
import com.it.utils.SqlDataUtil;
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
		
		final getAllKindsModel allkinds=new getAllKindsModel(new OnListDataLoadListener<ContentType>() {
			
			@Override
			public void onListDataLoaded(ArrayList<ContentType> data) {
				// TODO Auto-generated method stub
				if(data!=null&&!data.isEmpty()){
					SqlDataUtil.getInstance().saveContentType(data);
				}
			}
			
			@Override
			public void onListDataLoadErrorHappened(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				
			}
		});
		allkinds.sendHttp();
		
		return super.onStartCommand(intent, flags, startId);
	}
}
