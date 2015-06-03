package com.it.presenter;

import org.json.JSONObject;

import com.it.config.NetConst;
import com.it.inter.UpLoadFileInterface;
import com.it.model.AttestPersonalModel;
import com.it.utils.UploadUtils;
import com.it.view.inter.onBasicView;
import com.qiniu.android.http.ResponseInfo;

public class AttestPersonalPresenter implements OnBasicDataLoadListener<String>,UpLoadFileInterface {

	
	private onBasicView<String> mView;
	
	private String name;
	private String number;
	private String key;
	
	private AttestPersonalModel model;
	public AttestPersonalPresenter(onBasicView<String> View) {
		// TODO Auto-generated constructor stub
		 mView=View;
	}
	
	public void uploadAttestPersonal(String path,String name,String number){
		this.name=name;
		this.number=number;
		this.key=null;
		UploadUtils.UploadPortrait(path, this);
	}
	
	private void uploadAttest(){
		model=new AttestPersonalModel();
		model.AttestPersonalUp(key, name, number, this);
	}
	
	

	@Override
	public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
		// TODO Auto-generated method stub
		if(arg2!=null){
			try {
				key=arg2.getString(NetConst.UPKEY);
				uploadAttest();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			mView.onFail("-2", "图片上传失败");
		}
	}

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
	public void onBaseDataLoaded(String data) {
		// TODO Auto-generated method stub
		mView.onSucess(data);
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mView.onFail(errorCode, errorMsg);
	}

}
