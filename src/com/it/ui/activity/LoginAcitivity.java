package com.it.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.UserInfo;
import com.it.presenter.LoginPresenter;
import com.it.ui.base.BaseActivity;
import com.it.utils.DataUtil;
import com.it.utils.DialogUtil;
import com.it.utils.TelNumMath;
import com.it.utils.ToastUtils;
import com.it.view.CircleImageView;
import com.it.view.inter.LoginView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnChildClick;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 登陆
 * @author Administrator
 *
 */
public class LoginAcitivity extends BaseActivity implements LoginView{

	
	@ViewInject(R.id.login_user_head)
	private CircleImageView user_logo;
	

	private LoginPresenter mpresenter;
	private Dialog dialog;
	
	@ViewInject(R.id.login_edit_name)
	private EditText ed_name;
	@ViewInject(R.id.login_edit_password)
	private EditText ed_pwd;
	
	private boolean isShow=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ViewUtils.inject(this);
		mpresenter=new LoginPresenter(this);
		
	}


	
	
	
	@OnClick({R.id.title_back, R.id.login_bt_clear,R.id.login_bt_login,R.id.login_bt_register,R.id.login_bt_forgot})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=null;
		switch (v.getId()) {
		case R.id.title_back://返回主界面
			intent=new Intent(LoginAcitivity.this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			break;
		case R.id.login_bt_clear://显示下拉用户框
			if(isShow){
				showPopWinddos();
			}else{
				disPopWinddos();
			}
			break;
		case R.id.login_bt_login://登陆
			startLogin();
			break;
		case R.id.login_bt_register://跳转到注册页面
			startActivity(new Intent(LoginAcitivity.this, RegisterActivity.class));
			
			break;
		case R.id.login_bt_forgot://找回密码界面
			startActivity(new Intent(LoginAcitivity.this, ForgotActivity.class));
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
		
	}




	/**
	 * 显示本地数据库中的用户
	 */
	private void showPopWinddos() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * 登陆
	 */
	private void startLogin() {
		// TODO Auto-generated method stub
		final String name=ed_name.getText().toString();
		final String pwd=ed_pwd.getText().toString();
		 if(TelNumMath.isMobileNO(name)){
			 if(!pwd.isEmpty()&&pwd.length()>5){
				 dialog=DialogUtil.createLoadingDialog(this, "登陆中.....");
				 dialog.show();
				 mpresenter.startLogin(name, pwd);
			 }else{
				 ed_pwd.setText("");
				 new ToastUtils(this, "密码不正确，请重新输入");
			 }
		 }else{
			 ed_name.setText("");
			 ed_pwd.setText("");
			 new ToastUtils(this, "手机号码不正确");
		 }
	}





	@Override
	public void loginSucess(UserInfo user) {//登陆成功
		// TODO Auto-generated method stub
		if(dialog!=null){
			dialog.dismiss();
		}
		((ItApplication)getApplication()).setCurrnUser(user);
		DataUtil.getInstance(getApplicationContext()).saveUserInfo(user);
		
		Intent intent=new Intent(LoginAcitivity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		finish();
	}

	
	@Override
	public void loginFail(String errorCode, String errorMsg) {//登陆失败
		// TODO Auto-generated method stub
		if(dialog!=null){
			dialog.dismiss();
		}
		new ToastUtils(this, errorCode+","+errorMsg);
	}
}
