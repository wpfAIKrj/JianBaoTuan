package com.yingluo.Appraiser.model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.qiniu.android.http.ResponseInfo;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.UpLoadFileInterface;
import com.yingluo.Appraiser.presenter.UploadLogoPresenter;
import com.yingluo.Appraiser.utils.UploadUtils;

public class publishModel extends BaseModel {
	
	
	
	private OnBasicDataLoadListener<String> listener;
	
	private String user_id;
	private String kind_name;
	private String kind_id;
	private String context;
	
	private StringBuffer allsb=new StringBuffer();
	private StringBuffer fearsb=new StringBuffer();
	private boolean isUploadall=false;
	private boolean isuoloadfear=false;

	private ArrayList<String> imageAll;

	private ArrayList<String> imageTest;
	@Override
	public void addRequestParams() {
		// TODO Auto-generated method stub
		params=new RequestParams();
		params.addBodyParameter("description", context);
		params.addBodyParameter("allviewimages", allsb.toString());
		params.addBodyParameter("otherimages",fearsb.toString() );
		params.addBodyParameter("kindname",kind_name );
		params.addBodyParameter("kindid", kind_id);
		params.addBodyParameter("appraiser_id", user_id);
	}

	@Override
	public void onFailureForString(String error, String msg) {
		// TODO Auto-generated method stub
		listener.onBaseDataLoadErrorHappened(error, msg);
	}

	@Override
	public void analyzeData(String data) {
		// TODO Auto-generated method stub
		listener.onBaseDataLoaded(data);
	}

	
	public void startSendTreasure(UserInfo user, TreasureType type,
			String context, ArrayList<String> imageAll, ArrayList<String> imageTest, OnBasicDataLoadListener<String> listener) {
		// TODO 自动生成的方法存根
		this.listener=listener;
		isuoloadfear=false;
		isUploadall=false;
		this.context=context;
		this.imageAll=imageAll;
		this.imageTest=imageTest;
		if(user!=null){
			user_id=String.valueOf(user.getId());
		}else{
			user_id=String.valueOf(0);
		}
		kind_id=String.valueOf(type.getId());
		kind_name=type.getName();
		
		StringBuffer sb=new StringBuffer(url);
		sb.append(NetConst.PUBLISH_TREASURES);
		if(NetConst.SESSIONID!=null){
			sb.append("?").append(NetConst.SID).append("=").append(NetConst.SESSIONID);
		}
		url=sb.toString();
		startUploadImage();
	}



	private void startUploadImage() {
		// TODO 自动生成的方法存根
		if(imageAll!=null&&imageAll.size()>0){
			startuploadImageAll(0);	
		}else{
			if(imageTest!=null&&imageTest.size()>0){
				startuploadImagefear(0);	
			}else{
				startSend();
			}
		}
	}

	private void startuploadImageAll( final int index) {
		// TODO 自动生成的方法存根
		String path=imageAll.get(index);
		UploadUtils.UploadPortrait(path, new UpLoadFileInterface() {
			
			@Override
			public void progress(String arg0, double arg1) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public boolean isCancelled() {
				// TODO 自动生成的方法存根
				return false;
			}
			
			@Override
			public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
				// TODO 自动生成的方法存根
				if(arg2!=null){
					try {
						allsb.append(arg2.getString(NetConst.UPKEY)).append(",");
						int newindex=index+1;
						if(imageAll.size()>newindex){//继续上传上一张
							startuploadImageAll(newindex);
						}else{//上传完毕
							isUploadall=true;
							if(imageTest!=null&&imageTest.size()>0){
								startuploadImagefear(0);	
							}else{
								startSend();
							}
						}
					} catch (JSONException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}else{
					listener.onBaseDataLoadErrorHappened("-1", "上传图片失败");
				}
			}

		});

	}

	private void startuploadImagefear(final int index) {
		// TODO 自动生成的方法存根
		String path=imageTest.get(index);
		UploadUtils.UploadPortrait(path, new UpLoadFileInterface() {
			
			@Override
			public void progress(String arg0, double arg1) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public boolean isCancelled() {
				// TODO 自动生成的方法存根
				return false;
			}
			
			@Override
			public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
				// TODO 自动生成的方法存根
				if(arg2!=null){
					try {
						fearsb.append(arg2.getString(NetConst.UPKEY)).append(",");
						int newindex=index+1;
						if(imageTest.size()>newindex){//继续上传上一张
							startuploadImagefear(newindex);
						}else{//上传完毕
							isuoloadfear=true;
							startSend();
						}
					} catch (JSONException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}else{
					listener.onBaseDataLoadErrorHappened("-1", "上传图片失败");
				}
			}

		});
	}

	
	protected void startSend() {
		// TODO 自动生成的方法存根
	
		allsb.deleteCharAt(allsb.length()-1);
		fearsb.deleteCharAt(fearsb.length()-1);
		addRequestParams();
		setHTTPMODE(HttpMethod.POST);
		sendHttp();
	}	

	

}
