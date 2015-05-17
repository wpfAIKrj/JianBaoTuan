package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;

/**
 * 发布藏品的第二步
 * @author Administrator
 *
 */
public class PublishedNextActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publishednext);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		ImageView bt=(ImageView)findViewById(R.id.button_category);
		bt.setBackgroundResource(R.drawable.back_botton);
		bt.setOnClickListener(this);
		TextView tv=(TextView)findViewById(R.id.home_title);
		tv.setText(R.string.publish_title);
		Button b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.button1:
			Intent intent=new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			break;
			default:
				break;
		}
	}
	
}
