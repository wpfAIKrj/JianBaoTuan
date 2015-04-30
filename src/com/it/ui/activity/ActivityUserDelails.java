package com.it.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.it.R;

public class ActivityUserDelails extends Activity {

	private View detail_back, btn_goto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_delails);
		detail_back = findViewById(R.id.detail_back);
		detail_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		btn_goto = findViewById(R.id.btn_goto);
		btn_goto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ActivityUserDelails.this,
						ActivityIdentifyByMe.class));
			}
		});
	}

}
