package com.it.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.it.R;

public class ActivityIdentifyByMe extends Activity {

	View btn_delete, btn_back, btn_publish;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_indentify_by_me);
		title = (TextView) findViewById(R.id.tv_title);
		title.setText("我来鉴定");
		btn_delete = findViewById(R.id.btn_delete);
		btn_delete.setVisibility(View.GONE);
		btn_back = findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		btn_publish = findViewById(R.id.btn_publish);
		btn_publish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ActivityIdentifyByMe.this,
						PublishedActivity.class));
			}
		});
	}

}
