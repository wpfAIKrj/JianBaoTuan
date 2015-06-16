package com.yingluo.Appraiser.presenter;

import org.json.JSONObject;

import com.qiniu.android.http.ResponseInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.OnBasicDataLoadListener;
import com.yingluo.Appraiser.inter.UpLoadFileInterface;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.AttestPersonalModel;
import com.yingluo.Appraiser.utils.UploadUtils;

public class AttestAgencyPresenter implements OnBasicDataLoadListener<String> {

	
	private onBasicView<String> mView;
	
	private String name;
	private String key2;
	private String key1;
	
	private AttestPersonalModel model;
	public AttestAgencyPresenter(onBasicView<String> View) {
		// TODO Auto-generated constructor stub
		 mView=View;
	}
	
	public void uploadAttestAgency(String pathAgency,String pathPersonal,String name){
		this.name=name;
		this.key1=pathAgency;
		this.key2=pathPersonal;
		
		UploadUtils.UploadPortrait(pathAgency, upAgency);
	}
	
	private void uploadAttest(){
		model=new AttestPersonalModel();
		model.AttestPersonalUp(key1, key2,name, this);
	}
	
	
	private UpLoadFileInterface upAgency=new UpLoadFileInterface() {
		
		@Override
		public void progress(String arg0, double arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isCancelled() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
			// TODO Auto-generated method stub
			if(arg2!=null){
				try {
					key1=arg2.getString(NetConst.UPKEY);
					UploadUtils.UploadPortrait(key2, upPersonal);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				mView.onFail("-2", "图片上传失败");
			}
		}
	};
	

	@Override
	public void onBaseDataLoaded(String data) {
		// TODO Auto-generated method stub
		mView.onSucess(data);
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mView.onFail(errorCode, errorMsg);
	}

	private UpLoadFileInterface upPersonal=new UpLoadFileInterface() {
		
		@Override
		public void progress(String arg0, double arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isCancelled() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
			// TODO Auto-generated method stub
			if(arg2!=null){
				try {
					key2=arg2.getString(NetConst.UPKEY);
					uploadAttest();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				mView.onFail("-2", "图片上传失败");
			}
		}
	};
	
}
