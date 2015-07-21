package com.yingluo.Appraiser.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.InfoEvent;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.ArticlePresenter;
import com.yingluo.Appraiser.ui.activity.InformationDetailsActivity;
import com.yingluo.Appraiser.ui.activity.KindOfPreciousActivity;
import com.yingluo.Appraiser.ui.adapter.ArticleAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.ListLoadType;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.listview.XListView;
import com.yingluo.Appraiser.view.listview.XListView.IXListViewListener;

import de.greenrobot.event.EventBus;

public class InformationFragment extends BaseFragment implements onListView<ContentInfo>, ListviewLoadListener {

	private ArticleAdapter madapter;

	@ViewInject(R.id.button_category)
	private ImageView back;
	@ViewInject(R.id.home_title)
	private TextView title;

	private ArrayList<ContentInfo> list;

	private ArticlePresenter articlePresenter;

	@ViewInject(R.id.swipe_refresh_widget)
	private SwipeRefreshLayout mSwipeRefreshWidget;
	@ViewInject(R.id.recyclerview1)
	private RecyclerView mRecyclerView;

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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
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

	@OnClick({ R.id.button_category })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_category:
			Intent intent = new Intent(mActivity, KindOfPreciousActivity.class);
			mActivity.startActivityForResult(intent, Const.TO_SELECT_TYPE);
			break;

		default:
			break;
		}
	}

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_info, container, false);
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		ViewUtils.inject(this, view);
		title.setText(R.string.information);
		list = new ArrayList<ContentInfo>();
		articlePresenter = new ArticlePresenter(this);
		LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
		mRecyclerView.setLayoutManager(layoutManager);
		mSwipeRefreshWidget.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {// 刷新
				// TODO Auto-generated method stub
				if (isRefreshing) {
					InformationFragment.this.onRefresh();
				} else {
					Log.d("helper", "正在刷新中!");
					mSwipeRefreshWidget.setRefreshing(false);
				}
			}
		});
		// mRecyclerView.setOnScrollListener(Scrolllistener);

		mRecyclerView.setHasFixedSize(true);

		madapter = new ArticleAdapter(mActivity, list, lisntener, InformationFragment.this);

		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setAdapter(madapter);
	}

	/**
	 * 刷新页面
	 */
	public void refresh() {
		lazyLoad();
	}
	
	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub

		if (isFiset) {
			mSwipeRefreshWidget.setRefreshing(true);
			onRefresh();
			isFiset = false;
		}
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		if (isFiset) {
			mSwipeRefreshWidget.setRefreshing(true);
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
			// TODO Auto-generated method stub
			ContentInfo contentInfo = (ContentInfo) v.getTag();
			Intent intent = new Intent(mActivity, InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, contentInfo);
			startActivity(intent);
		}
	};

	@Override
	public void onSucess(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		if (currt == ListLoadType.LoadMore) {// 加载更多
			addList(data);
		}
		if (currt == ListLoadType.Refresh) {// 刷新
			list = data;
		}
		madapter.setListData(list);
		currt = ListLoadType.Nomal;
		mSwipeRefreshWidget.setRefreshing(false);
		isLoadMore = true;
		isRefreshing = true;
		madapter.notifyDataSetChanged();
		madapter.setFootType(0);

	}

	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		new ToastUtils(mActivity, errorMsg);
		mSwipeRefreshWidget.setRefreshing(false);
		isLoadMore = true;
		isRefreshing = true;
		madapter.setFootType(0);
	}

	/**
	 *
	 * @param data
	 */
	public void addList(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		currt = ListLoadType.LoadMore;
		isLoadMore = false;
		isRefreshing = false;
		mSwipeRefreshWidget.setRefreshing(false);
		madapter.setFootType(1);
		madapter.notifyDataSetChanged();
		articlePresenter.getArticleList("0", ground_id);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_SELECT_TYPE && resultCode == Activity.RESULT_OK) {
			try {
				int kindid = data.getIntExtra(Const.KIND_ID, 0);
				ground_id = String.valueOf(kindid);
				mSwipeRefreshWidget.setRefreshing(true);
				onRefresh();
				isFiset = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
