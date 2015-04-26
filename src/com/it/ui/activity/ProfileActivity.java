package com.it.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;

public class ProfileActivity extends BaseActivity implements OnClickListener{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		ImageView bt=(ImageView)findViewById(R.id.button_category);
		bt.setBackgroundResource(R.drawable.back_botton);
		bt.setOnClickListener(this);
		TextView tv=(TextView)findViewById(R.id.home_title);
		tv.setText(R.string.profile_title);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;

		default:
			break;
		}
	}
	
	
	
}
