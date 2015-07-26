package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.IdentifyPresenter;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.ui.adapter.IdentiyAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ToastUtils;

public class SearchActivity extends BaseActivity {

	@ViewInject(R.id.button_category)
	ImageView button_category;

	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_identifing;
	@ViewInject(R.id.btn_identifed)
	ViewGroup btn_identified;
	@ViewInject(R.id.prrv)
	PullRefreshRecyclerView prrvRe;

	RecyclerView prrv;
	
	IdentiyAdapter mAdapter = null;

	@ViewInject(R.id.home_title)
	private TextView home_title;

	int type = 2;

	int current_page = 0;

	private List<CollectionTreasure> list = null;

	private int kindId = 0;

	private IdentifyPresenter identifyPresenter;
	private TreasureType treasuretype;

	private boolean isFirest = true;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		treasuretype = (TreasureType) getIntent().getSerializableExtra(Const.KIND_ID);
		if (treasuretype == null) {
			kindId = 0;
		} else {
			kindId = treasuretype.getId().intValue();
		}
		setContentView(R.layout.layout_identiy);
		ViewUtils.inject(this);
		initViews();
	}

	protected void initViews() {
		home_title.setText(treasuretype.name);
		prrv = (RecyclerView)prrvRe.getRefreshView();
		identifyPresenter = new IdentifyPresenter(netlistener1);
		mAdapter = new IdentiyAdapter();
		list = new ArrayList<CollectionTreasure>();
		if (list != null && list.size() != 0) {

			mAdapter.setData(list);
		}
		button_category.setImageResource(R.drawable.back_botton);
//		prrv.setRefreshLoadMoreListener(this);
//		prrv.setVertical();
		prrv.setAdapter(mAdapter);
		// prrv.refresh();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	
	private onListView<CollectionTreasure> netlistener1 = new onListView<CollectionTreasure>() {

		@Override
		public void onSucess(ArrayList<CollectionTreasure> data) {
			isFirest = false;
			prrvRe.refreshOver(null);
			if (data.size() == 0) {
				new ToastUtils(SearchActivity.this, "没有更多数据");
			}
			mAdapter.setData(data);
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			prrvRe.refreshOver(null);
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
			new ToastUtils(SearchActivity.this, errorMsg);
		}
	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isFirest) {
			getIndentity();
		}
	}

	public void onRefresh() {
		// TODO Auto-generated method stub
		// current_page++;
		getIndentity();

	}

	private void getIndentity() {
		// TODO Auto-generated method stub
		if (dialog == null) {
			dialog = DialogUtil.createLoadingDialog(this, "获取鉴定宝物中....");
		}
		dialog.show();
		identifyPresenter.getIdentity(kindId, type);
	}

	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_identifing: {
			// 先设置背景
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources().getColor(R.color.bt_color));
			btn_identifing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.bt_color));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_identified.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_identifed: {
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_identifing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources().getColor(R.color.bt_color));
			btn_identified.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.bt_color));
		}
			break;
		}
	}

	@OnClick({ R.id.btn_identifing, R.id.btn_identifed, R.id.button_category })
	public void doClick(View v) {
		// TODO Auto-generated method stub
		setIdentifyBackground(v.getId());
		switch (v.getId()) {
		case R.id.button_category: {
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}
			break;
		case R.id.btn_identifing: {
			type = 1;
			prrvRe.refreshOver(null);
//			prrv.setLoadMoreCompleted();
			// 获取正在鉴定数据
			Log.i("ytmfdw", "get identifing");

			getIndentity();

		}

			break;

		case R.id.btn_identifed: {
			// 获取已经鉴定数据
			type = 2;
			prrvRe.refreshOver(null);
//			prrv.setLoadMoreCompleted();
			getIndentity();

		}

			break;
		}
	}

}
