package com.it.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.it.R;
import com.it.ui.activity.ActivitySearch;
import com.it.ui.base.BaseFragment;
import com.it.view.ImageViewPage;

public class HomeFragment extends BaseFragment {

	private ImageViewPage head;

	private View btn_search;
	HorizontalScrollView hsv;

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_home, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		head = (ImageViewPage) view.findViewById(R.id.imageViewpage);
		hsv = (HorizontalScrollView) view.findViewById(R.id.hs_people);
		// for test
		List<Drawable> list = new ArrayList<Drawable>();
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		list.add(getResources().getDrawable(R.drawable.test_1));
		head.prepareData(list);

		btn_search = view.findViewById(R.id.btn_search);
		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), ActivitySearch.class));
			}
		});

	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		setMenu(hsv);
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub

	}

	public void setMenu(HorizontalScrollView view) {

		LinearLayout layout = new LinearLayout(getActivity());
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		layout.setPadding(10, 0, 10, 0);
		layout.setLayoutParams(layoutParams);
		layout.setGravity(Gravity.CENTER_VERTICAL);
		layoutParams.gravity = Gravity.CENTER_VERTICAL;

		layout.addView(insertImage(1));
		layout.addView(insertImage(1));
		layout.addView(insertImage(0));
		layout.addView(insertImage(1));
		layout.addView(insertImage(1));
		view.addView(layout);
	}

	private View insertImage(int i) {
		ImageView view = new ImageView(getActivity());
		view.setImageResource(R.drawable.test_xc4);
		return view;
	}

}
