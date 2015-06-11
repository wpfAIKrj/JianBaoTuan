package com.yingluo.Appraiser.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.ui.base.BaseActivity;
/**
 * 找回密码
 * @author Administrator
 *
 */
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
		iv_back=(ImageView)findViewById(R.id.title_back);
		iv_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_back://返回登陆页面
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
