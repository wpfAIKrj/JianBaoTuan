package com.it.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.it.R;
import com.it.ui.activity.ActivityHotIdentiy;
import com.it.ui.activity.ActivityKindOfPrecious;
import com.it.ui.base.BaseFragment;

public class IdentiyFragment extends BaseFragment {

	ImageView button_category;

	ViewGroup btn_identifing, btn_identified;
	ViewGroup fl_identify;

	View view_identifing, view_identified;

	View item0, item1, item2, item3;

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
					view_identifing = LayoutInflater.from(getActivity())
							.inflate(R.layout.item_identified_test, null);
				}
				if (view_identified == null) {
					view_identified = LayoutInflater.from(getActivity())
							.inflate(R.layout.item_identified_test, null);
				}
				view_identified.startAnimation(AnimationUtils.loadAnimation(
						getActivity(), R.anim.left_out));
				fl_identify.removeView(view_identified);
				view_identifing.startAnimation(AnimationUtils.loadAnimation(
						getActivity(), R.anim.right_in));
				fl_identify.addView(view_identifing);
			}

				break;

			case R.id.btn_identified: {
				if (currentView == 1) {
					return;
				}
				currentView = 1;
				if (view_identifing == null) {
					view_identifing = LayoutInflater.from(getActivity())
							.inflate(R.layout.item_identified_test, null);
				}
				if (view_identified == null) {
					view_identified = LayoutInflater.from(getActivity())
							.inflate(R.layout.item_identified_test, null);
				}
				view_identifing.startAnimation(AnimationUtils.loadAnimation(
						getActivity(), R.anim.left_out));
				fl_identify.removeView(view_identifing);

				view_identified.startAnimation(AnimationUtils.loadAnimation(
						getActivity(), R.anim.right_in));
				fl_identify.addView(view_identified);
			}

				break;
			case R.id.iv_01:
			case R.id.iv_02:
			case R.id.iv_03:
			case R.id.iv_04:
				startActivity(new Intent(getActivity(),
						ActivityHotIdentiy.class));
				break;
			}

		}
	};

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_identiy, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		button_category = (ImageView) view.findViewById(R.id.button_category);
		button_category.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),
						ActivityKindOfPrecious.class));
			}
		});
		fl_identify = (ViewGroup) view.findViewById(R.id.fl_identify);
		btn_identifing = (ViewGroup) view.findViewById(R.id.btn_identifing);
		btn_identified = (ViewGroup) view.findViewById(R.id.btn_identified);

		btn_identifing.setOnClickListener(identifyListener);
		btn_identified.setOnClickListener(identifyListener);

		if (view_identifing == null) {
			view_identifing = LayoutInflater.from(getActivity()).inflate(
					R.layout.item_identified_test, null);
		}
		if (view_identified == null) {
			view_identified = LayoutInflater.from(getActivity()).inflate(
					R.layout.item_identified_test, null);
		}

		fl_identify.addView(view_identifing);

		item0 = view_identifing.findViewById(R.id.iv_01);
		item1 = view_identifing.findViewById(R.id.iv_02);
		item2 = view_identifing.findViewById(R.id.iv_03);
		item3 = view_identifing.findViewById(R.id.iv_04);
		item0.setOnClickListener(identifyListener);
		item1.setOnClickListener(identifyListener);
		item2.setOnClickListener(identifyListener);
		item3.setOnClickListener(identifyListener);

	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName() + "正在加载数据");
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
