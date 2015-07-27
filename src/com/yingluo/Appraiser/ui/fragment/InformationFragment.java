package com.yingluo.Appraiser.ui.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.InfoEvent;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.ArticlePresenter;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.refresh.RefreshLayout;
import com.yingluo.Appraiser.ui.activity.InformationDetailsActivity;
import com.yingluo.Appraiser.ui.activity.KindOfPreciousActivity;
import com.yingluo.Appraiser.ui.adapter.ArticleAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.ListLoadType;
import com.yingluo.Appraiser.utils.ToastUtils;

import de.greenrobot.event.EventBus;

public class InformationFragment extends BaseFragment implements onListView<ContentInfo>, ListviewLoadListener {

	private ArticleAdapter madapter;

	@ViewInject(R.id.home_title)
	private TextView title;

	private ArrayList<ContentInfo> list;

	private ArticlePresenter articlePresenter;

	@ViewInject(R.id.recyclerview1)
	private PullRefreshRecyclerView mRecyclerView;

	protected boolean isLoadMore = false;
	private boolean isRefreshing = true;

	private boolean isFiset = true;

	protected int lastVisableView;
	protected int totalItemCount;
	protected int nextShow = 1;

	private ListLoadType currt = ListLoadType.Nomal;

	private String ground_id = "0";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public void onEventMainThread(InfoEvent event) {
		switch (event.type) {
		case 0:// 刷新
			lazyLoad();
			break;

		default:
			break;
		}
	}

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_info, container, false);
	}

	@Override
	protected void initViews(View view) {
		ViewUtils.inject(this, view);
		title.setText(R.string.information);
		list = new ArrayList<ContentInfo>();
		articlePresenter = new ArticlePresenter(this);
		RecyclerView recyclerView = (RecyclerView)mRecyclerView.getRefreshView();
		LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
		recyclerView.setLayoutManager(layoutManager);
		
		mRecyclerView.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDown(float y) {

            }

            @Override
            public void onRefresh() {
            	if (isRefreshing) {
					InformationFragment.this.onRefresh();
				} else {
					Log.d("helper", "正在刷新中!");
					mRecyclerView.refreshOver(null);
				}
            	
            }

            @Override
            public void onRefreshOver(Object obj) {
            	
            }
        });

		recyclerView.setHasFixedSize(true);

		madapter = new ArticleAdapter(mActivity, list, lisntener, InformationFragment.this);

		recyclerView.setAdapter(madapter);
	}

	
	@Override
	protected void initDisplay() {
		if (isFiset) {
			mRecyclerView.setToRefreshing();
			onRefresh();
			isFiset = false;
		}
	}

	@Override
	public void lazyLoad() {
		if (isFiset) {
			mRecyclerView.setToRefreshing();
			onRefresh();
			isFiset = false;
		}
	}

	/**
	 * 跳转详细文章页面
	 */
	private OnClickListener lisntener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ContentInfo contentInfo = (ContentInfo) v.getTag();
			Intent intent = new Intent(mActivity, InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, contentInfo);
			startActivity(intent);
		}
	};

	@Override
	public void onSucess(ArrayList<ContentInfo> data) {
		if (currt == ListLoadType.LoadMore) {// 加载更多
			addList(data);
		}
		if (currt == ListLoadType.Refresh) {// 刷新
			list = data;
		}
		madapter.setListData(list);
		currt = ListLoadType.Nomal;
		mRecyclerView.refreshOver(null);
		isLoadMore = true;
		isRefreshing = true;
		madapter.notifyDataSetChanged();
		madapter.setFootType(0);

	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		new ToastUtils(mActivity, errorMsg);
		mRecyclerView.refreshOver(null);
		isLoadMore = true;
		isRefreshing = true;
		madapter.setFootType(0);
	}

	/**
	 *
	 * @param data
	 */
	public void addList(ArrayList<ContentInfo> data) {
		if (list.size() == 0) {
			list.addAll(data);
		} else {
			for (int i = 0; i < data.size(); i++) {
				if (!list.contains(data.get(i))) {
					list.add(data.get(i));
				}
			}
		}

	}

	OnScrollListener Scrolllistener = new OnScrollListener() {

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			LayoutManager lm = recyclerView.getLayoutManager();
			if (lm instanceof LinearLayoutManager) {// 竖向
				LinearLayoutManager llm = (LinearLayoutManager) lm;
				lastVisableView = llm.findLastVisibleItemPosition();
				totalItemCount = llm.getItemCount();
				// lastVisableView>=totalItemCount-nextShow表示剩下nextshow个item的时候自动加载
				// dy>0表示向下滑动，dy<0表示向上滑动
				if ((lastVisableView >= totalItemCount - nextShow) && dy > 0) {// 加载更多
					if (isLoadMore) {
						InformationFragment.this.onLoadMore();
					} else {
						Log.d("helper", "正在加载中!");
					}
				}
			}
		}

		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);
			LayoutManager lm = recyclerView.getLayoutManager();
			if (lm instanceof LinearLayoutManager) {// 竖向
				LinearLayoutManager llm = (LinearLayoutManager) lm;
				lastVisableView = llm.findLastVisibleItemPosition();
				totalItemCount = llm.getItemCount();
				// lastVisableView>=totalItemCount-nextShow表示剩下nextshow个item的时候自动加载
				// dy>0表示向下滑动，dy<0表示向上滑动
				if ((lastVisableView >= totalItemCount - nextShow) && newState == RecyclerView.SCROLL_STATE_IDLE) {// 加载更多
					if (isLoadMore) {
						InformationFragment.this.onLoadMore();
					} else {
						Log.d("helper", "正在加载中!");
					}
				}
			}
		};
	};

	@Override
	public void onRefresh() {
		currt = ListLoadType.Refresh;
		isRefreshing = false;
		isLoadMore = false;
		madapter.setFootType(2);
		madapter.notifyItemChanged(list.size());
		int foot = mRecyclerView.getChildCount();
		articlePresenter.getArticleList("0", ground_id);
	}

	@Override
	public void onLoadMore() {
		currt = ListLoadType.LoadMore;
		isLoadMore = false;
		isRefreshing = false;
		mRecyclerView.refreshOver(null);
		madapter.setFootType(1);
		madapter.notifyDataSetChanged();
		articlePresenter.getArticleList("0", ground_id);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_SELECT_TYPE && resultCode == Activity.RESULT_OK) {
			try {
				int kindid = data.getIntExtra(Const.KIND_ID, 0);
				ground_id = String.valueOf(kindid);
				mRecyclerView.setToRefreshing();
				onRefresh();
				isFiset = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
