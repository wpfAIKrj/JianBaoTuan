package com.yingluo.Appraiser.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import cn.smssdk.SMSSDK;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.ForgetPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setname);
		ViewUtils.inject(this);
		SMSSDK.initSDK(this, NetConst.SMS_KEY, NetConst.SMS_SECRET);
		presenter = new ForgetPresenter(iview);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@OnClick({R.id.tv_tiao,R.id.bt_yes})
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_tiao:
			//跳过
			break;
		case R.id.bt_yes:
			//确定
//			presenter
			break;
		default:
			break;
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
