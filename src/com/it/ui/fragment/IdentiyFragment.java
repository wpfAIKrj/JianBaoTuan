package com.it.ui.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.it.R;
import com.it.app.ItApplication;
import com.it.bean.CollectionEntity;
import com.it.config.Const;
import com.it.model.CommonCallBack;
import com.it.model.IdentifyModel;
import com.it.ui.activity.ActivityKindOfPrecious;
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

	IdentifyModel model = null;

	int type = 1;

	int current_page = 0;

	private List<CollectionEntity> list = null;

	ItApplication app;

	@OnClick({ R.id.btn_identifing, R.id.btn_identifed, R.id.button_category })
	public void doClick(View v) {
		// TODO Auto-generated method stub
		setIdentifyBackground(v.getId());
		switch (v.getId()) {
		case R.id.button_category: {
			Intent mIntent = new Intent(getActivity(),
					ActivityKindOfPrecious.class);
			startActivityForResult(mIntent, Const.TO_INDENTIFY);
		}
			break;
		case R.id.btn_identifing: {
			type = 1;
			prrv.stopRefresh();
			prrv.setLoadMoreCompleted();
			// 获取正在鉴定数据
			Log.i("ytmfdw", "get identifing");
			if (app != null) {
				if (app.getHasIdentify() != null) {
					list = app.getHasIdentify();
					mAdapter.setData(list);
				} else {
					// 联网下载
				}
			}

		}

			break;

		case R.id.btn_identifed: {
			// 获取已经鉴定数据
			type = 2;
			prrv.stopRefresh();
			prrv.setLoadMoreCompleted();
			Log.i("ytmfdw", "get identifed");
			if (app != null) {
				if (app.getUnIdentify() != null) {
					list = app.getUnIdentify();
					mAdapter.setData(list);
				} else {
					// 联网下载
				}
			}
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
		model = new IdentifyModel();
		mAdapter = new IdentiyAdapter();
		app = (ItApplication) getActivity().getApplication();
		list = app.getHasIdentify();
		if (list != null && list.size() != 0) {

			mAdapter.setData(list);
		}

		prrv.setRefreshLoadMoreListener(this);
		prrv.setVertical();

		prrv.setAdapter(mAdapter);
		// prrv.refresh();

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
		// current_page++;
		model.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				prrv.stopRefresh();
				List<CollectionEntity> result = model.getResult();
				if (result.size() == 0) {
					Toast.makeText(mActivity, "没有更多数据", Toast.LENGTH_LONG)
							.show();
				}
				mAdapter.getData().addAll(model.getResult());
				mAdapter.setData(model.getResult());

			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub
				prrv.stopRefresh();

			}
		}, type);

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Const.TO_INDENTIFY) {
			long kindId = data.getIntExtra(Const.KIND_ID, -1);
			// 根据分类id来加载
			model.sendHttp(new CommonCallBack() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					prrv.stopRefresh();
					List<CollectionEntity> result = model.getResult();
					if (result.size() == 0) {
						Toast.makeText(mActivity, "没有更多数据", Toast.LENGTH_LONG)
								.show();
					}
					mAdapter.setData(model.getResult());
				}

				@Override
				public void onError() {
					// TODO Auto-generated method stub
					prrv.stopRefresh();
				}
			}, type, kindId);
		}
	}

}
