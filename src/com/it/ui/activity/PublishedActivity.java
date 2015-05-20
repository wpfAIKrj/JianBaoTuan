package com.it.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.config.Const;
import com.it.ui.base.BaseActivity;
import com.it.ui.dialog.SelectPhotoDialog;
import com.it.utils.BitmapsUtils;
import com.it.utils.FileUtils;
import com.it.utils.ImageUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 发布藏品
 * @author Administrator
 *
 */
public class PublishedActivity extends BaseActivity{

	private SelectPhotoDialog dialog;
	
	
	@ViewInject(R.id.published_bt)
	private ImageView tv_munu;
	
	@ViewInject(R.id.tv_tag1)
	private TextView tv_tag1;
	
	@ViewInject(R.id.tv_tag2)
	private TextView tv_tag2;
	
	@ViewInject(R.id.tv_tag3)
	private TextView tv_tag3;
	
	@ViewInject(R.id.imageView01)
	private ImageView iv1;
	@ViewInject(R.id.imageView02)
	private ImageView iv2;
	@ViewInject(R.id.imageView03)
	private ImageView iv3;
	@ViewInject(R.id.imageView04)
	private ImageView iv4;
	@ViewInject(R.id.imageView05)
	private ImageView iv5;
	@ViewInject(R.id.imageView06)
	private ImageView iv6;
	
	@ViewInject(R.id.home_title)
	private TextView title;
	
	private int getPhoto=-1;//没有选择图片时为-1
	private String[] imagePath=new String[6];//全景图片路径
	
	private ImageUtils imageUtils;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_published);
		ViewUtils.inject(this);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		imageUtils=new ImageUtils(this);
		title.setText(R.string.publish_title);
		
	}



	@OnClick({R.id.btn_back,R.id.published_bt,R.id.imageView01,R.id.imageView02,R.id.imageView03,R.id.imageView04
		,R.id.imageView05,R.id.imageView06,R.id.bt_next})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back://返回上层
			for (String path : imagePath) {
				if(path!=null&&!path.isEmpty()){
					FileUtils.getInstance().deleteFile(path);
				}
			}
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.published_bt://跳转到搜索
			
			break;
		case R.id.imageView01:
			showGetPhotoDialog(0);
			break;
		case R.id.imageView02:
			showGetPhotoDialog(1);
			break;
		case R.id.imageView03:
			showGetPhotoDialog(2);
			break;
		case R.id.imageView04:
			showGetPhotoDialog(3);
			break;
		case R.id.imageView05:
			showGetPhotoDialog(4);
			break;
		case R.id.imageView06:
			showGetPhotoDialog(5);
			break;
		case R.id.bt_next:
			Intent intent=new Intent(PublishedActivity.this, PublishedNextActivity.class);
			intent.putExtra(Const.IMAGEPATH, imagePath);
			startActivityForResult(intent, Const.TO_IDENTY_NEXT);
			break;
		default:
			break;
		}
	}
	
	//制定那个imageview获取图片
	private void showGetPhotoDialog(int number) {
		// TODO Auto-generated method stub
		getPhoto=number;
		if(dialog==null){
			dialog=new SelectPhotoDialog(this, ImageListner);
		}
		if(!dialog.isShowing()){
			dialog.show();
		}
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==Const.TO_IDENTY_NEXT){
			if(resultCode==RESULT_OK){//鉴定发布成功，返回主页面
				
			}
			if(resultCode==RESULT_CANCELED){//鉴赏失败
				
			}
		}
		if(requestCode==ImageUtils.GET_IMAGE_BY_CAMERA&&resultCode==RESULT_OK){//我的页面，获取照片地址获取到图片（相机）
			imageUtils.doPhotoCamera();
			if(imageUtils.PICPATH!=null){
				saveImage();
			}
		}
		if(requestCode==ImageUtils.GET_IMAGE_FROM_PHONE&&resultCode==Activity.RESULT_OK){//我的页面，获取照片地址获取到图片（相册）
			if(data != null && data.getData() != null) {
				imageUtils.doPhoto( data);
				if(imageUtils.PICPATH!=null){
					saveImage();
				}
			}
		}
		if(requestCode==ImageUtils.GET_IMAGE_FROM_PHONE_KITKAT&&resultCode==RESULT_OK){//我的页面，获取照片地址获取到图片（相册）4.4系统，用谷歌的相册
			if(data != null && data.getData() != null) {
				imageUtils.doPhotoKIKAT(data);
				if(imageUtils.PICPATH!=null){
					saveImage();
				}
			}
		}
		dismissGetPhotoDialog();
	}
	
	
	private void dismissGetPhotoDialog() {
		// TODO Auto-generated method stub
		getPhoto=-1;
		if(dialog!=null){
			if(dialog.isShowing()){
				dialog.dismiss();
			}
		}
	}



	private void saveImage() {
		// TODO Auto-generated method stub
		String path=imagePath[getPhoto];
		if(path!=null&&!path.isEmpty()){
			FileUtils.getInstance().deleteFile(path);
		}
		imagePath[getPhoto]=FileUtils.getInstance().saveUpImageForCamera(imageUtils.PICPATH);
		switch (getPhoto) {
		case 0:
			BitmapsUtils.getInstance().display(iv1, imagePath[getPhoto]);
			break;
		case 1:
			BitmapsUtils.getInstance().display(iv2, imagePath[getPhoto]);
			break;
		case 2:
			BitmapsUtils.getInstance().display(iv3, imagePath[getPhoto]);
			break;
		case 3:
			BitmapsUtils.getInstance().display(iv4, imagePath[getPhoto]);
			break;
		case 4:
			BitmapsUtils.getInstance().display(iv5, imagePath[getPhoto]);
			break;
		case 5:
			BitmapsUtils.getInstance().display(iv6, imagePath[getPhoto]);
			break;
		default:
			break;
		}
	}
	private OnClickListener ImageListner = new OnClickListener() {
			
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
					if(dialog!=null){
						dialog.dismiss();
					}
					break;
				default:
					break;
				}
			}
		};
}
