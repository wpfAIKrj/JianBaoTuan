package com.it.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.it.R;
import com.it.bean.UserInfo;
import com.it.config.NetConst;
import com.it.presenter.RegisterPresenter;
import com.it.ui.base.BaseActivity;
import com.it.view.inter.RegisterView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView{

	@ViewInject(R.id.register_edit_phone)
	private EditText ed_phone;
	
	@ViewInject(R.id.register_edit_code)
	private EditText ed_code;
	
	@ViewInject(R.id.register_edit_password)
	private EditText ed_pwd;
	
	@ViewInject(R.id.register_send_code)
	private Button send_code;
	
	private RegisterPresenter mpresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ViewUtils.inject(this);
		mpresenter=new RegisterPresenter(this);
		SMSSDK.initSDK(this, NetConst.SMS_KEY, NetConst.SMS_SECRET);
		SMSSDK.registerEventHandler(eh); //注册短信回调
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		SMSSDK.unregisterEventHandler(eh);
		super.onDestroy();
	}
	


	@OnClick({R.id.register_button,R.id.register_send_code,R.id.title_back})
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



	@Override
	public void loginSucess(UserInfo user) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void loginFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		
	}
	
	 EventHandler eh=new EventHandler(){
		   
         @Override
         public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
             //回调完成
         	  
             if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
             //提交验证码成功
             	
             }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
             //获取验证码成功
             	
             }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
             //返回支持发送验证码的国家列表
            	 ArrayList<HashMap<String,Object>> citys=(ArrayList<HashMap<String, Object>>) data;
            	 
            	 for (HashMap<String, Object> hashMap : citys) {
            		 Set<String> c = hashMap.keySet();
            		 System.out.println(c);
				}
            	
             } 
           }else{                                                                 
              ((Throwable)data).printStackTrace(); 
       }
   } 
}; 
	
}
