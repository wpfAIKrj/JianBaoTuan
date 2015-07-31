package com.yingluo.Appraiser.ui.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 聊天页面
 * 
 * @author Administrator
 *
 */
public class ConversationActivity extends FragmentActivity {
	@ViewInject(R.id.home_title)
	private TextView tvtitle;

	@ViewInject(R.id.button_delect)
	private ImageView iv_delete;

	private String targetId;
	private String title;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_conversation); // 加载会话页面 Fragment。
		ViewUtils.inject(this);
		// 显示
		initView();
	}

	private void initView() {
		targetId = getIntent().getData().getQueryParameter("targetId");
		title = getIntent().getData().getQueryParameter("title");
		tvtitle.setText(title);
		iv_delete.setVisibility(View.GONE);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@OnClick({ R.id.button_category })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_category:// 跳转到
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;

		default:
			break;
		}
	}
}
