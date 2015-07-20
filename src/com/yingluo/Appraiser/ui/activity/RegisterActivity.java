package com.yingluo.Appraiser.ui.activity;

import io.rong.imkit.RongIM;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.DataUsageFeedback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.presenter.RegisterPresenter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.dialog.RegisterDialog;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.TelNumMath;
import com.yingluo.Appraiser.utils.ToastUtils;

/**
 * 注册页面
 * 
 * @author Administrator
 *
 */
public class RegisterActivity extends BaseActivity implements onBasicView<UserInfo> {

	@ViewInject(R.id.register_edit_phone)
	private EditText ed_phone;

	@ViewInject(R.id.register_edit_code)
	private EditText ed_code;

	@ViewInject(R.id.register_edit_password)
	private EditText ed_pwd;

	@ViewInject(R.id.register_send_code)
	private Button send_code;

	private RegisterPresenter mpresenter;

	private static final int TIME_COUNT = 61000;// 时间防止从119s开始显示（以倒计时120s为例子）
	private static final int TIME_LONG = 1000;

	private CountDownTimer timer;

	private String send_help;
	private Handler mhandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				reSendSMS();
				break;
			case 1:
				register();
				break;
			case 2:
				new ToastUtils(RegisterActivity.this, msg.obj.toString());
				break;
			default:
				break;
			}
		}
	};

	private String phone;

	private String code;

	private String pwd;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ViewUtils.inject(this);
		mpresenter = new RegisterPresenter(this);
		SMSSDK.initSDK(this, NetConst.SMS_KEY, NetConst.SMS_SECRET);
		SMSSDK.registerEventHandler(eh); // 注册短信回调
		send_help = getString(R.string.register_code_bt_text);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		SMSSDK.unregisterEventHandler(eh);
		super.onDestroy();
	}

	@OnClick({ R.id.register_button, R.id.register_send_code, R.id.title_back })
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_back:// 返回登陆页面
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.register_button:// 注册(1先验证验证码，2然后才是提交服务器后台)
			checkSms();
			break;
		case R.id.register_send_code:// 发送短信
			sendSms();
			break;
		default:
			break;
		}
	}

	/**
	 * 验证手机码是否正确
	 */
	private void checkSms() {
		// TODO Auto-generated method stub
		phone = ed_phone.getText().toString();
		if (TelNumMath.isMobileNO(phone)) {
			pwd = ed_pwd.getText().toString().trim();
			if (!pwd.isEmpty() && pwd.length() > 5) {
				code = ed_code.getText().toString().trim();
				if (code.length() == 4) {
					SMSSDK.submitVerificationCode("86", phone, code);
				} else {
					new ToastUtils(this, "请输入正确的验证码");
				}
			} else {
				ed_pwd.setText("");
				new ToastUtils(this, "密码格式不正确");
			}
		} else {
			ed_phone.setText("");
			ed_pwd.setText("");
			new ToastUtils(this, "手机号码不正确");
		}
	}

	/**
	 * 发送短信
	 */
	private void sendSms() {
		// TODO Auto-generated method stub
		phone = ed_phone.getText().toString();
		if (TelNumMath.isMobileNO(phone)) {
			SMSSDK.getVerificationCode("86", phone);
		} else {
			ed_phone.setText("");
			ed_pwd.setText("");
			new ToastUtils(this, "手机号码不正确");
		}
	}

	protected void register() {
		// TODO Auto-generated method stub
		dialog = DialogUtil.createLoadingDialog(this, "注册中.....");
		dialog.show();
		mpresenter.startRegister(phone, pwd);
	}

	private EventHandler eh = new EventHandler() {

		@Override
		public void afterEvent(int event, int result, Object data) {

			if (result == SMSSDK.RESULT_COMPLETE) {
				// 回调完成
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
					// 提交验证码成功
					HashMap<String, String> hash = (HashMap<String, String>) data;
					mhandler.obtainMessage(1).sendToTarget();
				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					// 获取验证码成功
					if (data == null) {
						mhandler.obtainMessage(0).sendToTarget();
					}
				} else {
					mhandler.obtainMessage(2, "验证失败，请重新获取验证码").sendToTarget();
				}

			}
			if (result == 0) {
				if (data instanceof Throwable) {
					Throwable msg = (Throwable) data;
					mhandler.obtainMessage(2, "验证失败，请重新获取验证码").sendToTarget();
				}
				if (data instanceof UnknownHostException) {
					mhandler.obtainMessage(2, "请打开网络").sendToTarget();

				}
			}
		}
	};

	private RegisterDialog registerdialog;

	/**
	 * 倒计时
	 */
	protected void reSendSMS() {
		// TODO Auto-generated method stub
		System.out.println("获取验证码成功");
		send_code.setEnabled(false);
		timer = new CountDownTimer(TIME_COUNT, TIME_LONG) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				send_code.setEnabled(false);
				String s = String.format(send_help, (millisUntilFinished / 1000));
				System.out.println(s);
				send_code.setText(s);
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				send_code.setEnabled(true);
				send_code.setText(R.string.register_code_bt_hint);
			}
		};
		timer.start();
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(R.anim.left_in, R.anim.left_out);
	}

	@Override
	public void onSucess(UserInfo user) {
		// TODO Auto-generated method stub
		if (dialog != null) {
			dialog.dismiss();
		}
		ItApplication.setCurrnUser(user);
		SqlDataUtil.getInstance().saveUserInfo(user);
		SharedPreferencesUtils.getInstance().saveForIsLogin(true);
		SharedPreferencesUtils.getInstance().saveLoginUserName(user.getMobile());
		registerdialog = new RegisterDialog(this);
		registerdialog.show();
		mhandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (registerdialog != null) {
					registerdialog.dismiss();
				}
				setResult(Activity.RESULT_OK, getIntent());
				finish();
				overridePendingTransition(R.anim.right_in, R.anim.right_out);
			}
		}, 5000);
	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		if (dialog != null) {
			dialog.dismiss();
		}
		new ToastUtils(this, errorCode + "," + errorMsg);
	}
}
