package com.yingluo.Appraiser.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.CollectionEntity;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.utils.FileUtils;

public class HomeModel extends BaseModel {

	private HomeEntity homeEntity = null;

	public HomeModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getHomePageURL();
	}
	
	
	public void sendHttp(final CommonCallBack callBack){
		final HttpUtils httpUtils=new HttpUtils(connTimeout);
		httpUtils.send(httpmodel, url, params,new RequestCallBack<String>(){

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				onSuccessForString(responseInfo.result);
				callBack.onSuccess();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				onFailureForString(error.getMessage(), msg);
				callBack.onError();
			}
			
		});
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		FileUtils.getInstance().saveFileForJson(data);
		homeEntity = new HomeEntity();
		JSONObject json = new JSONObject(data);
		try {
			if (json != null) {
				Gson gson = new Gson();
				String advertising = json.getString("advertising");
				List<CollectionEntity> list_advertising = gson.fromJson(
						advertising, new TypeToken<List<CollectionEntity>>() {
						}.getType());
				homeEntity.setAdvertising(list_advertising);
				String choices = json.getString("choices");
				List<CollectionEntity> list_choices = gson.fromJson(choices,
						new TypeToken<List<CollectionEntity>>() {
						}.getType());
				homeEntity.setChoices(list_choices);
				
				String hots = json.getString("hots");
				List<CollectionEntity> list_hots = gson.fromJson(hots,
						new TypeToken<List<CollectionEntity>>() {
						}.getType());
				homeEntity.setHots(list_hots);
				String articles = json.getString("articles");
				List<CollectionEntity> list_articles = gson.fromJson(articles,
						new TypeToken<List<CollectionEntity>>() {
						}.getType());
				homeEntity.setArticles(list_articles);
				String authors = json.getString("authors");
				List<CollectionEntity> list_authors = gson.fromJson(authors,
						new TypeToken<List<CollectionEntity>>() {
						}.getType());
				homeEntity.setAuthors(list_authors);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub

	}

	public HomeEntity getResult() {
		return homeEntity;
	}

}
