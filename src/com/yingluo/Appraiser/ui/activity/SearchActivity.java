package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
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
import com.yingluo.Appraiser.ui.adapter.IdentiyAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.PullRefreshRecyclerView;
import com.yingluo.Appraiser.view.PullRefreshRecyclerView.RefreshLoadMoreListener;

public class SearchActivity extends BaseActivity implements
RefreshLoadMoreListener{

	@ViewInject(R.id.button_category)
	ImageView button_category;

	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_identifing;
	@ViewInject(R.id.btn_identifed)
	ViewGroup btn_identified;
	@ViewInject(R.id.prrv)
	PullRefreshRecyclerView prrv;

	IdentiyAdapter mAdapter = null;

	@ViewInject(R.id.home_title)
	private TextView home_title;


	int type = 1;

	int current_page = 0;

	private List<CollectionTreasure> list = null;

	ItApplication app;

	private int kindId= 0;
	
	private IdentifyPresenter identifyPresenter;
	private TreasureType treasuretype;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		treasuretype=(TreasureType) getIntent().getSerializableExtra(Const.KIND_ID);
		if(treasuretype==null){
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
		setContentView(R.layout.layout_identiy);
		ViewUtils.inject(this);
		initViews();
	}
	
	protected void initViews() {
		kindId=treasuretype.getId().intValue();
		home_title.setText(treasuretype.name);
		identifyPresenter=new IdentifyPresenter(netlistener1);
		mAdapter = new IdentiyAdapter();
		app = (ItApplication)getApplication();
		list = app.getHasIdentify();
		if (list != null && list.size() != 0) {

			mAdapter.setData(list);
		}
		button_category.setImageResource(R.drawable.back_botton);
		prrv.setRefreshLoadMoreListener(this);
		prrv.setVertical();
		prrv.setAdapter(mAdapter);
		// prrv.refresh();

	}
	private onListView<CollectionTreasure> netlistener1=new onListView<CollectionTreasure>() {
		
		@Override
		public void onSucess(ArrayList<CollectionTreasure> data) {
			// TODO Auto-generated method stub
			prrv.stopRefresh();
			if (data.size() == 0) {
				new ToastUtils(SearchActivity.this, "没有更多数据");
			}
			mAdapter.setData(data);
		}
		
		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			prrv.stopRefresh();
			new ToastUtils(SearchActivity.this, errorMsg);
		}
	};
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		// current_page++;
		
		identifyPresenter.getIdentity(kindId,type);

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

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
	
	@OnClick({ R.id.btn_identifing, R.id.btn_identifed, R.id.button_category })
	public void doClick(View v) {
		// TODO Auto-generated method stub
		setIdentifyBackground(v.getId());
		switch (v.getId()) {
		case R.id.button_category: {
			setResult(RESULT_OK, getIntent());
			finish();
		}
			break;
		case R.id.btn_identifing: {
			type = 1;
			prrv.stopRefresh();
			prrv.setLoadMoreCompleted();
			// 获取正在鉴定数据
			Log.i("ytmfdw", "get identifing");
			if (app != null) {
				if(kindId==0){
				if (app.getHasIdentify() != null) {
					list = app.getHasIdentify();
					mAdapter.setData(list);
				} else {
					// 联网下载
					identifyPresenter.getIdentity(kindId, type);
				}
				}else{
					identifyPresenter.getIdentity(kindId, type);
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
				if(kindId==0){
				if (app.getUnIdentify() != null) {
					list = app.getUnIdentify();
					mAdapter.setData(list);
				} else {
					// 联网下载
					identifyPresenter.getIdentity(kindId, type);
				}
				}else{
					identifyPresenter.getIdentity(kindId, type);
				}
			}
		}

			break;
		}
	}

}
