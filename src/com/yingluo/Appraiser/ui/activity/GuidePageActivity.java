package com.yingluo.Appraiser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.server.HomeService;
/**
 * 引导页面
 * @author Administrator
 *
 */
public class GuidePageActivity extends BaseActivity {

	private Handler mHandler=new Handler(){
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
//		if(mTintManager!=null){
//			int color=getResources().getColor(R.color.wite);
//    		mTintManager.setTintColor(color);
//		}
		startService(new Intent(this,HomeService.class));
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(GuidePageActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 5000);
		
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		super.startActivity(intent);
		overridePendingTransition(R.anim.left_in, R.anim.left_out);
	}
}
