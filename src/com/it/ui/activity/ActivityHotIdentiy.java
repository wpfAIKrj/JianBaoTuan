package com.it.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;

import com.it.R;

public class ActivityHotIdentiy extends Activity implements OnClickListener {

	private View btn_back;

	ViewGroup btn_identifing, btn_identified;
	ViewGroup fl_identify;

	View view_identifing, view_identified;

	private int currentView = 0;

	OnClickListener identifyListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 先设置背景
			setIdentifyBackground(v.getId());
			switch (v.getId()) {
			case R.id.btn_identifing: {
				// 设置View
				if (currentView == 0)
					return;
				currentView = 0;
				if (view_identifing == null) {
					view_identifing = LayoutInflater.from(
							ActivityHotIdentiy.this).inflate(
							R.layout.item_identified_test, null);
				}
				if (view_identified == null) {
					view_identified = LayoutInflater.from(
							ActivityHotIdentiy.this).inflate(
							R.layout.item_identified_test, null);
				}
				view_identified.startAnimation(AnimationUtils.loadAnimation(
						ActivityHotIdentiy.this, R.anim.left_out));
				fl_identify.removeView(view_identified);
				view_identifing.startAnimation(AnimationUtils.loadAnimation(
						ActivityHotIdentiy.this, R.anim.right_in));
				fl_identify.addView(view_identifing);
			}

				break;

			case R.id.btn_identified: {
				if (currentView == 1) {
					return;
				}
				currentView = 1;
				if (view_identifing == null) {
					view_identifing = LayoutInflater.from(
							ActivityHotIdentiy.this).inflate(
							R.layout.item_identified_test, null);
				}
				if (view_identified == null) {
					view_identified = LayoutInflater.from(
							ActivityHotIdentiy.this).inflate(
							R.layout.item_identified_test, null);
				}
				view_identifing.startAnimation(AnimationUtils.loadAnimation(
						ActivityHotIdentiy.this, R.anim.left_out));
				fl_identify.removeView(view_identifing);

				view_identified.startAnimation(AnimationUtils.loadAnimation(
						ActivityHotIdentiy.this, R.anim.right_in));
				fl_identify.addView(view_identified);
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
		btn_back = findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		fl_identify = (ViewGroup) findViewById(R.id.fl_identify);
		btn_identifing = (ViewGroup) findViewById(R.id.btn_identifing);
		btn_identified = (ViewGroup) findViewById(R.id.btn_identified);

		btn_identifing.setOnClickListener(identifyListener);
		btn_identified.setOnClickListener(identifyListener);

		if (view_identifing == null) {
			view_identifing = LayoutInflater.from(this).inflate(
					R.layout.item_identified_test, null);
		}
		if (view_identified == null) {
			view_identified = LayoutInflater.from(this).inflate(
					R.layout.item_identified_test, null);
		}

		fl_identify.addView(view_identifing);

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
