package com.it.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.it.R;
import com.it.ui.base.BaseActivity;

public class ForgotActivity extends BaseActivity implements OnClickListener{

	private ImageView iv_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
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
		case R.id.back_activity://返回登陆页面
			setResult(RESULT_CANCELED, getIntent());
			finish();
			
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
