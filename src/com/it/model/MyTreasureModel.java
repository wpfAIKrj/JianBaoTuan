package com.it.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.provider.SyncStateContract.Constants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.bean.CollectionEntity;
import com.it.bean.HomeEntity;
import com.it.bean.TreasureEntity;
import com.it.config.Const;
import com.it.config.NetConst;
import com.it.config.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author ytmfdw 获取我的收藏，宝贝，鉴定
 *
 */
public class MyTreasureModel extends BaseModel {

	//全部宝物
	public static final int TYPE_ALL = 0;
	//未鉴定的
	public static final int TYPE_IDENTIFYING = 1;
	//已鉴定的
	public static final int TYPE_IDENTIFIED = 2;

	private List<TreasureEntity> list = null;

	private int type = TYPE_ALL;

	public MyTreasureModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getMyCollectionURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}

	public void setType(int type) {
		switch (type) {
		case Const.COLLECT: {// 我的收藏
			url = UrlUtil.getMyCollectionURL();
			StringBuffer sb = new StringBuffer(url);
			if (NetConst.SESSIONID != null) {
				sb.append("?").append(NetConst.SID).append("=")
						.append(NetConst.SESSIONID);
			} else {
				sb.append("?").append(NetConst.SID).append("=").append("");
			}
			url = sb.toString();
		}
			break;
		case Const.PRECIOUS: {// 我的宝物

		}
			break;
		case Const.IDENTIFY: {// 我的鉴定

		}
			break;

		default:
			break;
		}
	}

	public void sendHttp(final CommonCallBack callBack, int type) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		params = new RequestParams();
		params.addBodyParameter("status", String.valueOf(type));
		// params.addBodyParameter("length", String.valueOf(type));
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
			list = gson.fromJson(data, new TypeToken<List<TreasureEntity>>() {
			}.getType());
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

	public List<TreasureEntity> getResult() {
		return list;
	}

}
