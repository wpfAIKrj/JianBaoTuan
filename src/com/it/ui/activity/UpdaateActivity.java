package com.it.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.ui.base.BaseActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 更新软件
 * @author Administrator
 *
 */
public class UpdaateActivity extends BaseActivity implements OnClickListener{
	

	@ViewInject(R.id.home_title)
	private TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		ViewUtils.inject(this);
		initView();
	}

	
	
	private void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.feednack_title);
	}



	@OnClick({R.id.btn_back})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back://返回上层
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;

		default:
			break;
		}
	}
}
