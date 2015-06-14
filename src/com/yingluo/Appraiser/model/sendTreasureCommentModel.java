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
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.config.UrlUtil;
import com.yingluo.Appraiser.presenter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.presenter.OnStringDataLoadListener;

/**
 * @author ytmfdw 发表宝贝评论
 *
 */
public class sendTreasureCommentModel extends BaseModel {

	private OnStringDataLoadListener listener=null;
	private long treasure_id;
	private long to_user_id;
	private String comment;
	public sendTreasureCommentModel(OnStringDataLoadListener listener) {
		// TODO Auto-generated constructor stub
		this.listener=listener;
		url = UrlUtil.sendTreasureCommentURL();
		StringBuffer sb = new StringBuffer(url);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url = sb.toString();
	}
	
	public void sendTreasureComment(long treasure_id,long to_user_id,String comment){
		this.treasure_id=treasure_id;
		this.to_user_id=to_user_id;
		this.comment=comment;
		addRequestParams();
		sendHttp();
	}
	
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter("treasure_id", String.valueOf(treasure_id));
		params.addBodyParameter("to_user_id", String.valueOf(to_user_id));
		params.addBodyParameter("comment", comment);
		
	}
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		try {
			Gson gson = new Gson();
			// String json_data = json.getString("data");
			LogUtils.i("ytmdfdw" + "get treasure by id :" + data);
	
			listener.onBaseDataLoaded("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listener.onBaseDataLoadErrorHappened("-1", "json error");
		}
	}


	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		listener.onBaseDataLoadErrorHappened(error, msg);
	}



}
