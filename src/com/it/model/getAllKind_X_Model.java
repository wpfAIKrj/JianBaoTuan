package com.it.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.bean.CollectionEntity;
import com.it.bean.HomeEntity;
import com.it.bean.KindEntity;
import com.it.config.NetConst;
import com.it.config.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author ytmfdw 获取我的足迹
 *
 */
public class getAllKind_X_Model extends BaseModel {

	private List<KindEntity> first = null;
	private List<KindEntity> second = null;
	private List<KindEntity> third = null;

	private List<List<KindEntity>> list = null;

	public getAllKind_X_Model() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getAllKinds();
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
	public void onSuccessForString(String jsonstring) {
		try {
			LogUtils.d(jsonstring);
			JSONObject json = new JSONObject(jsonstring);
			int code = json.getInt(NetConst.CODE);
			String message = json.getString(NetConst.MESSAGE);
			if (code == NetConst.CODE_SUCCESS) {
				analyzeData(jsonstring);

			} else {
				onFailureForString(String.valueOf(code), message);
			}
		} catch (Exception e) {
			// TODO: handle exception
			onFailureForString(HTTP_ERROR, e.getMessage());
		}
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		first = new ArrayList<KindEntity>();
		second = new ArrayList<KindEntity>();
		third = new ArrayList<KindEntity>();
		list = new ArrayList<List<KindEntity>>();
		JSONObject json = new JSONObject(data);
		LogUtils.d("?" + (json == null));
		try {
			if (json != null) {
				JSONArray jsons = new JSONArray(json.getString(NetConst.DATA));
				int size = jsons.length();
				LogUtils.d("begin size=" + size);
				for (int i = 0; i < size; i++) {
					LogUtils.d("first begin size=" + size);
					JSONObject firstObj = jsons.getJSONObject(i);
					KindEntity firstEntity = new KindEntity();
					firstEntity.id = firstObj.getLong(NetConst.INFO_ID);
					firstEntity.name = firstObj.getString("name");
					firstEntity.type = KindEntity.TYPE_FIRST;
					JSONArray secondJSONs = firstObj.getJSONArray("children");
					if (secondJSONs != null) {
						int size_2 = secondJSONs.length();
						for (int j = 0; j < size_2; j++) {
							LogUtils.d("second begin size=" + size_2);
							JSONObject secondObj = secondJSONs.getJSONObject(j);
							KindEntity secondEntity = new KindEntity();
							secondEntity.id = secondObj
									.getLong(NetConst.INFO_ID);
							secondEntity.name = secondObj.getString("name");
							secondEntity.parent_id = secondObj
									.getLong("parent_id");
							secondEntity.type = KindEntity.TYPE_SECOND;

							JSONArray thirdJSONs = secondObj
									.getJSONArray("children");
							if (thirdJSONs != null) {
								int size_3 = thirdJSONs.length();
								for (int k = 0; k < size_3; k++) {
									LogUtils.d("third begin size=" + size_3);
									JSONObject thirdObj = thirdJSONs
											.getJSONObject(k);
									KindEntity thirdEntity = new KindEntity();
									thirdEntity.id = thirdObj
											.getLong(NetConst.INFO_ID);
									thirdEntity.name = thirdObj
											.getString("name");
									thirdEntity.parent_id = thirdObj
											.getLong("parent_id");
									thirdEntity.type = KindEntity.TYPE_THIRD;

									third.add(thirdEntity);
								}
							}

							second.add(secondEntity);
						}
					}

					first.add(firstEntity);
				}

				list.add(first);
				list.add(second);
				list.add(third);
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

	public List<List<KindEntity>> getResult() {
		if (list == null) {
			list = new ArrayList<List<KindEntity>>();
		}
		return list;
	}

}
