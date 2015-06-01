package com.it.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.R;
import com.it.model.CommonCallBack;
import com.it.model.getMyFootPrintsModel;
import com.it.ui.adapter.IdentiyAdapter;
import com.it.view.PullRefreshRecyclerView;
import com.it.view.PullRefreshRecyclerView.RefreshLoadMoreListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ActivityFootPrint extends Activity implements
		RefreshLoadMoreListener {

	@ViewInject(R.id.btn_back)
	View btn_back;
	@ViewInject(R.id.prrv)
	PullRefreshRecyclerView prrv;
	@ViewInject(R.id.tv_none)
	TextView tv_none;
	@ViewInject(R.id.iv_loading)
	ImageView iv_loading;

	IdentiyAdapter mAdapter = null;

	getMyFootPrintsModel model = null;

	@OnClick({ R.id.btn_back })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_foot_print);
		ViewUtils.inject(this);
		model = new getMyFootPrintsModel();
		mAdapter = new IdentiyAdapter();

		prrv.setRefreshLoadMoreListener(this);
		prrv.setVertical();

		prrv.setAdapter(mAdapter);
		prrv.refresh();

		prrv.setVisibility(View.GONE);
		iv_loading.setVisibility(View.VISIBLE);

		model.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				prrv.setVisibility(View.VISIBLE);
				iv_loading.setVisibility(View.GONE);
				mAdapter.setData(model.getResult());
				if (mAdapter.getData().size() == 0) {
					tv_none.setVisibility(View.VISIBLE);
				} else {
					tv_none.setVisibility(View.GONE);
				}

			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		prrv.stopRefresh();

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

}
