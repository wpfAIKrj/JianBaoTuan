package com.yingluo.Appraiser.ui.activity;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnChildClick;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.LoginPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.TelNumMath;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;
import com.yingluo.Appraiser.view.SelectMoilbWindow;

/**
 * 登陆
 * 
 * @author Administrator
 *
 */
public class LoginAcitivity extends BaseActivity implements onBasicView<UserInfo>, OnItemClickListener {

	@ViewInject(R.id.login_user_head)
	private CircleImageView user_logo;

	private LoginPresenter mpresenter;
	private Dialog dialog;

	@ViewInject(R.id.login_edit_name)
	private EditText ed_name;
	@ViewInject(R.id.login_edit_password)
	private EditText ed_pwd;

	private boolean isShow = false;

	private SelectMoilbWindow popwindow;

	@ViewInject(R.id.name_layout)
	private LinearLayout namelayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ViewUtils.inject(this);
		mpresenter = new LoginPresenter(this);
		List<UserInfo> list = SqlDataUtil.getInstance().getUserList();
		if (list == null) {
			isShow = true;
		}
		popwindow = new SelectMoilbWindow(this, this);

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	@OnClick({ R.id.title_back, R.id.login_bt_clear, R.id.login_bt_login, R.id.login_bt_register,
			R.id.login_bt_forgot })
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.title_back:// 返回主界面
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.login_bt_clear:// 显示下拉用户框
			if (!isShow) {
				showPopWinddos();
			} else {
				disPopWinddos();
			}
			break;
		case R.id.login_bt_login:// 登陆
			startLogin();
			break;
		case R.id.login_bt_register:// 跳转到注册页面
			intent = new Intent(LoginAcitivity.this, RegisterActivity.class);
			startActivityForResult(intent, Const.TO_REGISTER);
			break;
		case R.id.login_bt_forgot:// 找回密码界面
			intent = new Intent(LoginAcitivity.this, ForgotActivity.class);
			startActivityForResult(intent, Const.TO_FOGOT);
			break;
		default:
			break;
		}
	}

	/**
	 * 隐藏下拉框
	 */
	private void disPopWinddos() {
		// TODO Auto-generated method stub
		isShow = false;
		popwindow.showPopupWindow(namelayout);
	}

	/**
	 * 显示本地数据库中的用户
	 */
	private void showPopWinddos() {
		// TODO Auto-generated method stub

		isShow = true;
		popwindow.showPopupWindow(namelayout);
	}

	/**
	 * 登陆
	 */
	private void startLogin() {
		// TODO Auto-generated method stub
		final String name = ed_name.getText().toString();
		final String pwd = ed_pwd.getText().toString();
		if (TelNumMath.isMobileNO(name)) {
			if (!pwd.isEmpty() && pwd.length() > 5) {
				dialog = DialogUtil.createLoadingDialog(this, "登录中...");
				dialog.show();
				mpresenter.startLogin(name, pwd);
			} else {
				ed_pwd.setText("");
				new ToastUtils(this, "密码不正确，请重新输入");
			}
		} else {
			ed_name.setText("");
			ed_pwd.setText("");
			new ToastUtils(this, "手机号码不正确");
		}
	}

	@Override
	public void onSucess(UserInfo user) {
		// TODO Auto-generated method stub
		SharedPreferencesUtils.getInstance().saveForIsLogin(true);
		SharedPreferencesUtils.getInstance().saveLoginUserName(user.getMobile());
		SqlDataUtil.getInstance().saveUserInfo(user);
		ItApplication.getcurrnUser();
		if (dialog != null) {
			dialog.dismiss();
		}
		setResult(Activity.RESULT_OK, getIntent());
		finish();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		if (dialog != null) {
			dialog.dismiss();
		}
		new ToastUtils(this, errorCode + "," + errorMsg);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		UserInfo user = popwindow.getUserInfo(position);
		ed_name.setText(user.getMobile());
		popwindow.showPopupWindow(namelayout);
		isShow = false;
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_REGISTER) {
			if (resultCode == RESULT_OK) {// 注册成功
				setResult(Activity.RESULT_OK, getIntent());
				finish();
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
			}
		}
		if (requestCode == Const.TO_FOGOT && resultCode == RESULT_OK) {
			new ToastUtils(LoginAcitivity.this, "找回密码成功！");
		}

	}
}
