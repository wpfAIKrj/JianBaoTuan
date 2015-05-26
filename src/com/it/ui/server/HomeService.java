package com.it.ui.server;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.app.ItApplication;
import com.it.bean.CollectionEntity;
import com.it.bean.HomeEntity;
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
		LogUtils.i("ytmfdw" + "HomeService onCreate!");
		http = new HttpUtils();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtils.i("ytmfdw" + "HomeService onStart!");
		// TODO Auto-generated method stub
		http.send(HttpMethod.GET, UrlUtil.getHomePageURL(),
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						LogUtils.i("ytmfdw" + "http onstart");

					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						LogUtils.i("ytmfdw" + "http onLoading");
						// TODO Auto-generated method stub
						super.onLoading(total, current, isUploading);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						LogUtils.i("ytmfdw" + "http onSuccess"
								+ responseInfo.result);
						HomeEntity homeEntity = new HomeEntity();
						try {
							JSONObject json = new JSONObject(
									responseInfo.result);
							if (json != null) {
								Gson gson = new Gson();
								JSONObject json_data = json
										.getJSONObject("data");
								String advertising = json_data
										.getString("advertising");
								List<CollectionEntity> list_advertising = gson
										.fromJson(
												advertising,
												new TypeToken<List<CollectionEntity>>() {
												}.getType());
								homeEntity.setAdvertising(list_advertising);
								String choices = json_data.getString("choices");
								List<CollectionEntity> list_choices = gson
										.fromJson(
												choices,
												new TypeToken<List<CollectionEntity>>() {
												}.getType());
								homeEntity.setChoices(list_choices);
								String hots = json_data.getString("hots");
								List<CollectionEntity> list_hots = gson
										.fromJson(
												hots,
												new TypeToken<List<CollectionEntity>>() {
												}.getType());
								homeEntity.setHots(list_hots);
								String articles = json_data
										.getString("articles");
								List<CollectionEntity> list_articles = gson
										.fromJson(
												articles,
												new TypeToken<List<CollectionEntity>>() {
												}.getType());
								homeEntity.setArticles(list_articles);
								String authors = json_data.getString("authors");
								List<CollectionEntity> list_authors = gson
										.fromJson(
												authors,
												new TypeToken<List<CollectionEntity>>() {
												}.getType());
								homeEntity.setAuthors(list_authors);

								((ItApplication) getApplication())
										.setHomeEntity(homeEntity);

							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						LogUtils.e("ytmfdw" + "http onFailure" + msg);
						// TODO Auto-generated method stub

					}
				});
		return super.onStartCommand(intent, flags, startId);
	}
}
