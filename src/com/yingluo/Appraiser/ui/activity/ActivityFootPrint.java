package com.yingluo.Appraiser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.deleteMyFootPrintsModel;
import com.yingluo.Appraiser.model.getMyFootPrintsModel;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.refresh.RefreshLayout;
import com.yingluo.Appraiser.ui.adapter.MyFootAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;

public class ActivityFootPrint extends BaseActivity {

	@ViewInject(R.id.btn_back)
	View btn_back;
	@ViewInject(R.id.prrv)
	PullRefreshRecyclerView prrvRe;
	RecyclerView prrv;
	
	@ViewInject(R.id.tv_none)
	TextView tv_none;
	@ViewInject(R.id.iv_loading)
	ImageView iv_loading;
	@ViewInject(R.id.btn_delete)
	View btn_delete;
	@ViewInject(R.id.cancle_all_bt)
	View btn_cancle;
	@ViewInject(R.id.delete_all_bt)
	View delete_all_bt;
	@ViewInject(R.id.layout_delet)
	private RelativeLayout layout_delet;
	@ViewInject(R.id.all_checkbox)
	CheckBox all_checkbox;

	MyFootAdapter mAdapter = null;

	getMyFootPrintsModel model = null;
	deleteMyFootPrintsModel delModel = null;

	private boolean isRefresh;
	/**
	 * 点击跳转
	 */
	private OnClickListener onarcitilis = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CollectionTreasure treasure = (CollectionTreasure) v.getTag();
			ContentInfo contentInfo = new ContentInfo();
			contentInfo.setId(treasure.article_id);
			contentInfo.setTitle(treasure.msg);
			contentInfo.setView_times((int) treasure.viewTimes);
			contentInfo.setImage(treasure.image);
			Intent intent = new Intent(ActivityFootPrint.this, InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, contentInfo);
			startActivity(intent);
		}
	};

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	@OnClick({ R.id.btn_back, R.id.btn_delete, R.id.delete_all_bt, R.id.all_checkbox, R.id.cancle_all_bt })
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;

		case R.id.btn_delete: {
			// 进入删除模式
			mAdapter.setScorll(true);
			mAdapter.notifyDataSetChanged();
			layout_delet.setVisibility(View.VISIBLE);
		}
			break;
		case R.id.all_checkbox: {
			// 全选
			mAdapter.selectAll(all_checkbox.isChecked());
			mAdapter.notifyDataSetChanged();
		}
			break;
		case R.id.cancle_all_bt: {
			// 退出选择模式
			exitDelete();
		}
			break;
		case R.id.delete_all_bt: {
			// 删除
			mAdapter.deleteAll(this, delModel);
		}
			break;
		}
	}

	public void exitDelete() {
		layout_delet.setVisibility(View.GONE);
		mAdapter.setScorll(false);
		mAdapter.notifyDataSetChanged();
		mAdapter.exitDelete();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		prrvRe.setToRefreshing();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_foot_print);
		ViewUtils.inject(this);
		isRefresh = false;
		model = new getMyFootPrintsModel();
		delModel = new deleteMyFootPrintsModel();
		mAdapter = new MyFootAdapter(this,onarcitilis);
		prrv = (RecyclerView)prrvRe.getRefreshView();
		
//		prrvRe.setToRefreshing();
		prrvRe.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDown(float y) {

            }

            @Override
            public void onRefresh() {
//            	prrvRe.refreshOver(null);
            	isRefresh = true;
        		getInfo();
            }

            @Override
            public void onRefreshOver(Object obj) {
            	
            }
        });

		prrv.setAdapter(mAdapter);

		prrv.setVisibility(View.GONE);
		iv_loading.setVisibility(View.VISIBLE);

	}

	public void getInfo() {
		model.sendHttp(new CommonCallBack() {

			@Override
			public void onSuccess() {
				if(isRefresh) {
					isRefresh = false;
					prrvRe.refreshOver(null);
				}
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

			}
		});
	}

}
