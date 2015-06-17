package com.yingluo.Appraiser.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.getMyFootPrintsModel;
import com.yingluo.Appraiser.ui.adapter.IdentiyAdapter;
import com.yingluo.Appraiser.ui.adapter.MyFootAdapter;
import com.yingluo.Appraiser.view.PullRefreshRecyclerView;
import com.yingluo.Appraiser.view.PullRefreshRecyclerView.RefreshLoadMoreListener;

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

	MyFootAdapter mAdapter = null;

	getMyFootPrintsModel model = null;
	private OnClickListener onarcitilis=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			
			CollectionTreasure treasure=(CollectionTreasure) v.getTag();
			
			ContentInfo contentInfo=new ContentInfo();
			contentInfo.setId(treasure.article_id);
			Intent intent = new Intent(ActivityFootPrint.this,
					InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, contentInfo);
			startActivity(intent);
		}
	};

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
		mAdapter = new MyFootAdapter(onarcitilis);

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
