package com.it.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;

/**
 * 认证鉴定师页面
 * @author Administrator
 *
 */
public class AuthenticateActivity extends BaseActivity implements OnClickListener{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authenticate);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		ImageView bt=(ImageView)findViewById(R.id.button_category);
		bt.setOnClickListener(this);
		TextView tv=(TextView)findViewById(R.id.home_title);
		tv.setText(R.string.authenticate_title);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;
		default:
			break;
		}
	}
}
