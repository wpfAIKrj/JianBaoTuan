package com.yingluo.Appraiser.model;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;

/**
 * 获取文章详情
 * @author Administrator
 *
 */
public class getdetailInfoModel extends BaseModel {

	private long id;
	private OnBasicDataLoadListener<ContentInfo> lis;
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		ContentInfo info=null;
		try {
			Gson gson=new Gson();
			info=gson.fromJson(data, ContentInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lis.onBaseDataLoaded(info);
		
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lis.onBaseDataLoadErrorHappened(error, msg);
	}

	public void getDetailInfo(Long user_id,long info_id,OnBasicDataLoadListener<ContentInfo> lis) {
		// TODO Auto-generated method stub
		this.lis=lis;
		id=info_id;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.GETDETAILINFO);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		sb.append("&user_id=").append(user_id);
		sb.append("&id=").append(id);
		url=sb.toString();
		httpmodel=HttpMethod.GET;
		sendHttp();
		
	}

}
