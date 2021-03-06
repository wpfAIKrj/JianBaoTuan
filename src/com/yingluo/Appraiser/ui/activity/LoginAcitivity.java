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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

	private LoginPresenter mpresenter;
	private Dialog dialog;

	@ViewInject(R.id.login_edit_name)
	private EditText ed_name;
	@ViewInject(R.id.login_edit_password)
	private EditText ed_pwd;

	@ViewInject(R.id.iv_rember_password)
	private ImageView ivPassword;
	@ViewInject(R.id.iv_rember_state)
	private ImageView ivState;
	
	private boolean isChoosePassword,isChooseState;
	private boolean isShow = false;

	private SelectMoilbWindow popwindow;

	@ViewInject(R.id.rl_login_edit_name)
	private RelativeLayout namelayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ViewUtils.inject(this);
		mpresenter = new LoginPresenter(this);
		isChoosePassword=isChooseState=true;
		
		List<UserInfo> list = SqlDataUtil.getInstance().getUserList();
		if (list == null) {
			isShow = true;
		}
		popwindow = new SelectMoilbWindow(this, this);
		ed_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {  
		      
		    @Override  
		    public void onFocusChange(View v, boolean hasFocus) {  
		        if(hasFocus){
		        	//获得焦点 
		        	String phone = ed_name.getText().toString();
		        	if(phone != null && phone.length()!=0) {
		        		change(phone);
		        	}
		        }else{
		        	//失去焦点  
		        }  
		    }             
		});
	}

	@OnClick({R.id.ll_rember_password,R.id.ll_rember_state}) 
	public void clickView(View view) {
		switch(view.getId()) {
		case R.id.ll_rember_password:
			if(isChoosePassword) {
				isChoosePassword = false;
				ivPassword.setImageResource(R.drawable.choose_no);
			} else {
				isChoosePassword = true;
				ivPassword.setImageResource(R.drawable.choose_yes);
			}
			break;
		case R.id.ll_rember_state:
			if(isChooseState) {
				isChooseState = false;
				ivState.setImageResource(R.drawable.choose_no);
			} else {
				isChooseState = true;
				ivState.setImageResource(R.drawable.choose_yes);
			}
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.hold, R.anim.toast_out);
	}

	@OnClick({ R.id.title_back, R.id.login_bt_login,R.id.login_bt_forgot,R.id.iv_edit_name })
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.title_back:// 返回主界面
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.hold, R.anim.toast_out);
			break;
		case R.id.iv_edit_name:// 显示下拉用户框
			if (!isShow) {
				showPopWinddos();
			} else {
				disPopWinddos();
			}
			break;
		case R.id.login_bt_login:// 登陆
			startLogin();
			break;
//			intent = new Intent(LoginAcitivity.this, RegisterActivity.class);
//			startActivity(intent);
//			overridePendingTransition(R.anim.left_in, R.anim.left_out);
		case R.id.login_bt_forgot:// 找回密码界面
			intent = new Intent(LoginAcitivity.this, ForgotActivity.class);
			startActivityForResult(intent, Const.TO_FOGOT);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		default:
			break;
		}
	}

	/**
	 * 隐藏下拉框
	 */
	private void disPopWinddos() {
		isShow = false;
		popwindow.showPopupWindow(namelayout);
	}

	/**
	 * 显示本地数据库中的用户
	 */
	private void showPopWinddos() {
		isShow = true;
		popwindow.showPopupWindow(namelayout);
	}

	/**
	 * 登陆
	 */
	private void startLogin() {
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
		if (user == null) {
			return;
		}
		new ToastUtils(this,"登陆成功");
		String name = ed_name.getText().toString();
		String pas = ed_pwd.getText().toString();
		
		if(SqlDataUtil.getInstance().getUserForPhone(name) == null) {
			SqlDataUtil.getInstance().saveUserInfo(user);
		}
		ItApplication.setCurrnUser(user);
		SharedPreferencesUtils.getInstance().saveLoginUserName(name);
		SharedPreferencesUtils.getInstance().saveLoginUserID(user.getId());
		
		// 保存密码，不知道有用没有，看需求吧
		if(isChoosePassword) {
			SharedPreferencesUtils.getInstance().saveLoginUserPassword(name+"1", pas);
		} else {
			SharedPreferencesUtils.getInstance().saveLoginUserPassword(name+"1", null);
		}
		SharedPreferencesUtils.getInstance().saveForIsLoginSave(name, isChooseState);
		
		if (dialog != null) {
			dialog.dismiss();
		}
		setResult(Activity.RESULT_OK, getIntent());
		finish();
		overridePendingTransition(R.anim.hold, R.anim.toast_out);
	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		if (dialog != null) {
			dialog.dismiss();
		}
		new ToastUtils(this,errorMsg);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		UserInfo user = popwindow.getUserInfo(position);
		String phone = user.getMobile();
		if(phone == null) {
			return;
		}
		ed_name.setText(phone);
		ed_name.setSelection(phone.length());
		change(phone);
		disPopWinddos();
	}

	public void change(String phone) {
		if(SharedPreferencesUtils.getInstance().getIsHaveLoginSave(phone)) {
			ivState.setImageResource(R.drawable.choose_yes);
			isChooseState = true;
		} else {
			ivState.setImageResource(R.drawable.choose_no);
			isChooseState = false;
		}
		
		String pas = SharedPreferencesUtils.getInstance().getLoginUserPassword(phone+"1");
		if(pas != null) {
			ed_pwd.setText(pas);
			ed_pwd.setSelection(pas.length());
			isChoosePassword = true;
			ivPassword.setImageResource(R.drawable.choose_yes);
		} else {
			isChoosePassword = false;
			ivPassword.setImageResource(R.drawable.choose_no);
		}
	}
	
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_REGISTER) {
//			if (resultCode == RESULT_OK) {// 注册成功
//				setResult(Activity.RESULT_OK, getIntent());
//				finish();
//				overridePendingTransition(R.anim.right_in, R.anim.right_out);
//			}
		}
		if (requestCode == Const.TO_FOGOT && resultCode == RESULT_OK) {
			new ToastUtils(LoginAcitivity.this, "找回密码成功！");
		}

	}
}
