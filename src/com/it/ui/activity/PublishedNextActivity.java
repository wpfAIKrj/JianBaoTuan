package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;

/**
 * 发布藏品的第二步
 * @author Administrator
 *
 */
public class PublishedNextActivity extends BaseActivity {

	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.ed_info)
	private EditText ed_info;
	
	@ViewInject(R.id.people01)
	private LinearLayout layout1;
	@ViewInject(R.id.people02)
	private LinearLayout layout2;
	@ViewInject(R.id.people03)
	private LinearLayout layout3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publishednext);
		ViewUtils.inject(this);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.publish_title);

	}

	@OnClick({R.id.btn_back,R.id.send_identy})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.send_identy:
			
			setResult(RESULT_OK, getIntent());
			break;
			default:
				break;
		}
	}
	
	
	
}
