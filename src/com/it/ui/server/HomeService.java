package com.it.ui.server;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.it.app.ItApplication;
import com.it.bean.TreasureType;
import com.it.model.CommonCallBack;
import com.it.model.HomeModel;
import com.it.model.IdentifyModel;
import com.it.model.getAllKind_X_Model;
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
				List<List<TreasureType>> list = allKinds.getResult();
				List<TreasureType> first = list.get(0);// 获取一级分类
				List<TreasureType> second = list.get(1);// 获取一级分类
				List<TreasureType> third = list.get(2);// 获取一级分类
				// 写入数据库
				SqlDataUtil.getInstance().saveContentType(first);
				SqlDataUtil.getInstance().saveContentType(second);
				SqlDataUtil.getInstance().saveContentType(third);
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		});

		return super.onStartCommand(intent, flags, startId);
	}
}
