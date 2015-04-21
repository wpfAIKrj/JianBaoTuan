package com.it.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.it.R;
import com.it.ui.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_activity://返回登陆页面
			
			
			break;
		case R.id.register_button://注册
			
			break;
		case R.id.register_send_code://发送短信
			break;
		default:
			break;
		}
	}
	
	
	
}
