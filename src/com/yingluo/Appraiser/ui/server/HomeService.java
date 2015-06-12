package com.yingluo.Appraiser.ui.server;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.HomeModel;
import com.yingluo.Appraiser.model.IdentifyModel;
import com.yingluo.Appraiser.model.getAllKind_X_Model;
import com.yingluo.Appraiser.model.getAllKindsModel;
import com.yingluo.Appraiser.presenter.OnListDataLoadListener;
import com.yingluo.Appraiser.utils.SqlDataUtil;

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
		http = new HttpUtils();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
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
		}, 1);
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
		}, 2);

		/*
		 * final getAllKindsModel allkinds=new getAllKindsModel(new
		 * OnListDataLoadListener<TreasureType>() {
		 * 
		 * @Override public void onListDataLoaded(ArrayList<TreasureType> data)
		 * { // TODO Auto-generated method stub if(data!=null&&!data.isEmpty()){
		 * SqlDataUtil.getInstance().saveContentType(data); } }
		 * 
		 * @Override public void onListDataLoadErrorHappened(String errorCode,
		 * String errorMsg) { // TODO Auto-generated method stub
		 * 
		 * } }); allkinds.sendHttp();
		 */
		final getAllKind_X_Model allKinds = new getAllKind_X_Model();
		allKinds.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		});

		return super.onStartCommand(intent, flags, startId);
	}
}
