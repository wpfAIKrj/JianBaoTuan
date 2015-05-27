package com.it.ui.activity;

import java.util.ArrayList;

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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.daimajia.swipe.util.Attributes;
import com.it.R;
import com.it.bean.ContentInfo;
import com.it.config.Const;
import com.it.inter.ListviewLoadListener;
import com.it.model.getCollectArticleModel;
import com.it.presenter.ArticlePresenter;
import com.it.presenter.OnListDataLoadListener;
import com.it.presenter.myCollectArticlePresenter;
import com.it.ui.adapter.ArticleAdapter;
import com.it.ui.adapter.MyArticleAdapter;
import com.it.ui.base.BaseActivity;
import com.it.ui.fragment.InformationFragment;
import com.it.utils.ListLoadType;
import com.it.utils.ToastUtils;
import com.it.view.inter.onListView;
import com.it.view.listview.XListView;
import com.it.view.listview.XListView.IXListViewListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
/**
 * 文章加载页面
 * @author Administrator
 *
 */
public class FavoriteArticlesActivity extends BaseActivity implements ListviewLoadListener,onListView<ContentInfo>{

	
	

	@ViewInject(R.id.home_title)
	private TextView title;
	
	
	private MyArticleAdapter madapter;
	private ArrayList<ContentInfo> list;
	
	@ViewInject(R.id.swipe_refresh_widget)
	private SwipeRefreshLayout mSwipeRefreshWidget;
	@ViewInject(R.id.recyclerview1)
	private RecyclerView mRecyclerView;
	
	
	private int length=30;
	
	protected boolean isLoadMore=false;
	private boolean isRefreshing=true;
	
	private boolean isFiset=true;
	
	protected int lastVisableView;
	protected int totalItemCount;
	protected int nextShow=1;
	
	private ListLoadType currt=ListLoadType.Nomal;


	private myCollectArticlePresenter myPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritearticles);
		ViewUtils.inject(this);
		initView();
		initData();
	}

	protected void initView() {
		// TODO Auto-generated method stub
		title.setText(R.string.collect_info_title);
		list=new ArrayList<ContentInfo>();
		myPresenter=new myCollectArticlePresenter(this);
		LayoutManager layoutManager=new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(layoutManager);
		mSwipeRefreshWidget.setDistanceToTriggerSync(200);
		mSwipeRefreshWidget.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {//刷新
				// TODO Auto-generated method stub
				if(isRefreshing){
					FavoriteArticlesActivity.this.onRefresh();
				}else{
					Log.d("helper","正在刷新中!");
					mSwipeRefreshWidget.setRefreshing(false);
				}
			}
		});
		mRecyclerView.setOnScrollListener(Scrolllistener);

		mRecyclerView.setHasFixedSize(true);
		
		madapter=new MyArticleAdapter(this,list,listener,FavoriteArticlesActivity.this);
		madapter.setMode(Attributes.Mode.Single);
		madapter.setScorll(true);
		
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setAdapter(madapter);
	}

	protected void initData() {
		// TODO Auto-generated method stub
//		madapter=new ArticleAdapter(this,list);
//		mlistview.setAdapter(madapter);
//		mlistview.setXListViewListener(this);
//		mlistview.setOnItemClickListener(this);
	}



	@OnClick({R.id.button_delect,R.id.button_category})
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://跳转到
			setResult(RESULT_CANCELED, getIntent());
			finish();
			break;
		case R.id.button_delect://删除模式
			
			break;
		default:
			break;
		}
	}

	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ContentInfo info=(ContentInfo) v.getTag();
			Intent intent=new Intent(FavoriteArticlesActivity.this, InformationDetailsActivity.class);
			intent.putExtra(Const.ArticleId, info);
			startActivity(intent);
		}
	};
	
	@Override
	public void onSucess(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		if(currt==ListLoadType.LoadMore){//加载更多
			addList(data);
		}
		if(currt==ListLoadType.Refresh){//刷新
			if(!data.isEmpty()){ 
				list.clear();
				addList(data);
			}
		}
		madapter.setListData(list);
		currt=ListLoadType.Nomal;
		mSwipeRefreshWidget.setRefreshing(false);
		isLoadMore=true;
		isRefreshing=true;
		madapter.notifyDataSetChanged();
		madapter.setFootType(0);

	}

	
	
	@Override
	public void onFail(String errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		new ToastUtils(this, errorMsg);
		mSwipeRefreshWidget.setRefreshing(false);
		isLoadMore=true;
		isRefreshing=true;
		madapter.setFootType(0);
	}

	/**
	 * 
	 * @param data
	 */
	public void addList(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		if(list.size()==0){
			list.addAll(data);	
		}else {
			for (int i = 0; i < data.size(); i++) {
				if(!list.contains(data.get(i))){
					list.add(data.get(i));
				}
			}
		}
	}
	OnScrollListener Scrolllistener=new OnScrollListener() {
		
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			// TODO Auto-generated method stub
			super.onScrolled(recyclerView, dx, dy);
//			LayoutManager lm = recyclerView.getLayoutManager();
//			if(lm instanceof LinearLayoutManager){//竖向
//				LinearLayoutManager llm=(LinearLayoutManager) lm;
//				lastVisableView=llm.findLastVisibleItemPosition();
//				totalItemCount=llm.getItemCount();
//				//lastVisableView>=totalItemCount-nextShow表示剩下nextshow个item的时候自动加载
//				//dy>0表示向下滑动，dy<0表示向上滑动
//				if((lastVisableView>=totalItemCount-nextShow)&&dy>0){//加载更多
//					if(isLoadMore){
//						FavoriteArticlesActivity.this.onLoadMore();
//					}else{
//						Log.d("helper","正在加载中!");
//					}
//				}
//			}
		}


		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);

			
		};
	};
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		currt=ListLoadType.Refresh;
		isRefreshing=false;
		isLoadMore=false;
		madapter.setFootType(2);
		madapter.notifyItemChanged(list.size());
		int foot=mRecyclerView.getChildCount();
		myPresenter.getArticleList("0", length);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		currt=ListLoadType.LoadMore;
		isLoadMore=false;
		isRefreshing=false;
		mSwipeRefreshWidget.setRefreshing(false);
		madapter.setFootType(1);
		madapter.notifyDataSetChanged();
		myPresenter.getArticleList("0", (length+1));
	}



}
