package com.yingluo.Appraiser.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.updatePwdPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;

/**
 * 修改密码
 * 
 * @author Administrator
 *
 */
public class PasswordActivity extends BaseActivity {

	@ViewInject(R.id.home_title)
	private TextView title;

	@ViewInject(R.id.old_pwd)
	private EditText oldpwd;
	@ViewInject(R.id.new_pwd)
	private EditText newpwd;
	@ViewInject(R.id.new_pwd_02)
	private EditText repwd;

	private String oldpassword, newpassword, repassword;

	private updatePwdPresenter mypresenter;

	private Dialog loadding;

	private long TIME_STATE = 2000;
	private Handler mhandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		ViewUtils.inject(this);
		initView();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	private void initView() {
		title.setText(R.string.password_title);
		mypresenter = new updatePwdPresenter(lis);

	}

	@OnClick({ R.id.btn_back, R.id.pwd_save })
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:// 返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.pwd_save:// 修改密码
			oldpassword = oldpwd.getText().toString();
			if (oldpassword != null && !oldpassword.isEmpty()) {
				newpassword = newpwd.getText().toString();
				if (newpassword != null && !newpassword.isEmpty()) {
					repassword = repwd.getText().toString();
					if (repassword != null && !repassword.isEmpty()) {
						if (repassword.length() >= 6) {
							if (newpassword.equals(repassword)) {
								startUpdatePwd(oldpassword, newpassword);
							} else {
								repwd.setText("");
								newpwd.setText("");
								new ToastUtils(this, R.string.help_msg_07);
							}
						} else {
							new ToastUtils(this, R.string.help_msg_08);
						}
					} else {
						new ToastUtils(this, R.string.help_msg_06);
					}
				} else {
					new ToastUtils(this, R.string.help_msg_05);
				}
			} else {
				new ToastUtils(this, R.string.help_msg_04);
			}
			break;
		default:
			break;
		}
	}

	private void startUpdatePwd(String oldpassword, String newpassword) {
		// TODO 自动生成的方法存根
		if (loadding == null) {
			loadding = DialogUtil.createLoadingDialog(this, "更新密码...");
		}
		loadding.show();
		mypresenter.updatePassword(oldpassword, newpassword);
	}

	private onBasicView<UserInfo> lis = new onBasicView<UserInfo>() {

		@Override
		public void onSucess(UserInfo data) {
			// TODO 自动生成的方法存根
			if (loadding != null) {
				loadding.dismiss();
			}
			SqlDataUtil.getInstance().saveUserInfo(data);
			ItApplication.setCurrnUser(data);
			new ToastUtils(PasswordActivity.this, "修改密码成功！");
			mhandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					setResult(RESULT_OK, getIntent());
					finish();
					overridePendingTransition(R.anim.right_in, R.anim.right_out);
				}
			}, TIME_STATE);
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO 自动生成的方法存根
			if (loadding != null) {
				loadding.dismiss();
			}
			new ToastUtils(PasswordActivity.this, errorMsg);
		}
	};
}
