package com.yingluo.Appraiser.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.ImUserInfo;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;

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
		FileUtils.getInstance().saveFileForHomeJson(data);
		homeEntity = new HomeEntity();
		JSONObject json = new JSONObject(data);
		try {
			if (json != null) {
				Gson gson = new Gson();
				String advertising = json.getString("advertising");
				List<CollectionTreasure> list_advertising = gson.fromJson(
						advertising, new TypeToken<List<CollectionTreasure>>() {
						}.getType());
				homeEntity.setAdvertising(list_advertising);
				String choices = json.getString("choices");
				List<CollectionTreasure> list_choices = gson.fromJson(choices,
						new TypeToken<List<CollectionTreasure>>() {
						}.getType());
				homeEntity.setChoices(list_choices);
				for (CollectionTreasure collectionTreasure : list_choices) {
					ImUserInfo user=new ImUserInfo();
					user.setId(collectionTreasure.user_id);
					user.setName(collectionTreasure.authName);
					user.setIcon(collectionTreasure.authImage);
					SqlDataUtil.getInstance().saveIMUserinfo(user);
				}
				String hots = json.getString("hots");
				List<CollectionTreasure> list_hots = gson.fromJson(hots,
						new TypeToken<List<CollectionTreasure>>() {
						}.getType());
				homeEntity.setHots(list_hots);
				
				for (CollectionTreasure collectionTreasure : list_hots) {
					ImUserInfo user=new ImUserInfo();
					user.setId(collectionTreasure.user_id);
					user.setName(collectionTreasure.authName);
					user.setIcon(collectionTreasure.authImage);
					SqlDataUtil.getInstance().saveIMUserinfo(user);
				}
				String articles = json.getString("articles");
//				List<ContentInfo> list_articles = gson.fromJson(articles,
//						new TypeToken<List<ContentInfo>>() {
//						}.getType());
				List<ContentInfo> list_articles=new ArrayList<ContentInfo>();
				JSONArray array=new JSONArray(articles);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj=array.getJSONObject(i);
					ContentInfo info=new ContentInfo();
					info.setId(obj.getLong("article_id"));
					info.setTitle(obj.getString("msg"));
					info.setView_times(obj.getInt("viewTimes"));
					info.setImage(obj.getString("image"));
					list_articles.add(info);
				}
				homeEntity.setArticles(list_articles);
				String authors = json.getString("authors");
				List<CollectionTreasure> list_authors = new ArrayList<CollectionTreasure>();
				array=new JSONArray(authors);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj=array.getJSONObject(i);
					CollectionTreasure user=new CollectionTreasure();
					user.authName=obj.getString("authName");
					user.authImage=obj.getString("authImage");
					user.user_id=obj.getLong("author_id");
					user.goodAt=obj.getString("goodAt");
					user.company=obj.getString("company");
					list_authors.add(user);
					
					ImUserInfo imuser=new ImUserInfo();
					imuser.setId(user.user_id);
					imuser.setName(user.authName);
					imuser.setIcon(user.authImage);
					SqlDataUtil.getInstance().saveIMUserinfo(imuser);
				}
				homeEntity.setAuthors(list_authors);

			}
		} catch (Exception e) {
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
