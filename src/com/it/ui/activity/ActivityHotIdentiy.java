package com.it.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.bean.CollectionEntity;
import com.it.config.Const;
import com.it.ui.adapter.IdentiyAdapter;
import com.it.utils.BitmapsUtils;
import com.it.view.PullRefreshRecyclerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ActivityHotIdentiy extends Activity implements OnClickListener {

	BitmapsUtils bitmapUtils;
	@ViewInject(R.id.btn_back)
	private View btn_back;
	@ViewInject(R.id.iv_icon)
	ImageView iv_icon;
	@ViewInject(R.id.tv_name)
	TextView tv_name;
	@ViewInject(R.id.tv_grade_name)
	TextView tv_grade_name;

	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_identifing;
	@ViewInject(R.id.btn_identified)
	ViewGroup btn_identified;

	CollectionEntity entity;
	@ViewInject(R.id.btn_msg)
	View btn_msg;
	@ViewInject(R.id.prrv)
	PullRefreshRecyclerView prrv;
	
	IdentiyAdapter mAdapter = null;

	@OnClick(R.id.btn_msg)
	public void doClick(View view) {
		// startActivity(new Intent(ActivityHotIdentiy.this,
		// IMListActivity.class));
	}

	OnClickListener identifyListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 先设置背景
			setIdentifyBackground(v.getId());
			switch (v.getId()) {
			case R.id.btn_identifing: {
				// 设置View
			}

				break;

			case R.id.btn_identified: {
			}

				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_first_page_user);
		ViewUtils.inject(this);
		bitmapUtils = BitmapsUtils.getInstance();
		entity = (CollectionEntity) getIntent().getSerializableExtra(
				Const.ENTITY);
		btn_back.setOnClickListener(this);
		btn_identifing.setOnClickListener(identifyListener);
		btn_identified.setOnClickListener(identifyListener);
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		if (entity == null) {
			return;
		}
		bitmapUtils.display(iv_icon, entity.authImage);
		tv_name.setText(entity.authName);
		if (!TextUtils.equals(entity.company, "")) {

			tv_grade_name.setText(entity.company);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;

		default:
			break;
		}
	}

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_identifing: {
			// 先设置背景
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.bt_color));
			btn_identifing.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.bt_color));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_identified.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_identified: {
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_identifing.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.bt_color));
			btn_identified.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.bt_color));
		}
			break;
		}
	}

}
