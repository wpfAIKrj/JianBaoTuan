package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_activity://返回主界面
			
			
			break;
		case R.id.login_bt_clear://显示下拉用户框
			
			break;
		case R.id.login_bt_login://注册
			
			break;
		case R.id.login_bt_register://跳转到注册页面
			
			break;
		default:
			break;
		}
	}
}
