package com.yingluo.Appraiser.model;

import java.util.ArrayList;
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
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.ImUserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;

/**
 * @author ytmfdw 获取我的足迹
 *
 */
public class getMyFootPrintsModel extends BaseModel {

	private List<CollectionTreasure> list = null;

	public getMyFootPrintsModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getMyFootPrintsURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	public void sendHttp(final CommonCallBack callBack) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
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
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get my foot print:" + data);
			list = gson.fromJson(data, new TypeToken<List<CollectionTreasure>>() {
			}.getType());
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					ImUserInfo user=new ImUserInfo();
					CollectionTreasure collectionTreasure=list.get(i);
					user.setId(collectionTreasure.user_id);
					user.setName(collectionTreasure.authName);
					user.setIcon(collectionTreasure.authImage);
					SqlDataUtil.getInstance().saveIMUserinfo(user);
				}		
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

	public List<CollectionTreasure> getResult() {
		if (list == null) {
			list = new ArrayList<CollectionTreasure>();
		}
		return list;
	}

}
