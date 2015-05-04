package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.it.R;
import com.it.bean.UserInfo;
import com.it.presenter.LoginPresenter;
import com.it.ui.base.BaseActivity;
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
			
			break;
		case R.id.login_bt_login://登陆
			
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
	
	

	@Override
	public void loginSucess(UserInfo user) {//登陆成功
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void loginFail(String errorCode, String errorMsg) {//登陆失败
		// TODO Auto-generated method stub
		
	}
}
