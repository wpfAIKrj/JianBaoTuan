package com.yingluo.Appraiser.model;

import java.util.ArrayList;
import java.util.List;

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
 * @author ytmfdw 获取我的宝物
 *
 */
public class getTreasureByIdModel extends BaseModel {

	private List<TreasureEntity> list = null;

	public getTreasureByIdModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getTreasureByIdURL();
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
//		sb.append("&length").append(page);
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
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get treasure by id :" + data);
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
		if (list == null) {
			list = new ArrayList<TreasureEntity>();
		}
		return list;
	}

}
