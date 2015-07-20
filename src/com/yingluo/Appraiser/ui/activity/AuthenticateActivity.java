package com.yingluo.Appraiser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.ToastUtils;

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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@OnClick({R.id.btn_back,R.id.attest_agency_layout,R.id.attest_personal_layout})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_back://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
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
