package com.yingluo.Appraiser.model;

import com.lidroid.xutils.http.RequestParams;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;

public class AttestPersonalModel extends BaseModel {

	private String name;
	private String number;
	private String key;
	
	private OnBasicDataLoadListener<String> lis;
	
	public void AttestPersonalUp(String key,String name,String number,OnBasicDataLoadListener<String> lis){
		this.lis=lis;
		this.key=key;
		this.name=name;
		this.number=number;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.ATTESTPERSONAL);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}else{
			sb.append("?").append(NetConst.SID).append("=").append("");
		}
		url=sb.toString();
		addRequestParams();
		sendHttp();
		
	}
	
	@Override
	public void analyzeData(String data) throws Exception {
		// TODO Auto-generated method stub
		lis.onBaseDataLoaded(data);
	}

	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter("realname", name);
		params.addBodyParameter("card_id", number);
		params.addBodyParameter("certificate", key);
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lis.onBaseDataLoadErrorHappened(error, msg);
	}

}
