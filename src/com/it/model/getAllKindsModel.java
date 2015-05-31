package com.it.model;

import java.util.ArrayList;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.it.bean.ContentInfo;
import com.it.bean.ContentType;
import com.it.config.NetConst;
import com.it.config.UrlUtil;
import com.it.presenter.OnListDataLoadListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * 获取内容分类信息
 * @author xy418
 *
 */
public class getAllKindsModel extends BaseModel {

	private ArrayList<ContentType> datas;
	private OnListDataLoadListener<ContentType> lis;
	public getAllKindsModel(OnListDataLoadListener<ContentType> listener) {
		// TODO Auto-generated constructor stub
		lis=listener;
		httpmodel = HttpMethod.GET;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.GETALLKINDS);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}
		url=sb.toString();
	}
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		datas=new ArrayList<ContentType>();
		try {
			JSONArray array=new JSONArray(data);
			Gson gson=new Gson();
			for (int i = 0; i < array.length(); i++) {
				ContentType type=gson.fromJson(array.getJSONObject(i).toString(), ContentType.class);
				datas.add(type);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lis.onListDataLoaded(datas);
		
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lis.onListDataLoadErrorHappened(error, msg);
	}

}
