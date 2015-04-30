package com.it.ui.activity;

import com.it.R;
import com.it.config.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ActivityMyPrecious extends Activity {

	TextView title;

	View view_all, view_identifing, view_identified;
	ViewGroup btn_all, btn_ing, btn_ed;
	ViewGroup vg;

	View iv_01, iv_02, iv_03, iv_04;

	View btn_delete, btn_back;
	int type = Const.PRECIOUS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_precious);
		type = getIntent().getIntExtra(Const.GOTO_MY_PRECIOUS, Const.PRECIOUS);
		title = (TextView) findViewById(R.id.tv_title);
		btn_delete = findViewById(R.id.btn_delete);
		btn_back = findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		if (type == Const.PRECIOUS) {

			title.setText("我的宝物");
		} else if (type == Const.COLLECT) {

			title.setText("收藏宝物");
			btn_delete.setVisibility(View.VISIBLE);
		} else if (type == Const.IDENTIFY) {

			title.setText("我的鉴定");
		}
		view_all = LayoutInflater.from(this).inflate(
				R.layout.layout_my_precious, null);
		view_identifing = LayoutInflater.from(this).inflate(
				R.layout.layout_my_precious, null);
		view_identified = LayoutInflater.from(this).inflate(
				R.layout.layout_my_precious, null);
		vg = (ViewGroup) findViewById(R.id.layout_add);
		btn_all = (ViewGroup) findViewById(R.id.btn_all);
		btn_ing = (ViewGroup) findViewById(R.id.btn_identifing);
		btn_ed = (ViewGroup) findViewById(R.id.btn_identified);
		btn_all.setOnClickListener(listener);
		btn_ing.setOnClickListener(listener);
		btn_ed.setOnClickListener(listener);

		vg.addView(view_all);
		findViews(view_all);

	}

	private void findViews(View view) {
		// TODO Auto-generated method stub
		iv_01 = view.findViewById(R.id.iv_01);
		iv_02 = view.findViewById(R.id.iv_02);
		iv_03 = view.findViewById(R.id.iv_03);
		iv_04 = view.findViewById(R.id.iv_04);
		iv_01.setOnClickListener(ivListener);
		iv_02.setOnClickListener(ivListener);
		iv_03.setOnClickListener(ivListener);
		iv_04.setOnClickListener(ivListener);
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
			case R.id.btn_all:
				vg.getChildAt(0).startAnimation(
						AnimationUtils.loadAnimation(ActivityMyPrecious.this,
								R.anim.left_out));
				vg.removeAllViews();
				view_all.startAnimation(AnimationUtils.loadAnimation(
						ActivityMyPrecious.this, R.anim.right_in));
				vg.addView(view_all);
				findViews(view_all);
				break;
			case R.id.btn_identifing:
				vg.getChildAt(0).startAnimation(
						AnimationUtils.loadAnimation(ActivityMyPrecious.this,
								R.anim.left_out));
				vg.removeAllViews();
				view_identifing.startAnimation(AnimationUtils.loadAnimation(
						ActivityMyPrecious.this, R.anim.right_in));
				vg.addView(view_identifing);
				findViews(view_identifing);
				break;
			case R.id.btn_identified:
				vg.getChildAt(0).startAnimation(
						AnimationUtils.loadAnimation(ActivityMyPrecious.this,
								R.anim.left_out));
				vg.removeAllViews();
				view_identified.startAnimation(AnimationUtils.loadAnimation(
						ActivityMyPrecious.this, R.anim.right_in));
				vg.addView(view_identified);
				findViews(view_identified);
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
