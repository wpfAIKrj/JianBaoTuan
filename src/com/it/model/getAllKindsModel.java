package com.it.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.it.bean.ContentInfo;
import com.it.bean.TreasureType;
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
 * 获取宝物分类信息
 * @author xy418
 *
 */
public class getAllKindsModel extends BaseModel {

	private ArrayList<TreasureType> datas;
	private OnListDataLoadListener<TreasureType> lis;
	public getAllKindsModel(OnListDataLoadListener<TreasureType> listener) {
		// TODO Auto-generated constructor stub
		lis=listener;
		httpmodel = HttpMethod.GET;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.GETALLKINDTYPE);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=");
		}
		url=sb.toString();
	}
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		datas=new ArrayList<TreasureType>();
		try {
			JSONArray all=new JSONArray(data);
			for (int i = 0; i < all.length(); i++) {
				JSONObject json=all.getJSONObject(i);
				TreasureType firsttype=new TreasureType();
				firsttype.setType(TreasureType.TYPE_FIRST);
				firsttype.setCurrnt_id(json.getLong("id"));
				firsttype.setName(json.getString("name"));
				firsttype.setParent_id((long) -1);
				datas.add(firsttype);
				JSONArray secall=json.getJSONArray("children");
				for (int j = 0; j < secall.length(); j++) {
					JSONObject secendjson=secall.getJSONObject(j);
					TreasureType secendtype=new TreasureType();
					secendtype.setType(TreasureType.TYPE_SECOND);
					secendtype.setCurrnt_id(secendjson.getLong("id"));
					secendtype.setName(secendjson.getString("name"));
					secendtype.setParent_id(secendjson.getLong("parent_id"));
					datas.add(secendtype);
					JSONArray thirdall=secendjson.getJSONArray("children");
					for (int k = 0; k < thirdall.length(); k++) {
						JSONObject thirdjson=thirdall.getJSONObject(k);
						TreasureType thirdtype=new TreasureType();
						thirdtype.setType(TreasureType.TYPE_THIRD);
						thirdtype.setCurrnt_id(thirdjson.getLong("id"));
						thirdtype.setName(thirdjson.getString("name"));
						thirdtype.setParent_id(thirdjson.getLong("parent_id"));
						datas.add(thirdtype);
					}
				}
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
