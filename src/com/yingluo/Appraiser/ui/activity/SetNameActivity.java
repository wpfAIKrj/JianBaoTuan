package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import cn.smssdk.SMSSDK;
import de.greenrobot.event.EventBus;

import java.io.File;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.MyEvent;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.ForgetPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.dialog.SelectPhotoDialog;
import com.yingluo.Appraiser.utils.ActivityTaskManager;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ImageUtils;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;

/**
 * 设置昵称和头像
 * 
 * @author Administrator
 *
 */
public class SetNameActivity extends BaseActivity {

	@ViewInject(R.id.login_user_head)
	private CircleImageView head;

	@ViewInject(R.id.et_nickname)
	private EditText nickName;
	
	private ForgetPresenter presenter;

	private SelectPhotoDialog photodialog;
	
	private ImageUtils imageUtils;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setname);
		ViewUtils.inject(this);
		SMSSDK.initSDK(this, NetConst.SMS_KEY, NetConst.SMS_SECRET);
		presenter = new ForgetPresenter(iview);
		imageUtils = new ImageUtils(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@OnClick({R.id.tv_tiao,R.id.bt_yes,R.id.login_user_head})
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_tiao:
			//跳过
			onBackPressed();
			break;
		case R.id.bt_yes:
			//确定
//			presenter
			onBackPressed();
			break;
		case R.id.login_user_head:
			if (photodialog == null) {
				photodialog = new SelectPhotoDialog(SetNameActivity.this, ImageListner);
			}
			if (!photodialog.isShowing()) {
				photodialog.show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ActivityTaskManager.getInstance().getActivity("com.yingluo.Appraiser.ui.activity.LoginAcitivity").finish();
		ActivityTaskManager.getInstance().getActivity("com.yingluo.Appraiser.ui.activity.RegisterActivity").finish();
		overridePendingTransition(R.anim.hold, R.anim.toast_out);
	}
	
	private OnClickListener ImageListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_take_photo:// 相机获取
				imageUtils.openCameraImage();
				break;
			case R.id.btn_pick_photo:// 相册获取
				imageUtils.openLocalImage();
				break;
			case R.id.btn_cancel:// 取消
				if (photodialog != null) {
					photodialog.dismiss();
				}
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		
		if (arg0 == ImageUtils.GET_IMAGE_BY_CAMERA && arg1 == RESULT_OK) {// 我的页面，获取照片地址获取到图片（相机）
			if (imageUtils.PICPATH != null) {
				imageUtils.crop(Uri.fromFile((new File(imageUtils.PICPATH))));
			}
		}
		if (arg0 == ImageUtils.GET_IMAGE_FROM_PHONE && arg1 == Activity.RESULT_OK) {// 我的页面，获取照片地址获取到图片（相册）
			if (arg2 != null && arg2.getData() != null) {
				imageUtils.crop(arg2.getData());
			}
		}
		if (arg0 == ImageUtils.GET_IMAGE_FROM_PHONE_KITKAT && arg1 == RESULT_OK) {// 我的页面，获取照片地址获取到图片（相册）4.4系统，用谷歌的相册
			if (arg2 != null && arg2.getData() != null) {
				imageUtils.doPhotoKIKAT(arg2);
				imageUtils.crop(Uri.fromFile(new File(imageUtils.PICPATH)));

			}
		}
		if (arg0 == ImageUtils.PHOTO_REQUEST_CUT && arg1 == RESULT_OK) {// 获取剪切好的人物头像
			if (arg2 != null) {

				uploadLogo(FileUtils.getInstance().getLogoPath().getAbsolutePath(),arg2);
			}

		}
		disshowPhoto();

	}
	
	public void uploadLogo(String path,Intent data) {
		LogUtils.d("获取的头像路径：" + path);
		disshowPhoto();
		Bundle extras = data.getExtras();
        if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                Drawable drawable = new BitmapDrawable(photo);
                head.setImageDrawable(drawable);
        }
	}

	private void disshowPhoto() {
		// TODO Auto-generated method stub
		if (photodialog != null) {
			if (photodialog.isShowing()) {
				photodialog.dismiss();
			}
		}
	}
	
	private onBasicView<UserInfo> iview = new onBasicView<UserInfo>() {

		@Override
		public void onSucess(UserInfo data) {
			setResult(RESULT_OK, getIntent());
			finish();
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			new ToastUtils(SetNameActivity.this, errorMsg);
		}
	};

}
