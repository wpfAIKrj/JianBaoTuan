package com.yingluo.Appraiser.ui.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.GetImageTokenModel;
import com.yingluo.Appraiser.model.HomeModel;
import com.yingluo.Appraiser.model.IdentifyModel;
import com.yingluo.Appraiser.model.getAllKind_X_Model;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;

public class HomeService extends Service {

	HttpUtils http = null;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		http = new HttpUtils();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("server is running", "onStartCommand");
		final HomeModel homeModel = new HomeModel();
		homeModel.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				((ItApplication) getApplication()).setHomeEntity(homeModel.getResult());
			}

			@Override
			public void onError() {
				String str = FileUtils.getInstance().getJsonStringForJson(FileUtils.JSON_HOME);
				if (str != null) {
					try {
						homeModel.analyzeData(str);
						((ItApplication) getApplication()).setHomeEntity(homeModel.getResult());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		final IdentifyModel identify = new IdentifyModel();
		identify.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				((ItApplication) getApplication()).setHasIdentify(identify.getResult());
			}

			@Override
			public void onError() {

			}
		}, 1);
		
		final IdentifyModel unIdentify = new IdentifyModel();
		unIdentify.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				((ItApplication) getApplication()).setUnIdentify(unIdentify.getResult());
			}

			@Override
			public void onError() {

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

			}

			@Override
			public void onError() {

			}
		});

		final GetImageTokenModel getImageToke = new GetImageTokenModel();
		getImageToke.GetIMageToken(new onBasicView<String>() {

			@Override
			public void onSucess(String data) {
				NetConst.UPTOKEN = data;
				UserInfo user = ItApplication.getcurrnUser();
				if (user != null) {
					user.setImage_token(data);
					SqlDataUtil.getInstance().saveUserInfo(user);
					ItApplication.setCurrnUser(user);
				}
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

			}
		});

		return super.onStartCommand(intent, flags, startId);
	}
}
