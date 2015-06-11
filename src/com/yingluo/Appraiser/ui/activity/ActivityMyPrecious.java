package com.yingluo.Appraiser.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.MyTreasureModel;
import com.yingluo.Appraiser.ui.adapter.MyTreasureAdapter;

public class ActivityMyPrecious extends Activity {

	@ViewInject(R.id.tv_title)
	TextView title;

	// View view_all, view_identifing, view_identified;
	@ViewInject(R.id.btn_all)
	ViewGroup btn_all;
	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_ing;
	@ViewInject(R.id.btn_identified)
	ViewGroup btn_ed;

	View iv_01, iv_02, iv_03, iv_04;
	@ViewInject(R.id.btn_delete)
	View btn_delete;
	@ViewInject(R.id.btn_back)
	View btn_back;
	@ViewInject(R.id.swipe_refresh_widget)
	SwipeRefreshLayout swipe_refresh_widget;
	@ViewInject(R.id.recyclerview)
	RecyclerView recyclerview;

	@OnClick(R.id.btn_back)
	public void back_click(View view) {
		onBackPressed();
	}

	int type = Const.PRECIOUS;

	MyTreasureModel model;

	MyTreasureAdapter mAdapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_precious);
		ViewUtils.inject(this);

		model = new MyTreasureModel();

		type = getIntent().getIntExtra(Const.GOTO_MY_PRECIOUS, Const.PRECIOUS);

		model.setType(type);
//		model.setType(Const.IDENTIFY);
		if (type == Const.PRECIOUS) {
			title.setText("我的宝物");
		} else if (type == Const.COLLECT) {

			title.setText("收藏宝物");
			btn_delete.setVisibility(View.VISIBLE);
		} else if (type == Const.IDENTIFY) {

			title.setText("我的鉴定");
		}
		btn_all.setOnClickListener(listener);
		btn_ing.setOnClickListener(listener);
		btn_ed.setOnClickListener(listener);

		btn_all.callOnClick();

		initViews();

	}

	private void initViews() {
		// TODO Auto-generated method stub
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(layoutManager);
		recyclerview.setHasFixedSize(true);
		mAdapter = new MyTreasureAdapter();
		recyclerview.setAdapter(mAdapter);
	}

	OnClickListener ivListener = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(ActivityMyPrecious.this,
					ActivityUserDelails.class));
		};
	};

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setIdentifyBackground(v.getId());
			switch (v.getId()) {
			case R.id.btn_all: {
				model.sendHttp(new CommonCallBack() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						mAdapter.setData(model.getResult());

					}

					@Override
					public void onError() {
						// TODO Auto-generated method stub

					}
				}, MyTreasureModel.TYPE_ALL);
			}
				break;
			case R.id.btn_identifing: {
				model.sendHttp(new CommonCallBack() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						mAdapter.setData(model.getResult());

					}

					@Override
					public void onError() {
						// TODO Auto-generated method stub

					}
				}, MyTreasureModel.TYPE_IDENTIFYING);

			}
				break;
			case R.id.btn_identified: {
				model.sendHttp(new CommonCallBack() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						mAdapter.setData(model.getResult());

					}

					@Override
					public void onError() {
						// TODO Auto-generated method stub

					}
				}, MyTreasureModel.TYPE_IDENTIFIED);
			}
				break;

			default:
				break;
			}

		}
	};

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_all: {
			// 先设置背景
			((Button) btn_all.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.bt_color));
			btn_all.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.bt_color));

			((Button) btn_ed.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_identifing: {
			((Button) btn_all.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));

			((Button) btn_ed.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.bt_color));
			btn_ing.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.bt_color));
		}
			break;
		case R.id.btn_identified: {
			((Button) btn_all.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));

			((Button) btn_ed.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.bt_color));
			btn_ed.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.bt_color));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));
		}
			break;
		}
	}

}
