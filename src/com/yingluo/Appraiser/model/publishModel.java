package com.yingluo.Appraiser.model;

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

	private String[] imageAll;

	private String[] imageTest;
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
			String context, String[] imageAll, String[] imageTest, OnBasicDataLoadListener<String> listener) {
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
		if(imageAll!=null&&imageAll.length>0){
			startuploadImageAll(imageAll, 0);	
		}else{
			if(imageTest!=null&&imageTest.length>0){
				startuploadImagefear(imageTest,0);	
			}else{
				startSend();
			}
		}
	}

	private void startuploadImageAll(final String[] imagepath, final int index) {
		// TODO 自动生成的方法存根
		String path=imagepath[index];
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
						imagepath[index] = arg2.getString(NetConst.UPKEY);
						int newindex=index+1;
						if(imagepath.length>newindex){//继续上传上一张
							startuploadImageAll(imagepath, newindex);
						}else{//上传完毕
							isUploadall=true;
							if(imageTest!=null&&imageTest.length>0){
								startuploadImagefear(imageTest,0);	
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

	private void startuploadImagefear(final String[] imagepath,final int index) {
		// TODO 自动生成的方法存根
		String path=imagepath[index];
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
						imagepath[index] = arg2.getString(NetConst.UPKEY);
						int newindex=index+1;
						if(imagepath.length>newindex){//继续上传上一张
							startuploadImagefear(imagepath, newindex);
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
		for (int i = 0; i < imageAll.length; i++) {
			String string = imageAll[i];
			allsb.append(string).append(",");
		}
		allsb.deleteCharAt(allsb.length()-1);
		
		for (int i = 0; i < imageTest.length; i++) {
			String str=imageTest[i];
			fearsb.append(str).append(",");
		}
		fearsb.deleteCharAt(fearsb.length()-1);
		addRequestParams();
		setHTTPMODE(HttpMethod.POST);
		sendHttp();
	}	

	

}
