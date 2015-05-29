package com.it.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.bean.CollectionEntity;
import com.it.config.Const;
import com.it.utils.BitmapsUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ActivityUserDelails extends Activity {

	private BitmapsUtils bitmapUtils;

	@ViewInject(R.id.detail_back)
	private View detail_back;
	@ViewInject(R.id.btn_goto)
	private View btn_goto;

	@ViewInject(R.id.iv_head)
	private ImageView iv_head;

	@ViewInject(R.id.tv_name)
	private TextView tv_name;

	@ViewInject(R.id.tv_msg)
	private TextView tv_msg;

	@OnClick({ R.id.detail_back, R.id.btn_goto })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.detail_back: {
			onBackPressed();
		}
			break;

		case R.id.btn_goto: {
			startActivity(new Intent(ActivityUserDelails.this,
					ActivityIdentifyByMe.class));
		}
			break;
		}
	}

	private CollectionEntity entity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_delails);
		ViewUtils.inject(this);
		entity = (CollectionEntity) getIntent().getSerializableExtra(
				Const.ENTITY);
		bitmapUtils = BitmapsUtils.getInstance();
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		if (entity == null) {
			return;
		}
		bitmapUtils.display(iv_head, entity.authImage);
		tv_name.setText(entity.authName);
		tv_msg.setText(entity.msg);

	}
}
