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
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.MainEvent;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.HomeModel;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.ui.activity.ActivitySearch;
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

public class HomeFragment extends BaseFragment implements OnItemClickListener, OnClickListener {

	private SlideShowView head;

	HomeEntity homeEntity;

	private ImageView btn_search;
	HorizontalListView hsv;

	WellKnowPeopleAdapter adapter;

	ViewChoices choices_0, choices_1, choices_2, choices_3;
	ViewHots viewhots_0, viewhots_1, viewhots_2, viewhots_3;
	ViewArticles articles_0, articles_1;

	ViewGroup layout_home_item0, layout_home_item3, layout_home_item2, layout_home_item1;

	ViewHomeWhoWellKnow wellKnow;

	View tv_goto_infor, tv_goto_identify;

	private int index = 0;

	private PullToRefreshScrollView mScrollView;
	
	private Activity mActivity;
	private HomeModel homeModel;
	private boolean isRefresh;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_home, container, false);
	}
	
	@Override
	protected void initViews(View view) {
		homeModel = new HomeModel();
		isRefresh = false;
		mScrollView = (PullToRefreshScrollView)view.findViewById(R.id.scrollview);
		mScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				isRefresh = true;
				homeModel.sendHttp(new CommonCallBack() {

					@Override
					public void onSuccess() {
						mScrollView.onRefreshComplete();
						homeEntity = homeModel.getResult();
						setDate();
					}

					@Override
					public void onError() {
						String str=FileUtils.getInstance().getJsonStringForJson(FileUtils.JSON_HOME);
						if(str!=null){
							try {
								homeModel.analyzeData(str);
								homeEntity = homeModel.getResult();
								setDate();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
			}
		});
		
		layout_home_item0 = (ViewGroup) view.findViewById(R.id.layout_home_item0);
		layout_home_item3 = (ViewGroup) view.findViewById(R.id.layout_home_item3);
		layout_home_item2 = (ViewGroup) view.findViewById(R.id.layout_home_item2);
		layout_home_item1 = (ViewGroup) view.findViewById(R.id.layout_home_item1);
		// 精品藏品
		choices_0 = (ViewChoices) view.findViewById(R.id.choices_0);
		choices_1 = (ViewChoices) view.findViewById(R.id.choices_1);
		choices_2 = (ViewChoices) view.findViewById(R.id.choices_2);
		choices_3 = (ViewChoices) view.findViewById(R.id.choices_3);
		// 热门鉴定
		viewhots_0 = (ViewHots) view.findViewById(R.id.viewhots_0);
		viewhots_1 = (ViewHots) view.findViewById(R.id.viewhots_1);
		viewhots_2 = (ViewHots) view.findViewById(R.id.viewhots_2);
		viewhots_3 = (ViewHots) view.findViewById(R.id.viewhots_3);
		// 不知道做什么的
		articles_0 = (ViewArticles) view.findViewById(R.id.articles_0);
		articles_1 = (ViewArticles) view.findViewById(R.id.articles_1);

		// 知名专家的第一行
		wellKnow = (ViewHomeWhoWellKnow) view.findViewById(R.id.wellKnow);

		wellKnow.setListener(user_listener);
		tv_goto_infor = view.findViewById(R.id.tv_goto_infor);
		tv_goto_identify = view.findViewById(R.id.tv_goto_identiy);
		tv_goto_identify.setOnClickListener(this);
		tv_goto_infor.setOnClickListener(this);
		// 顶部轮播图
		head = (SlideShowView) view.findViewById(R.id.imageViewpage);

		// 知名专家第二行
		hsv = (HorizontalListView) view.findViewById(R.id.hs_people);
		hsv.setOnItemClickListener(this);
		btn_search = (ImageView)view.findViewById(R.id.bt_sure);
		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ActivitySearch.class));
				mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			}
		});

		homeEntity = ((ItApplication) getActivity().getApplication()).getHomeEntity();
		setDate();

	}


	private void setDate() {
		if (homeEntity == null) {
			// 说明没有下载数据，就要去联网加载
			hideViews(true, (short) 7);
		} else {
			hideViews(false, (short) 7);
			if (homeEntity.getAdvertising() != null && homeEntity.getAdvertising().size() > 0 && !isRefresh) {
				head.prepareData(homeEntity.getAdvertising());
			} else {
				
			}
			try {
				if (homeEntity.getChoices() != null && homeEntity.getChoices().size() > 0) {
					if (homeEntity.getChoices().size() == 1) {
						choices_0.setItem(homeEntity.getChoices().get(0));
						choices_1.setVisibility(View.GONE);
						choices_2.setVisibility(View.GONE);
						choices_3.setVisibility(View.GONE);
					}
					if (homeEntity.getChoices().size() == 2) {
						choices_0.setItem(homeEntity.getChoices().get(0));
						choices_1.setItem(homeEntity.getChoices().get(1));
						choices_2.setVisibility(View.GONE);
						choices_3.setVisibility(View.GONE);
					}
					if (homeEntity.getChoices().size() == 3) {
						choices_0.setItem(homeEntity.getChoices().get(0));
						choices_1.setItem(homeEntity.getChoices().get(1));
						choices_2.setItem(homeEntity.getChoices().get(2));
						choices_3.setVisibility(View.GONE);
					}
					if (homeEntity.getChoices().size() == 4) {
						choices_0.setItem(homeEntity.getChoices().get(0));
						choices_1.setItem(homeEntity.getChoices().get(1));
						choices_2.setItem(homeEntity.getChoices().get(2));
						choices_3.setItem(homeEntity.getChoices().get(3));
					}
				} else {
					hideViews(true, (short) 0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				hideViews(true, (short) 0);
			}
			try {
				if (homeEntity.getHots() != null && homeEntity.getHots().size() > 0) {
					if (homeEntity.getHots().size() == 1) {
						viewhots_0.setItem(homeEntity.getHots().get(0));
						viewhots_1.setVisibility(View.GONE);
						viewhots_2.setVisibility(View.GONE);
						viewhots_3.setVisibility(View.GONE);
					}
					if (homeEntity.getHots().size() == 2) {
						viewhots_0.setItem(homeEntity.getHots().get(0));
						viewhots_1.setItem(homeEntity.getHots().get(1));
						viewhots_2.setVisibility(View.GONE);
						viewhots_3.setVisibility(View.GONE);
					}
					if (homeEntity.getHots().size() == 3) {
						viewhots_0.setItem(homeEntity.getHots().get(0));
						viewhots_1.setItem(homeEntity.getHots().get(1));
						viewhots_2.setItem(homeEntity.getHots().get(2));
						viewhots_3.setVisibility(View.GONE);
					}
					if (homeEntity.getHots().size() == 4) {
						viewhots_0.setItem(homeEntity.getHots().get(0));
						viewhots_1.setItem(homeEntity.getHots().get(1));
						viewhots_2.setItem(homeEntity.getHots().get(2));
						viewhots_3.setItem(homeEntity.getHots().get(3));
					}

				} else {
					hideViews(true, (short) 1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				hideViews(true, (short) 1);
			}
			if (homeEntity.getArticles() != null && homeEntity.getArticles().size() >= 2) {
				articles_0.setItem(homeEntity.getArticles().get(0));
				articles_1.setItem(homeEntity.getArticles().get(1));
			} else {
				hideViews(true, (short) 2);
			}
			if (homeEntity.getAuthors() != null && homeEntity.getAuthors().size() > 0) {
				adapter = new WellKnowPeopleAdapter(getActivity(), homeEntity.getAuthors());
				hsv.setAdapter(adapter);
				if (adapter.getCount() > 0) {// 设置第一个选中
					index = 0;
					wellKnow.setItem(adapter.getItem(index));
					adapter.notifyDataSetChanged();
					new Thread(myWork).start();
				}
			} else {
				hideViews(true, (short) 3);
			}

		}
	}
	
	@Override
	protected void initDisplay() {
	}

	@Override
	public void lazyLoad() {
//		if (mScrollView != null) {
//			mhandler.post(new Runnable() {
//				@Override
//				public void run() {
//					mScrollView.scrollTo(0, 0);
//				}
//			});
//		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (adapter != null) {
			Intent mIntent = new Intent(mActivity, ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, homeEntity.getAuthors().get(position));
			mActivity.startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
	}

	public Runnable myWork = new Runnable() {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(5000);
					mhandler.sendEmptyMessage(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:// 更新页面
				if (adapter.getCount() > 0) {
					index++;
					if (index >= adapter.getCount()) {
						index = 0;
					}
//					wellKnow.setItem(adapter.getItem(index));
					adapter.notifyDataSetChanged();
				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_goto_infor:
			EventBus.getDefault().post(new MainEvent(3, ""));
			break;

		case R.id.tv_goto_identiy:
			EventBus.getDefault().post(new MainEvent(2, ""));
			break;
		}
	}

	/** 如果没数据，就隐藏 */
	private void hideViews(boolean flag, short type) {
		switch (type) {
		case 0:// 隐藏第一个
			layout_home_item0.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 1:// 隐藏第一个
			layout_home_item1.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 2:// 隐藏第二个
			layout_home_item2.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 3:// 隐藏第三个
			layout_home_item3.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		case 7:// 隐藏全部
			layout_home_item0.setVisibility(flag ? View.GONE : View.VISIBLE);
			layout_home_item1.setVisibility(flag ? View.GONE : View.VISIBLE);
			layout_home_item2.setVisibility(flag ? View.GONE : View.VISIBLE);
			layout_home_item3.setVisibility(flag ? View.GONE : View.VISIBLE);
			break;
		}

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
