package com.it.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class AttestAgencyActivity extends BaseActivity {


	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.et_name)
	private EditText name;
	
	@ViewInject(R.id.et_number)
	private EditText number;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attest_agentcy);
		ViewUtils.inject(this);
		initView();
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.title_personal);
	}


	@OnClick({R.id.btn_back,R.id.bt_attest})
	public void OnClick(View v){
		switch (v.getId()) {
		case R.id.btn_back://返回上一层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.bt_attest://提交认证信息
			
			
			break;
			
		default:
			break;
		}
	}
}
