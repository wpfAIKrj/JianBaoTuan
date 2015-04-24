package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.it.R;
import com.it.ui.base.BaseActivity;

public class LoginAcitivity extends BaseActivity implements OnClickListener{

	private ImageView iv_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		iv_back=(ImageView)findViewById(R.id.back_activity);
		iv_back.setOnClickListener(this);
		Button bt=(Button)findViewById(R.id.login_bt_clear);
		bt.setOnClickListener(this);
		bt=(Button)findViewById(R.id.login_bt_forgot);
		bt.setOnClickListener(this);
		bt=(Button)findViewById(R.id.login_bt_login);
		bt.setOnClickListener(this);
		bt=(Button)findViewById(R.id.login_bt_register);
		bt.setOnClickListener(this);
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=null;
		switch (v.getId()) {
		case R.id.back_activity://返回主界面
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
}
