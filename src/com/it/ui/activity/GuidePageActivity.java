package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;

public class GuidePageActivity extends BaseActivity {

	private Handler mHandler=new Handler(){
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		if(mTintManager!=null){
			int color=getResources().getColor(R.color.wite);
    		mTintManager.setTintColor(color);
		}
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		TextView tv=(TextView)findViewById(R.id.guide_tv1);
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.guide_text_show);
		tv.startAnimation(animation);
		tv=(TextView)findViewById(R.id.guide_tv2);
		tv.startAnimation(animation);
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(GuidePageActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 4000);
	}
}
