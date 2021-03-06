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
 * 获取用户收藏文章列表
 * @author Administrator
 *
 */
public class getCollectArticleModel extends BaseModel{
	

	private OnListDataLoadListener<ContentInfo> lisntenr;
	
	public void getArticleList(Long uid, int group_size ,OnListDataLoadListener<ContentInfo> lis){
		this.lisntenr=lis;
		
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.GETCOLLECTINFO);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&user_id=").append(uid);
		sb.append("&length=").append(String.valueOf(group_size));
		url=sb.toString();
	}
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();

	}
	
	

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lisntenr.onListDataLoadErrorHappened(error, msg);
	}




	@Override
	public void setHTTPMODE(HttpMethod httpmodel) {
		// TODO Auto-generated method stub
		this.httpmodel=httpmodel;
	}




	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		ArrayList<ContentInfo> infos=new ArrayList<ContentInfo>();
			JSONArray array=new JSONArray(data);
			for (int i = 0; i < array.length(); i++) {
				ContentInfo info=gson.fromJson(array.getString(i), ContentInfo.class);
				infos.add(info);
//				try {
//					ContentInfo info=new ContentInfo();
//					JSONObject object=array.getJSONObject(i);
//					info.setId(object.getLong("content_id"));
//					info.setTitle(object.getString("title"));
//					info.setView_times(object.getInt("viewTimes"));
//					info.setImage(object.getString("image"));
//					infos.add(info);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
			}
			lisntenr.onListDataLoaded(infos);
		}



	
	
}
