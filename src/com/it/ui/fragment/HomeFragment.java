package com.it.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.it.R;
import com.it.ui.base.BaseFragment;

public class HomeFragment extends BaseFragment {

	private RelativeLayout mtitle;

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_home, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		

	}

}
