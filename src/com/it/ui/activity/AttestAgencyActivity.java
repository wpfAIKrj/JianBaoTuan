package com.it.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.it.R;
import com.it.inter.onBasicView;
import com.it.model.AttestAgencyModel;
import com.it.presenter.AttestAgencyPresenter;
import com.it.ui.base.BaseActivity;
import com.it.ui.dialog.SelectPhotoDialog;
import com.it.utils.BitmapsUtils;
import com.it.utils.DialogUtil;
import com.it.utils.FileUtils;
import com.it.utils.ImageUtils;
import com.it.utils.ToastUtils;
import com.it.view.CircleImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class AttestAgencyActivity extends BaseActivity {

	private SelectPhotoDialog dialog;
	
	@ViewInject(R.id.home_title)
	private TextView title;
	
	private ImageUtils imageUtils;
	
	private String pathPersonal=null;
	
	private String pathAgency=null;
	
	private String name=null;
	
	@ViewInject(R.id.et_name)
	private EditText et_name;
	
	@ViewInject(R.id.imageView01)
	private CircleImageView imageview1;
	
	@ViewInject(R.id.imageView02)
	private CircleImageView imageview2;
	
	private Dialog loading;
	private int imagepath=-1;
	
	private AttestAgencyPresenter myPresenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attest_agentcy);
		ViewUtils.inject(this);
		initView();
		initData();
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.title_personal);
		imageUtils=new ImageUtils(this);
		myPresenter=new AttestAgencyPresenter(linstener);
	}
	private void initData() {
		// TODO Auto-generated method stub
		
	}

	@OnClick({R.id.btn_back,R.id.bt_attest,R.id.imageView01,R.id.imageView02})
	public void OnClick(View v){
		switch (v.getId()) {
		case R.id.btn_back://返回上一层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.bt_attest://提交认证信息
			name=et_name.getText().toString();
			if(name!=null&&!name.isEmpty()){
				if(pathAgency!=null){
					if(pathPersonal!=null){
						startAttestAgency();
					
					}else{
						new ToastUtils(this, "请上传个人执照！");
					}
				}else{
					new ToastUtils(this, "请上传营业执照！");
				}
			}else{
				new ToastUtils(this, "请输入正确的机构名称！");
			}
			
			break;
		case R.id.imageView01://机构
			imagepath=1;
			if(dialog==null){
				dialog=new SelectPhotoDialog(this, ImageListner1);
			}
			if(!dialog.isShowing()){
				dialog.show();
			}
			break;
		case R.id.imageView02://个人
			imagepath=2;
			if(dialog==null){
				dialog=new SelectPhotoDialog(this, ImageListner1);
			}
			if(!dialog.isShowing()){
				dialog.show();
			}
			break;
		default:
			break;
		}
	}
	
	private void startAttestAgency() {
		// TODO Auto-generated method stub
		if(loading==null){
			loading=DialogUtil.createLoadingDialog(this, "正在提交认证中...");
		}
		loading.show();
		myPresenter.uploadAttestAgency(pathAgency, pathPersonal, name);
	}

	private OnClickListener ImageListner1 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_take_photo://相机获取
			
				imageUtils.openCameraImage();
				break;
			case R.id.btn_pick_photo://相册获取
			
				imageUtils.openLocalImage();
				break;
			case R.id.btn_cancel://取消
				imagepath=-1;
			if(dialog!=null){
					dialog.dismiss();
				}
				break;
			default:
				break;
			}
		}
	};
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==ImageUtils.GET_IMAGE_BY_CAMERA&&resultCode==RESULT_OK){//获取照片地址获取到图片（相机）
			if(imageUtils.PICPATH!=null){
				saveImage();
			}
		}
		if(requestCode==ImageUtils.GET_IMAGE_FROM_PHONE&&resultCode==Activity.RESULT_OK){//获取照片地址获取到图片（相册）
			if(data != null && data.getData() != null) {
				imageUtils.doPhoto( data);
				if(imageUtils.PICPATH!=null){
					saveImage();
				}
			}
		}
		if(requestCode==ImageUtils.GET_IMAGE_FROM_PHONE_KITKAT&&resultCode==RESULT_OK){//获取照片地址获取到图片（相册）4.4系统，用谷歌的相册
			if(data != null && data.getData() != null) {
				imageUtils.doPhotoKIKAT(data);
				if(imageUtils.PICPATH!=null){
					saveImage();
				}
			}
		}
		if(dialog!=null){
			if(dialog.isShowing()){
				dialog.dismiss();
			}
		}
	}
	
	private void saveImage() {
		// TODO Auto-generated method stub
		switch (imagepath) {
		case 1://机构
			if(pathAgency!=null){
				FileUtils.getInstance().deleteFile(pathAgency);
			}
			pathAgency=FileUtils.getInstance().saveUpImageForCamera(imageUtils.PICPATH);
			BitmapsUtils.getInstance().display(imageview1, pathAgency);
			break;
		case 2://个人
			if(pathPersonal!=null){
				FileUtils.getInstance().deleteFile(pathPersonal);
			}
			pathPersonal=FileUtils.getInstance().saveUpImageForCamera(imageUtils.PICPATH);
			BitmapsUtils.getInstance().display(imageview2, pathPersonal);
			break;
		default:
			break;
		}
	};
	
	private onBasicView<String> linstener=new onBasicView<String>() {
		
		@Override
		public void onSucess(String data) {
			// TODO Auto-generated method stub
			if(loading!=null){
				loading.dismiss();
			}
			setResult(RESULT_OK, getIntent());
			finish();
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			if(loading!=null){
				loading.dismiss();
			}
			new ToastUtils(AttestAgencyActivity.this, errorMsg);
		}
	};
}
