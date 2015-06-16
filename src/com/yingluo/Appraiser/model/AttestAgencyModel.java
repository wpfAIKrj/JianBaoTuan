package com.yingluo.Appraiser.model;

import com.lidroid.xutils.http.RequestParams;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;

public class AttestAgencyModel extends BaseModel {

	private String name;
	private String key1;
	private String key2;
	
	private OnBasicDataLoadListener<String> lis;
	
	public void AttestPersonalUp(String key1,String key2,String name,OnBasicDataLoadListener<String> lis){
		this.lis=lis;
		this.key1=key1;
		this.name=name;
		this.key2=key2;
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.ATTESTAGENCY);
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
		params.addBodyParameter("company_name", name);
		params.addBodyParameter("company_certificate", key1);
		params.addBodyParameter("licence", key2);
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		lis.onBaseDataLoadErrorHappened(error, msg);
	}

}
