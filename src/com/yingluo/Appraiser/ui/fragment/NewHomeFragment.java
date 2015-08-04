package com.yingluo.Appraiser.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.MainEvent;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.HomeModel;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.ui.activity.ActivitySearch;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter;
import com.yingluo.Appraiser.ui.adapter.WellKnowPeopleAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.view.SlideShowView;
import com.yingluo.Appraiser.view.home.ViewArticles;
import com.yingluo.Appraiser.view.home.ViewChoices;
import com.yingluo.Appraiser.view.home.ViewHomeWhoWellKnow;
import com.yingluo.Appraiser.view.home.ViewHots;
import com.yingluo.Appraiser.view.listview.HorizontalListView;

import de.greenrobot.event.EventBus;

public class NewHomeFragment extends BaseFragment {

	/**
	 * 定义一些控件
	 */
	private SlideShowView head; //顶部轮播图
	private RelativeLayout rlSearch; //搜索框
	private ImageView ivCategory; //分类的按钮
	private ListView lvHome; //展示数据
	
	private int index = 0;

	private PullToRefreshScrollView mScrollView;
	private NewHomeListAdapter mAdapter;
	
	private Activity mActivity;
	private boolean isRefresh;
	
	private List<CommentEntity> list;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_new_home, container, false);
	}
	
	@Override
	protected void initViews(View view) {
		isRefresh = false;
//		mScrollView = (PullToRefreshScrollView)view.findViewById(R.id.scrollview);
//		mScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
//
//			@Override
//			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//				isRefresh = true;
//			}
//		});
		// 顶部轮播图
		head = (SlideShowView) view.findViewById(R.id.imageViewpage);
		rlSearch = (RelativeLayout) view.findViewById(R.id.rl_search);
		ivCategory = (ImageView) view.findViewById(R.id.iv_category);
		lvHome = (ListView) view.findViewById(R.id.comment_listview);
		
		list=new ArrayList<CommentEntity>();
		CommentEntity each= new CommentEntity();
		list.add(each);
		list.add(each); 
		mAdapter = new NewHomeListAdapter(list,mActivity,null);
		lvHome.setAdapter(mAdapter);
		
		mAdapter.setData(list);
		NewHomeListAdapter.setListViewHeightBasedOnChildren(lvHome);
	}
	
	@Override
	protected void initDisplay() {
	}

	@Override
	public void lazyLoad() {
		
	}

	private OnClickListener user_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			CollectionTreasure entity = (CollectionTreasure) v.getTag();
			Intent mIntent = new Intent(mActivity, ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, entity);
			mActivity.startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
	};
}
