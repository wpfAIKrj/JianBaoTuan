package com.it.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.it.R;
import com.it.bean.HotsEntity;
import com.it.ui.adapter.IdentiyAdapter;
import com.it.ui.base.BaseFragment;
import com.it.view.PullRefreshRecyclerView;
import com.it.view.PullRefreshRecyclerView.RefreshLoadMoreListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class IdentiyFragment extends BaseFragment implements
		RefreshLoadMoreListener {

	@ViewInject(R.id.button_category)
	ImageView button_category;

	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_identifing;
	@ViewInject(R.id.btn_identifed)
	ViewGroup btn_identified;
	@ViewInject(R.id.prrv)
	PullRefreshRecyclerView prrv;

	IdentiyAdapter mAdapter = null;

	@OnClick({ R.id.btn_identifing, R.id.btn_identifed })
	public void doClick(View v) {
		// TODO Auto-generated method stub
		setIdentifyBackground(v.getId());
		switch (v.getId()) {
		case R.id.btn_identifing: {
			// 获取正在鉴定数据
			Log.i("ytmfdw", "get identifing");
		}

			break;

		case R.id.btn_identifed: {
			// 获取已经鉴定数据
			Log.i("ytmfdw", "get identifed");
		}

			break;
		}
	}

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_identiy, container, false);
	}

	@Override
	protected void initViews(View view) {
		ViewUtils.inject(this, view);
		mAdapter = new IdentiyAdapter();
		// test data
		List<HotsEntity> list = new ArrayList<HotsEntity>();
		list.add(new HotsEntity("向阳花木", "http:\\", "我的宝贝", "http:\\", "1000",
				"http:\\www.baidu.com"));
		list.add(new HotsEntity("向阳花", "http:\\", "我宝贝", "http:\\", "1000",
				"http:\\www.baidu.com"));
		list.add(new HotsEntity("向阳", "http:\\", "宝贝", "http:\\", "1000",
				"http:\\www.baidu.com"));
		list.add(new HotsEntity("向", "http:\\", "我的", "http:\\", "1000",
				"http:\\www.baidu.com"));
		mAdapter.setData(list);

		prrv.setRefreshLoadMoreListener(this);
		prrv.setVertical();

		prrv.setAdapter(mAdapter);
		prrv.refresh();

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

		case R.id.btn_identifed: {
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

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

}
