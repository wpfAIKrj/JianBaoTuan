package com.it.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.inter.UpLoadFileInterface;
import com.it.model.LoginModel;
import com.it.model.UpLoadLogoModel;
import com.it.utils.UploadUtils;
import com.it.view.inter.onBasicView;
import com.lidroid.xutils.util.LogUtils;
import com.qiniu.android.http.ResponseInfo;

public class UploadLogoPresenter implements OnBasicDataLoadListener<UserInfo>,UpLoadFileInterface {
	
	
	private onBasicView<UserInfo> mview;
	private UpLoadLogoModel mModel;
	private String filepath;
	private String qq;
	private String email;
	private String key;
	private String name;
	public UploadLogoPresenter(onBasicView iview) {
		// TODO Auto-generated constructor stub
		mview=iview;
	}

	public void startUpLoadLogo(String path, String email, String qq, String name){
		this.filepath=path;
		this.qq=qq;
		this.email=email;
		this.name=name;
		UploadUtils.UploadPortrait(filepath, this);
	}
	
	
	public void startExtra(String key, String name,String email, String qq){
		this.key=key;
		this.email=email;
		this.qq=qq;
		this.name=name;
		mModel=new UpLoadLogoModel();
		mModel.startUpload(key,name,qq,email,this);
		mModel.addRequestParams();
		mModel.sendHttp();
	}
	
	@Override
	public void onBaseDataLoaded(UserInfo data) {
		// TODO Auto-generated method stub
		if(data==null){
			mview.onFail("-1", "服务器异常");
		}else{
			mview.onSucess(data);
		}
	}

	@Override
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		mview.onFail(errorCode,errorMsg);
	}

	
	@Override
	public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
		// TODO Auto-generated method stub
		if(arg2!=null){
			try {
				key=arg2.getString(NetConst.UPKEY);
				startExtra(key, name,email, qq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			mview.onFail("-2", "图片上传失败");
		}
	}

	
	@Override
	public void progress(String arg0, double arg1) {
		// TODO Auto-generated method stub
		LogUtils.d("文件："+arg0+"当前上传进度为："+arg1);
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}
	



}
