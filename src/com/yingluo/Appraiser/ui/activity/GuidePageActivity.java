package com.yingluo.Appraiser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.server.HomeService;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;

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
		if(SharedPreferencesUtils.getInstance().getIsHaveLogin()){
			String name=SharedPreferencesUtils.getInstance().getLoginUserName();
			if(name==null){
				SharedPreferencesUtils.getInstance().saveForIsLogin(false);
			}else{	
				ItApplication.currnUser=SqlDataUtil.getInstance().getUserForPhone(name);
				if(ItApplication.currnUser!=null){
					NetConst.SESSIONID=ItApplication.currnUser.getSession_id();
					NetConst.UPTOKEN=ItApplication.currnUser.getImage_token();
					NetConst.IMTOKEN=ItApplication.currnUser.getMessage_token();
					RongImUtils.getInstance().connect(NetConst.IMTOKEN);	
				}
			}
		}
	
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
