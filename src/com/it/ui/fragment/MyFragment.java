package com.it.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.R;
import com.it.ui.base.BaseFragment;

public class MyFragment extends BaseFragment {

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_my, container, false);
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
		System.out.println(getClass().getName()+"正在加载数据");
	}

	
	
	
	
	


}
