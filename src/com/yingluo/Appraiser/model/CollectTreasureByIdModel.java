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
import com.yingluo.Appraiser.inter.onBasicView;

/**
 * @author ytmfdw 获取他的宝物
 *
 */
public class CollectTreasureByIdModel extends BaseModel {

	private String msg = "";

	String url_collect = "";
	String url_delete = "";

	private long treasure_id;

	private onBasicView<String> lis;

	public CollectTreasureByIdModel() {
		// TODO Auto-generated constructor stub
		httpmodel = HttpMethod.GET;
		url_collect = UrlUtil.collectTreasureById();
		url_delete = UrlUtil.deleteCollectTreasureById();
	}
	
	/**
	 * 收藏藏品
	 * @param lis
	 * @param treasure_id
	 */
	public void isCollect(onBasicView<String> lis,long treasure_id){
		this.lis=lis;
		this.treasure_id=treasure_id;
		StringBuffer sb = new StringBuffer(url_collect);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&tid=").append(treasure_id);
		url = sb.toString();
		httpmodel=HttpMethod.GET;
		sendHttp();
	}

	
	/**
	 * 删除藏品
	 * @param lis
	 * @param treasure_id
	 */
	public void isDelete(onBasicView<String> lis,long treasure_id){
		this.lis=lis;
		this.treasure_id=treasure_id;
		StringBuffer sb = new StringBuffer(url_delete);
		if (NetConst.SESSIONID != null) {
			sb.append("?").append(NetConst.SID).append("=")
					.append(NetConst.SESSIONID);
		} else {
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&ids=").append(treasure_id);
		url = sb.toString();
		httpmodel=HttpMethod.GET;
		sendHttp();
	}

	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		lis.onSucess(data);
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lis.onFail(error, msg);
	}


}
