package com.yingluo.Appraiser.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;

/**
 * @author ytmfdw 获取他的宝物
 *
 */
public class getTreasureByOtherIdModel extends BaseModel {

	private List<CollectionTreasure> list = null;

	public getTreasureByOtherIdModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getTreasureByOtherIdURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	public void sendHttp(final CommonCallBack callBack,int page,long user_id) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		StringBuffer sb=new StringBuffer(url);
		//分页参数,每页20条
		sb.append("&length=");
		sb.append("&status=").append(0);
		sb.append("&user_id=").append(user_id);
		url=sb.toString();
		httpUtils.send(httpmodel, url, params, new RequestCallBack<String>() {

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
		try {
			Gson gson = new Gson();
			list = gson.fromJson(data,
					new TypeToken<List<CollectionTreasure>>() {
					}.getType());
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get treasure by id :" + data);
//			JSONArray array=new JSONArray(data);
//			if(array.length()<0){
//				return;
//			}
//			list=new ArrayList<CollectionTreasure>();
//			for (int i = 0; i < array.length(); i++) {
//				JSONObject obj=array.getJSONObject(i);
//				CollectionTreasure treasure=new CollectionTreasure();
//				treasure.user_id=obj.getLong("user_id");
//				treasure.authName=obj.getString("authName");
//				treasure.authImage=obj.getString("authImage");
//				treasure.authLevel=obj.getInt("authLevel");
//				treasure.name=obj.getString("name");
//				treasure.viewTimes=obj.getInt("viewTimes");
//				treasure.treasure_id=obj.getLong("treasure_id");
//				 JSONArray str = obj.getJSONArray("images");
//				 String[] images=new String[str.length()];
//				 for (int j = 0; j < str.length(); j++) {
//					images[j]=str.getString(j);
//				}
//				list.add(treasure);
//			}
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

	public List<CollectionTreasure> getResult() {
		if (list== null) {
			list = new ArrayList<CollectionTreasure>();
		}
		return list;
	}

}
