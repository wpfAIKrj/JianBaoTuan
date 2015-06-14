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
import com.yingluo.Appraiser.presenter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.presenter.OnStringDataLoadListener;

/**
 * @author ytmfdw 获取宝贝评论列表
 *
 */
public class getTreasureCommentListByIdModel extends BaseModel {

	private OnStringDataLoadListener listener=null;
	public List<CollectionTreasure> commentlist=null;
	public getTreasureCommentListByIdModel(OnStringDataLoadListener listener) {
		// TODO Auto-generated constructor stub
		this.listener=listener;
		httpmodel = HttpMethod.GET;
		url = UrlUtil.getTreasureCommentListByIdURL();
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
			commentlist=new ArrayList<CollectionTreasure>();
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
