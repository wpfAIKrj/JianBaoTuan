package com.yingluo.Appraiser.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.OnListDataLoadListener;

/**
 * 加载文章
 * 
 * @author Administrator
 *
 */
public class ArticleModel extends BaseModel {

	private OnListDataLoadListener<ContentInfo> lisntenr;

	private String type;
	private String group_id;

	public void getArticleList(String type, String group_id, OnListDataLoadListener<ContentInfo> lis) {
		this.lisntenr = lis;
		this.type = type;
		this.group_id = group_id;
		StringBuffer sb = new StringBuffer(url);
		sb.append(NetConst.LIST_INFO);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&type=").append(type).append("&group_id=").append(String.valueOf(group_id));
		url = sb.toString();
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lisntenr.onListDataLoadErrorHappened(error, msg);
	}

	@Override
	public void setHTTPMODE(HttpMethod httpmodel) {
		// TODO Auto-generated method stub
		this.httpmodel = httpmodel;
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		// JSONObject jason=new JSONObject(data);
		// String lists=jason.getString(NetConst.LISTSDATA);
		if (data.equals("null")) {
			lisntenr.onListDataLoaded(new ArrayList<ContentInfo>());
		} else {
			Gson gson = new Gson();
			ArrayList<ContentInfo> infos = new ArrayList<ContentInfo>();
			JSONArray array = new JSONArray(data);
			for (int i = 0; i < array.length(); i++) {
				ContentInfo info = gson.fromJson(array.getString(i), ContentInfo.class);
				infos.add(info);
			}
			lisntenr.onListDataLoaded(infos);
		}
	}

}
