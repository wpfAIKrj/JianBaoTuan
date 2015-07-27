package com.yingluo.Appraiser.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.MyTreasureModel;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.refresh.RefreshLayout;
import com.yingluo.Appraiser.ui.adapter.MyTreasureAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.ui.fragment.InformationFragment;

public class ActivityMyPrecious extends BaseActivity {

	@ViewInject(R.id.tv_title)
	TextView title;

	// View view_all, view_identifing, view_identified;
	@ViewInject(R.id.btn_all)
	ViewGroup btn_all;
	@ViewInject(R.id.btn_no)
	ViewGroup btn_no;
	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_ing;
	@ViewInject(R.id.btn_identified)
	ViewGroup btn_ed;

	View iv_01, iv_02, iv_03, iv_04;
	@ViewInject(R.id.btn_delete)
	View btn_delete;
	@ViewInject(R.id.btn_back)
	View btn_back;
	@ViewInject(R.id.recyclerview)
	PullRefreshRecyclerView mRecyclerview;

	@OnClick(R.id.btn_back)
	public void back_click(View view) {
		onBackPressed();
	}

	int type = Const.PRECIOUS;

	MyTreasureModel model;

	MyTreasureAdapter mAdapter;

	private int Treadsure_type;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_precious);
		ViewUtils.inject(this);

		model = new MyTreasureModel();

		type = getIntent().getIntExtra(Const.GOTO_MY_PRECIOUS, Const.PRECIOUS);

		model.setType(type);
		Treadsure_type = MyTreasureModel.TYPE_ALL;
		if (type == Const.PRECIOUS) {
			title.setText("我的宝物");
			btn_delete.setVisibility(View.GONE);
		} else if (type == Const.COLLECT) {
			
			title.setText("收藏宝物");
			btn_delete.setVisibility(View.GONE);
			btn_no.setVisibility(View.GONE);
			btn_no.setClickable(false);
		} else if (type == Const.IDENTIFY) {

			title.setText("我的鉴定");
			btn_delete.setVisibility(View.GONE);
			btn_no.setVisibility(View.GONE);
			btn_no.setClickable(false);
		}
		btn_all.setOnClickListener(listener);
		btn_no.setOnClickListener(listener);
		btn_ing.setOnClickListener(listener);
		btn_ed.setOnClickListener(listener);

		btn_all.callOnClick();

		initViews();

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	private void initViews() {
		RecyclerView recyclerview = (RecyclerView)mRecyclerview.getRefreshView();
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(layoutManager);
		recyclerview.setHasFixedSize(true);
		
		mAdapter = new MyTreasureAdapter(lis);
		recyclerview.setAdapter(mAdapter);
		
		mRecyclerview.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDown(float y) {

            }

            @Override
            public void onRefresh() {
            	ActivityMyPrecious.this.onRefresh();
            }

            @Override
            public void onRefreshOver(Object obj) {
            	
            }
        });
		
	}

	protected void onRefresh() {
		model.setType(type);
		model.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				mRecyclerview.refreshOver(null);
				mAdapter.setData(model.getResult());
			}

			@Override
			public void onError() {
				mRecyclerview.refreshOver(null);
			}
		}, Treadsure_type);
	}

	OnClickListener ivListener = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(ActivityMyPrecious.this, ActivityUserDelails.class));
		};
	};

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mRecyclerview.setToRefreshing();
			setIdentifyBackground(v.getId());
			switch (v.getId()) {
			case R.id.btn_all: {
				Treadsure_type = MyTreasureModel.TYPE_ALL;
				onRefresh();
			}
				break;
			case R.id.btn_no: {
				Treadsure_type = MyTreasureModel.TYPE_ALL;
				onRefresh();
			}
				break;
			case R.id.btn_identifing: {
				Treadsure_type = MyTreasureModel.TYPE_IDENTIFIED;
				onRefresh();

			}
				break;
			case R.id.btn_identified: {
				Treadsure_type = MyTreasureModel.TYPE_IDENTIFYING;
				onRefresh();
			}
				break;

			default:
				break;
			}

		}
	};

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_all: {
			// 先设置背景
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));

			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}

			break;
			
		case R.id.btn_no: {
			// 先设置背景
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
			
			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}

			break;
			

		case R.id.btn_identifing: {
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			
			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
		}
			break;
		case R.id.btn_identified: {
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));

			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}
			break;
		}
	}

	private OnClickListener lis = new OnClickListener() {

		@Override
		public void onClick(View v) {
			TreasureEntity entity = (TreasureEntity) v.getTag();
			CollectionTreasure id = new CollectionTreasure();
			id.treasure_id = entity.treasure_id;
			id.image = entity.image;
			id.name = entity.title;

			Intent mIntent = new Intent(ActivityMyPrecious.this, ActivityUserDelails.class);
			mIntent.putExtra(Const.ENTITY, id);
			startActivity(mIntent);
		}
	};

}
