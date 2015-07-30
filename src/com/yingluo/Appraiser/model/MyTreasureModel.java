package com.yingluo.Appraiser.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;

/**
 * @author ytmfdw 获取我的收藏，宝贝，鉴定
 *
 */
public class MyTreasureModel extends BaseModel {

	// 全部宝物
	public static final int TYPE_ALL = 0;
	// 未审核
	public static final int TYPE_NO = 3;
	// 未鉴定的
	public static final int TYPE_IDENTIFYING = 1;
	// 已鉴定的
	public static final int TYPE_IDENTIFIED = 2;

	private List<TreasureEntity> list = null;

	private int type = TYPE_ALL;

	public MyTreasureModel() {
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getMyCollectionURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
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
				sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
			} else {
				sb.append("?").append(NetConst.SID).append("=").append("");
			}
			url = sb.toString();
		}
			break;
		case Const.IDENTIFY: {// 我的鉴定
			url = UrlUtil.getMyIdentifyURL();
			StringBuffer sb = new StringBuffer(url);
			if (NetConst.SESSIONID != null) {
				sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
			} else {
				sb.append("?").append(NetConst.SID).append("=").append("");
			}
			url = sb.toString();
		}
			break;

		default:
			break;
		}
	}

	public void sendHttp(final CommonCallBack callBack, int type,Long uid) {
		if (list != null) {
			list.clear();
		}
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		StringBuffer sb = new StringBuffer(url);
		sb.append("&status=").append(type);
		sb.append("&user_id=").append(uid);
		// params.addBodyParameter("length", String.valueOf(type));
		url = sb.toString();
		LogUtils.d("ytmfdw  url=" + url);
		httpUtils.send(httpmodel, url, params, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				LogUtils.i("onSucess responseInfo=" + responseInfo);
				onSuccessForString(responseInfo.result);
				callBack.onSuccess();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				onFailureForString(error.getMessage(), msg);
				callBack.onError();
			}

		});
	}

	@Override
	public void analyzeData(String data) throws Exception {
		try {
			Gson gson = new Gson();
			// String json_data = json.getString("data");
			LogUtils.i("ytmfdw" + "get my foot print:" + data);
			list = gson.fromJson(data, new TypeToken<List<TreasureEntity>>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRequestParams() {

	}

	@Override
	public void onFailureForString(String error, String msg) {

	}

	public List<TreasureEntity> getResult() {
		if (list == null) {
			list = new ArrayList<TreasureEntity>();
		}
		return list;
	}

}
