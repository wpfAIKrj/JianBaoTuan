package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.http.AskNetWork;
import com.yingluo.Appraiser.http.ResponseToken;
import com.yingluo.Appraiser.http.AskNetWork.AskNetWorkCallBack;

import org.json.JSONException;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.AttestPersonalPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.dialog.SelectPhotoDialog;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ImageUtils;
import com.yingluo.Appraiser.utils.StringUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;

public class AttestPersionalActivity extends BaseActivity implements AskNetWorkCallBack{

	private SelectPhotoDialog dialog;

	@ViewInject(R.id.home_title)
	private TextView title;

	private ImageUtils imageUtils;

	private String path = null;
	private String name = null;
	private String number = null;

	@ViewInject(R.id.imageView01)
	private CircleImageView imageview;

	@ViewInject(R.id.et_number)
	private EditText et_number;

	@ViewInject(R.id.et_name)
	private EditText et_name;

	private Dialog loading;

	private AttestPersonalPresenter mypresenter;

	private AskNetWork askNet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attest_personal);
		ViewUtils.inject(this);
		askNet = new AskNetWork(NetConst.TOKEN,this);
		askNet.ask(HttpRequest.HttpMethod.GET,null);
		initView();
		initData();
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	private void initView() {
		title.setText(R.string.title_personal);
		imageUtils = new ImageUtils(this);
		mypresenter = new AttestPersonalPresenter(linstener);
	}

	private void initData() {

	}

	@OnClick({ R.id.btn_back, R.id.imageView01, R.id.bt_attest })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:// 返回上一层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.imageView01:// 弹出选择图片
			if (dialog == null) {
				dialog = new SelectPhotoDialog(this, ImageListner);
			}
			if (!dialog.isShowing()) {
				dialog.show();
			}
			break;
		case R.id.bt_attest:// 认证
			name = et_name.getText().toString();
			number = et_number.getText().toString();
			if (name != null && !name.isEmpty() && StringUtil.getIsChinesecharacter(name)) {
				if (number != null && !number.isEmpty() && StringUtil.personIdValidation(number)) {
					if (path != null) {
						startAttest();
					} else {
						new ToastUtils(this, "请选择身份证照片！");
					}
				} else {
					new ToastUtils(this, "请正确的身份证号码！");
				}
			} else {
				new ToastUtils(this, "请输入真实姓名！");
			}

			break;

		default:
			break;
		}
	}

	private void startAttest() {
		if (loading == null) {
			loading = DialogUtil.createLoadingDialog(this, "正在提交认证中...");
		}
		loading.show();
		mypresenter.uploadAttestPersonal(path, name, number);
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
				if (dialog != null) {
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
		if (requestCode == ImageUtils.GET_IMAGE_BY_CAMERA && resultCode == RESULT_OK) {// 获取照片地址获取到图片（相机）
			if (imageUtils.PICPATH != null) {
				saveImage();
			}
		}
		if (requestCode == ImageUtils.GET_IMAGE_FROM_PHONE && resultCode == Activity.RESULT_OK) {// 获取照片地址获取到图片（相册）
			if (data != null && data.getData() != null) {
				imageUtils.doPhoto(data);
				if (imageUtils.PICPATH != null) {
					saveImage();
				}
			}
		}
		if (requestCode == ImageUtils.GET_IMAGE_FROM_PHONE_KITKAT && resultCode == RESULT_OK) {// 获取照片地址获取到图片（相册）4.4系统，用谷歌的相册
			if (data != null && data.getData() != null) {
				imageUtils.doPhotoKIKAT(data);
				if (imageUtils.PICPATH != null) {
					saveImage();
				}
			}
		}
		if (dialog != null) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
	}

	@Override
	protected void onDestroy() {
		if (path != null) {
			FileUtils.getInstance().deleteFile(path);
		}
		super.onDestroy();
	}

	private void saveImage() {
		if (path != null) {
			FileUtils.getInstance().deleteFile(path);
		}
		path = FileUtils.getInstance().saveUpImageForCamera(imageUtils.PICPATH);
		BitmapsUtils.getInstance().display(imageview, path);
	};

	private onBasicView<String> linstener = new onBasicView<String>() {

		@Override
		public void onSucess(String data) {
			if (loading != null) {
				loading.dismiss();
			}
			setResult(RESULT_OK, getIntent());
			finish();
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			Toast.makeText(AttestPersionalActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
			if (loading != null) {
				loading.dismiss();
			}
			new ToastUtils(AttestPersionalActivity.this, errorMsg);
		}
	};

	@Override
	public void getNetWorkMsg(String msg, String param) throws JSONException {
		ResponseToken rt = new Gson().fromJson(msg, ResponseToken.class);
		if(rt.getCode()==100000) {
			Toast.makeText(this, "更新token"+rt.getData().getToken(), Toast.LENGTH_SHORT).show();
			NetConst.UPTOKEN= rt.getData().getToken();
		}
	}

	@Override
	public void getNetWorkMsgError(String msg, String param) throws JSONException {
		
	}
}
