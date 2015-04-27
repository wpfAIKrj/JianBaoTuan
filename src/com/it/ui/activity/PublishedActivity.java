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
import com.it.ui.dialog.SelectPhotoDialog;

public class PublishedActivity extends BaseActivity implements OnClickListener {

	private SelectPhotoDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_published);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		ImageView bt=(ImageView)findViewById(R.id.button_category);
		bt.setBackgroundResource(R.drawable.back_botton);
		bt.setOnClickListener(this);
		TextView tv=(TextView)findViewById(R.id.home_title);
		tv.setText(R.string.publish_title);
		 bt=(ImageView)findViewById(R.id.published_bt);
		bt.setOnClickListener(this);
	 bt=(ImageView)findViewById(R.id.imageView01);
	 bt.setOnClickListener(this);
	 bt=(ImageView)findViewById(R.id.imageView02);
	 bt.setOnClickListener(this);
	 bt=(ImageView)findViewById(R.id.imageView03);
	 bt.setOnClickListener(this);
	 bt=(ImageView)findViewById(R.id.imageView04);
	 bt.setOnClickListener(this);
	 bt=(ImageView)findViewById(R.id.imageView05);
	 bt.setOnClickListener(this);
	 bt=(ImageView)findViewById(R.id.imageView06);
	 bt.setOnClickListener(this);
	 Button bt1=(Button)findViewById(R.id.button1);
	 bt1.setOnClickListener(this);
	 
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.published_bt://跳转到搜索
			
			break;
		case R.id.imageView01:
			dialog=new SelectPhotoDialog(this);
			dialog.show();
			break;
		case R.id.imageView02:
			dialog=new SelectPhotoDialog(this);
			dialog.show();
			break;
		case R.id.imageView03:
			dialog=new SelectPhotoDialog(this);
			dialog.show();
			break;
		case R.id.imageView04:
			dialog=new SelectPhotoDialog(this);
			dialog.show();
			break;
		case R.id.imageView05:
			dialog=new SelectPhotoDialog(this);
			dialog.show();
			break;
		case R.id.imageView06:
			dialog=new SelectPhotoDialog(this);
			dialog.show();
			break;
		case R.id.button1:
			startActivity(new Intent(PublishedActivity.this, PublishedNextActivity.class));
			break;
		default:
			break;
		}
	}
}
