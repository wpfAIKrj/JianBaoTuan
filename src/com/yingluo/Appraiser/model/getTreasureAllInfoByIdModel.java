package com.yingluo.Appraiser.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.bean.ImUserInfo;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.OnStringDataLoadListener;
import com.yingluo.Appraiser.utils.SqlDataUtil;

/**
 * @author ytmfdw 获取宝贝详情
 *
 */
public class getTreasureAllInfoByIdModel extends BaseModel {

	private OnStringDataLoadListener listener=null;
	
	public CollectionTreasure curnt=null;
	public List<CollectionTreasure> otherTreasure=null;
	public List<CommentEntity> treasureList=null;
	public getTreasureAllInfoByIdModel(OnStringDataLoadListener listener) {
		// TODO Auto-generated constructor stub
		this.listener=listener;
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getTreasureAllInfoByIdURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}
	
	public void getInfoTreasure(long treasure_id){
		StringBuffer sb=new StringBuffer(url);
		sb.append("&id=").append(treasure_id);
		url=sb.toString();
		setHTTPMODE(HttpMethod.GET);
		sendHttp();
	}
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		try {
			Gson gson = new Gson();
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get treasure by id :" + data);
			//解析宝贝详情
				JSONObject json=new JSONObject(data);
				CollectionTreasure treasure=new CollectionTreasure();
				treasure.user_id=json.getLong("user_id");
				treasure.authName=json.getString("authName");
				treasure.authImage=json.getString("authImage");
				treasure.authLevel=json.getInt("authLevel");
				treasure.name=json.getString("content");
				treasure.viewTimes=json.getInt("viewTimes");
				treasure.treasure_id=json.getLong("treasure_id");
				treasure.authType=json.getInt("authType");
				treasure.isCollected=json.getBoolean("isCollected");
				treasure.status=json.getInt("status");
				ImUserInfo user=new ImUserInfo();
				user.setId(treasure.user_id);
				user.setName(treasure.authName);
				user.setIcon(treasure.authImage);
				SqlDataUtil.getInstance().saveIMUserinfo(user);
				List<String> images=gson.fromJson(json.getString("images1"), new TypeToken<List<String>>(){}.getType());
				if(images.size()>0){
					treasure.images1=images.toArray(new String[images.size()]);
				}
				images=gson.fromJson(json.getString("images2"), new TypeToken<List<String>>(){}.getType());
				if(images.size()>0){
					treasure.images2=images.toArray(new String[images.size()]);
				}
				JSONArray kinds=json.getJSONArray("kinds");
				TreasureType key=new TreasureType();
				for (int i = 0; i < kinds.length(); i++) {
					JSONObject kind = kinds.getJSONObject(i);
					if(i>=1){
						key.parent_id=key.id;
					}else{
						key.parent_id=0;
					}
					key.id=kind.getLong("id");
					key.name=kind.getString("name");
					key.type=i;
				}
				treasure.kind=key;
				curnt=treasure;
			//解析热门宝物
				JSONArray array1 = json.getJSONArray("others");
				otherTreasure=new ArrayList<CollectionTreasure>();
				if(array1.length()>0){
				for (int i = 0; i < array1.length(); i++) {
					JSONObject obj=array1.getJSONObject(i);
					CollectionTreasure other=new CollectionTreasure();
					other.viewTimes=obj.getInt("viewTimes");
					other.authName=obj.getString("authName");
					other.authImage=obj.getString("authImage");
					other.authLevel=obj.getInt("authLevel");
					other.name=obj.getString("name");
					String otherimages=obj.getString("images");
					images=gson.fromJson(otherimages, new TypeToken<List<String>>(){}.getType());
					if(images.size()>0){
						other.images=images.toArray(new String[images.size()]);
					}
					other.treasure_id=obj.getLong("treasure_id");
//					kinds=obj.getJSONArray("kinds");
//					key=new TreasureType();
//					for (int j = 0; j < kinds.length(); j++) {
//						JSONObject kind = kinds.getJSONObject(j);
//						key.id=kind.getLong("id");
//						key.name=kind.getString("name");
//						key.type=j;
//						if(j>1){
//							key.parent_id=kinds.getJSONObject(j-1).getLong("id");
//						}else{
//							key.parent_id=0;
//						}
//					}
//					other.kind=key;
					otherTreasure.add(other);
				}}
				
				//解析宝贝鉴定结果
				String array2 = json.getString("treasureList");
				treasureList=gson.fromJson(array2, new TypeToken<List<CommentEntity>>(){}.getType());	
				listener.onBaseDataLoaded("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listener.onBaseDataLoadErrorHappened("-1", "json error");
		}
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		listener.onBaseDataLoadErrorHappened(error, msg);
	}



}
