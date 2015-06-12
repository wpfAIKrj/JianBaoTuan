package com.yingluo.Appraiser.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;

/**
 * @author ytmfdw 获取他的宝物
 *
 */
public class CollectTreasureByIdModel extends BaseModel {

	private String msg = "";

	String url_collect = "";
	String url_delete = "";

	public CollectTreasureByIdModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url_collect = UrlUtil.collectTreasureById();
		url_delete = UrlUtil.deleteCollectTreasureById();
		/*
		 * StringBuffer sb = new StringBuffer(url); if (NetConst.SESSIONID !=
		 * null) { sb.append("?").append(NetConst.SID).append("=")
		 * .append(NetConst.SESSIONID); } else {
		 * sb.append("?").append(NetConst.SID).append("=").append(""); } url =
		 * sb.toString();
		 */
	}

	/**
	 * 根据宝物id来判断宝物是否被收藏
	 * */
	public void isCollect(final CommonCallBack callBack, long tid) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		url = url_collect;
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&tid=").append(tid);
		url = sb.toString();
		httpUtils.send(httpmodel, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				// onSuccessForString(responseInfo.result);
				String result = responseInfo.result;
				LogUtils.i("get result=" + result);
				try {
					JSONObject json = new JSONObject(result);
					if (json != null) {
						if (json.getInt("code") == 210003) {
							// 正常返回
							callBack.onSuccess();
						} else {
							msg = json.getString("message");
							callBack.onError();
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					callBack.onError();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				onFailureForString(error.getMessage(), msg);
				CollectTreasureByIdModel.this.msg = msg;
				callBack.onError();
			}

		});
	}

	public void sendHttp(final CommonCallBack callBack, long tid,
			final boolean isCollect) {
		final HttpUtils httpUtils = new HttpUtils(connTimeout);
		if (isCollect) {
			// 收藏url
			url = url_collect;
		} else {
			// 取消收藏url
			url = url_delete;
		}
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&tid=").append(tid);
		url = sb.toString();
		httpUtils.send(httpmodel, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				// onSuccessForString(responseInfo.result);
				String result = responseInfo.result;
				LogUtils.i("get result=" + result);
				try {
					JSONObject json = new JSONObject(result);
					if (json != null) {
						int code=isCollect?1000:100000;
						if (json.getInt("code") == code) {
							// 正常返回
							callBack.onSuccess();
						} else {
							msg = json.getString("message");
							callBack.onError();
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					callBack.onError();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				onFailureForString(error.getMessage(), msg);
				CollectTreasureByIdModel.this.msg = msg;
				callBack.onError();
			}

		});
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		try {
			// String json_data = json.getString("data");

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

	public String getResult() {
		return msg;
	}

}
