package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.config.Const;
import com.it.ui.base.BaseActivity;
import com.it.utils.ToastUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 认证鉴定师页面
 * @author Administrator
 *
 */
public class AuthenticateActivity extends BaseActivity {


	@ViewInject(R.id.home_title)
	private TextView title;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authenticate);
		ViewUtils.inject(this);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.authenticate_title);
	}



	@OnClick({R.id.btn_back,R.id.attest_agency_layout,R.id.attest_personal_layout})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
			break;
		case R.id.attest_agency_layout://机构认证
			 intent=new Intent(this, AttestAgencyActivity.class);
			startActivityForResult(intent, Const.TO_ATTEST_AGENCY);
			break;
		case R.id.attest_personal_layout://个人认证
			 intent=new Intent(this, AttestPersionalActivity.class);
			startActivityForResult(intent, Const.TO_ATTEST_PERSIONAL);
			break;
		default:
			break;
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==Const.TO_ATTEST_PERSIONAL&&resultCode==RESULT_OK){//个人认证成功
			new ToastUtils(this, "申请已经提交，请等待审核...");
			setResult(RESULT_OK, getIntent());
			finish();
		}
		if(requestCode==Const.TO_ATTEST_AGENCY&&resultCode==RESULT_OK){//个人认证成功
			new ToastUtils(this, "申请已经提交，请等待审核...");
			setResult(RESULT_OK, getIntent());
			finish();
		}
	}
}
