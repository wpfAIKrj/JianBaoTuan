package com.yingluo.Appraiser.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 聊天页面
 * 
 * @author Administrator
 *
 */
public class IMListActivity extends FragmentActivity {

	@ViewInject(R.id.home_title)
	private TextView title;

	@ViewInject(R.id.button_delect)
	private ImageView iv_delete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_imlistview);
		ViewUtils.inject(this);
		initView();

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	protected void initView() {
		title.setText("聊天");
		iv_delete.setVisibility(View.GONE);
	}

	@OnClick({ R.id.button_category })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_category:// 跳转到
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;

		default:
			break;
		}
	}

}
