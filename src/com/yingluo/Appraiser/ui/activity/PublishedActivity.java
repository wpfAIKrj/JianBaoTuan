package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;

import android.R.array;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.dialog.SelectPhotoDialog;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ImageUtils;
import com.yingluo.Appraiser.utils.NetUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.TagLinearLayout;
/**
 * 发布藏品
 * @author Administrator
 *
 */
public class PublishedActivity extends BaseActivity{

	private SelectPhotoDialog dialog;
	
	
	@ViewInject(R.id.published_bt)
	private ImageView tv_munu;
	
	@ViewInject(R.id.tag_layout)
	private TagLinearLayout taglayout;
	
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
	private String[] imageAll=new String[3];//全景图片路径
	private String[] imageTest=new String[3];//特写图片路径
	
	private ImageUtils imageUtils;
	
	private TreasureType type=null;//宝贝分类
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
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_back://返回上层
			for (String path : imageAll) {
				if(path!=null&&!path.isEmpty()){
					FileUtils.getInstance().deleteFile(path);
				}
			}
			for (String path : imageTest) {
				if(path!=null&&!path.isEmpty()){
					FileUtils.getInstance().deleteFile(path);
				}
			}
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.published_bt://跳转到搜索
			 intent=new Intent(PublishedActivity.this, ActivityKindOfPrecious.class);
			startActivityForResult(intent, Const.TO_PUBLISH_SELECT_TYPE);
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
		case R.id.bt_next://下一步
				if(type!=null){
					ArrayList<String> alllist=new ArrayList<String>();
					for (int i = 0; i < imageAll.length; i++) {
						String path = imageAll[i];
						if(path!=null){
							alllist.add(path);
						}
					}
					if(alllist.size()==0){
						new ToastUtils(this, R.string.help_msg_14);
						return;
					}
					ArrayList<String> testlist=new ArrayList<String>();
					for (int i = 0; i < imageTest.length; i++) {
						String path = imageTest[i];
						if(path!=null){
							testlist.add(path);
						}
					}
					if(testlist.size()==0){
						new ToastUtils(this, R.string.help_msg_15);
						return;
					}
					 intent=new Intent(PublishedActivity.this, PublishedNextActivity.class);
						intent.putExtra(Const.IMAGEPATH_PANORAMIC, alllist);
						intent.putExtra(Const.IMAGEPATH_FEATURE, testlist);
						intent.putExtra(Const.KIND_ID, type);
						startActivityForResult(intent, Const.TO_IDENTY_NEXT);	
				}else{
					new ToastUtils(this, R.string.help_msg_12);
				}
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
		if(requestCode==Const.TO_PUBLISH_SELECT_TYPE){//选择宝物
			if(resultCode==RESULT_OK){
				int kind_id=data.getIntExtra(Const.KIND_ID, 0);
				if(kind_id==0){
					type=null;
				}else{
					type=SqlDataUtil.getInstance().getTreasureTypeById(kind_id);
					taglayout.addTag(type);	
				}
			}
			
		}
		if(requestCode==Const.TO_IDENTY_NEXT){
			if(resultCode==RESULT_OK){//鉴定发布成功，返回主页面
				setResult(RESULT_OK, getIntent());
				finish();
			}
			if(resultCode==(-2)){//鉴赏失败
				new ToastUtils(PublishedActivity.this, "发布鉴定失败！");
			}
		}
		if(requestCode==ImageUtils.GET_IMAGE_BY_CAMERA&&resultCode==RESULT_OK){//我的页面，获取照片地址获取到图片（相机）
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
		String path=null;
		if(getPhoto>=3){
			path=imageTest[getPhoto-3];
			if(path!=null&&!path.isEmpty()){
				FileUtils.getInstance().deleteFile(path);
			}
			imageTest[getPhoto-3]=FileUtils.getInstance().saveUpImageForCamera(imageUtils.PICPATH);

		}else{
			path=imageAll[getPhoto];
			if(path!=null&&!path.isEmpty()){
				FileUtils.getInstance().deleteFile(path);
			}
			imageAll[getPhoto]=FileUtils.getInstance().saveUpImageForCamera(imageUtils.PICPATH);

		}
		switch (getPhoto) {
		case 0:
			BitmapsUtils.getInstance().display(iv1, imageAll[getPhoto]);
			break;
		case 1:
			BitmapsUtils.getInstance().display(iv2, imageAll[getPhoto]);
			break;
		case 2:
			BitmapsUtils.getInstance().display(iv3, imageAll[getPhoto]);
			break;
		case 3:
			BitmapsUtils.getInstance().display(iv4, imageTest[getPhoto-3]);
			break;
		case 4:
			BitmapsUtils.getInstance().display(iv5, imageTest[getPhoto-3]);
			break;
		case 5:
			BitmapsUtils.getInstance().display(iv6, imageTest[getPhoto-3]);
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
