package com.yingluo.Appraiser.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.InfoEvent;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.http.AskNetWork;
import com.yingluo.Appraiser.http.ResponseIsDel;
import com.yingluo.Appraiser.http.ResponseMyBaby;
import com.yingluo.Appraiser.http.ResponseToken;
import com.yingluo.Appraiser.http.AskNetWork.AskNetWorkCallBack;
import com.yingluo.Appraiser.http.ResponseGood;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.ArticlePresenter;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.refresh.RefreshLayout;
import com.yingluo.Appraiser.ui.activity.ActivitySearch;
import com.yingluo.Appraiser.ui.activity.InformationDetailsActivity;
import com.yingluo.Appraiser.ui.adapter.ArticleAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.ListLoadType;
import com.yingluo.Appraiser.utils.ToastUtils;

import de.greenrobot.event.EventBus;

public class InformationFragment extends BaseFragment implements AskNetWorkCallBack, ListviewLoadListener {

	//-1知识学堂全部|-2新闻公告全部|0知识|1新闻|2公告|3精品|4新品|5辨伪|6人物|7市场活动|8拍卖行情|9诈骗风险
	private ArticleAdapter madapter;

	private ArrayList<ContentInfo> list;

	@ViewInject(R.id.recyclerview1)
	private PullRefreshRecyclerView mRecyclerView;

	//下面是知识学堂的
	@ViewInject(R.id.ll_show_knowledge)
	LinearLayout llKonwledge;
	@ViewInject(R.id.ll_all_info)
	ViewGroup btn_all;
	@ViewInject(R.id.ll_good_info)
	ViewGroup btn_good;
	@ViewInject(R.id.ll_new_info)
	ViewGroup btn_new;
	@ViewInject(R.id.ll_truefalse_info)
	ViewGroup btn_true;
	@ViewInject(R.id.ll_person_info)
	ViewGroup btn_person;

	//新闻公告
	@ViewInject(R.id.ll_show_news)
	LinearLayout llNews;
	@ViewInject(R.id.ll_all_new_info)
	ViewGroup btn_all_news;
	@ViewInject(R.id.ll_activity_new_info)
	ViewGroup btn_activity_news;
	@ViewInject(R.id.ll_buy_new_info)
	ViewGroup btn_buy_news;
	@ViewInject(R.id.ll_pian_new_info)
	ViewGroup btn_pian_news;
	
	//头部切换
	@ViewInject(R.id.tv_info_left) 
	TextView tvLeft;
	@ViewInject(R.id.iv_info_right)
	TextView tvRight;
	
	@ViewInject(R.id.iv_search)
	ImageView search;
	
	//存储数据，用于切换时候显示效果
	private Map<Integer,ViewGroup> knowledgeMap;
	private Map<Integer,ViewGroup> newsMap;
	
	//用于记录现在是哪一个tab
	private int RadioType;
	
	private int page;

	private boolean isFiset = true;

	protected int lastVisableView;
	protected int totalItemCount;
	protected int nextShow = 1;

	private ListLoadType currt = ListLoadType.Nomal;

	private String ground_id = "0";

	private int type;

	private int chooseType;
	private AskNetWork askNewWork;

	private final int KNOWLEDGE = 1;
	private final int NEWSTYPE = 2;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		page = 1;
		chooseType = -1;
		
		askNewWork = new AskNetWork(NetConst.NEW_LIST_INFO, this);
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

	@SuppressLint("NewApi")
	@Override
	protected void initViews(View view) {
		ViewUtils.inject(this, view);
		list = new ArrayList<ContentInfo>();

		search.setVisibility(View.VISIBLE);
		
		RecyclerView recyclerView = (RecyclerView) mRecyclerView.getRefreshView();
		LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
		recyclerView.setLayoutManager(layoutManager);

		mRecyclerView.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
			@Override
			public void onPullDown(float y) {

			}

			@Override
			public void onRefresh() {
				InformationFragment.this.onRefresh();
			}

			@Override
			public void onRefreshOver(Object obj) {

			}
		});
		
		RadioType = KNOWLEDGE;
		
		tvLeft.setText(getResources().getString(R.string.new_str_knowdedge));
		tvRight.setText(getResources().getString(R.string.new_str_news));
		tvLeft.setOnClickListener(listenerTab);
		tvRight.setOnClickListener(listenerTab);
		
		knowledgeMap = new HashMap<Integer, ViewGroup>();
		knowledgeMap.put(R.id.ll_all_info,btn_all);
		knowledgeMap.put(R.id.ll_good_info,btn_good);
		knowledgeMap.put(R.id.ll_new_info,btn_new);
		knowledgeMap.put(R.id.ll_truefalse_info,btn_true);
		knowledgeMap.put(R.id.ll_person_info,btn_person);
		
		btn_all.setOnClickListener(listener);
		btn_good.setOnClickListener(listener);
		btn_new.setOnClickListener(listener);
		btn_true.setOnClickListener(listener);
		btn_person.setOnClickListener(listener);

		newsMap = new HashMap<Integer, ViewGroup>();
		newsMap.put(R.id.ll_all_new_info,btn_all_news);
		newsMap.put(R.id.ll_activity_new_info,btn_activity_news);
		newsMap.put(R.id.ll_buy_new_info,btn_buy_news);
		newsMap.put(R.id.ll_pian_new_info,btn_pian_news);
		
		btn_all_news.setOnClickListener(listener);
		btn_activity_news.setOnClickListener(listener);
		btn_buy_news.setOnClickListener(listener);
		btn_pian_news.setOnClickListener(listener);
		
		btn_all.callOnClick();
		recyclerView.setHasFixedSize(true);
		madapter = new ArticleAdapter(mActivity,list,lisntener,this);
		recyclerView.setAdapter(madapter);
		
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mActivity.startActivity(new Intent(mActivity,ActivitySearch.class));
			}
		});
	}

	@Override
	protected void initDisplay() {
//		if (isFiset) {
//			mRecyclerView.setToRefreshing();
//			onRefresh();
//			isFiset = false;
//		}
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
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
	};

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

	@Override
	public void onRefresh() {
		if(currt != ListLoadType.Refresh)
		{
			currt = ListLoadType.Refresh;
			page = 1;
			askNet();
		}
	}
	
	@Override
	public void onLoadMore() {
		if(currt != ListLoadType.LoadMore) {
			currt = ListLoadType.LoadMore;
			askNet();
		}
	}

	private void askNet() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(NetConst.SID, NetConst.SESSIONID);
		map.put("type", chooseType);
		map.put("page", page);
		askNewWork.ask(HttpRequest.HttpMethod.GET, map);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

	OnClickListener listenerTab = new OnClickListener() {

		@SuppressLint("NewApi")
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.tv_info_left) {
				//点击了左边的按钮
				if(RadioType != KNOWLEDGE) {
					RadioType = KNOWLEDGE;
					llKonwledge.setVisibility(View.VISIBLE);
					llNews.setVisibility(View.GONE);
					tvLeft.setBackgroundResource(R.drawable.knowledge_press);
					tvLeft.setTextColor(getResources().getColor(R.color.home_head_color));
					tvRight.setBackgroundResource(R.drawable.news_normal);
					tvRight.setTextColor(getResources().getColor(R.color.wite));
					btn_all.callOnClick();
				}
			} else {
				//点击了右边的按钮
				if(RadioType != NEWSTYPE) {
					RadioType = NEWSTYPE;
					llKonwledge.setVisibility(View.GONE);
					llNews.setVisibility(View.VISIBLE);
					tvRight.setBackgroundResource(R.drawable.news_press);
					tvRight.setTextColor(getResources().getColor(R.color.home_head_color));
					tvLeft.setBackgroundResource(R.drawable.knowledge_normal);
					tvLeft.setTextColor(getResources().getColor(R.color.wite));
					btn_all_news.callOnClick();
				}
			}
		}
	};
	
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			setIdentifyBackground(v.getId());
			switch (v.getId()) {
			//下面是知识学堂
			case R.id.ll_all_info: {
				// 全部
				chooseType = -1;
			}
				break;
			case R.id.ll_good_info: {
				// 精品
				chooseType = 3;
			}
				break;
			case R.id.ll_new_info: {
				// 新品
				chooseType = 4;
			}
				break;
			case R.id.ll_truefalse_info: {
				// 辨伪
				chooseType = 5;
			}
				break;
			case R.id.ll_person_info: {
				// 人物
				chooseType = 6;
			}
				break;
				
			//下面是新闻公告	
			case R.id.ll_all_new_info: {
				// 全部
				chooseType = -2;
			}
				break;
			case R.id.ll_activity_new_info: {
				// 市场活动
				chooseType = 7;
			}
				break;
			case R.id.ll_buy_new_info: {
				// 拍卖行情
				chooseType = 8;
			}
				break;
			case R.id.ll_pian_new_info: {
				// 诈骗风险
				chooseType = 9;
			}
				break;

			default:
				break;
			}
			mRecyclerView.setToRefreshing();
		}	
	};

	public void setIdentifyBackground(int id) {
		if(RadioType == KNOWLEDGE) {
			for (int key : knowledgeMap.keySet()) {
				ViewGroup view = knowledgeMap.get(key);
				if(key == id) {
					view.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
				} else {
					view.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
				}
			}
		} else {
			for (int key : newsMap.keySet()) {
				ViewGroup view = newsMap.get(key);
				if(key == id) {
					view.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
				} else {
					view.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
				}
			}
		}
	}

	@Override
	public void getNetWorkMsg(String msg, String param) throws JSONException {
		
		if(currt == ListLoadType.Refresh) {
			madapter.setFootType(0);
			list.clear();
			mRecyclerView.refreshOver(null);
		}
		ResponseGood rg = new Gson().fromJson(msg, ResponseGood.class);
		if (rg.getCode() == 100000) {
			page = rg.getData().getNextPage();
			List<ContentInfo> res = rg.getData().getList();
			if(res.size() == 0) {
				madapter.setFootType(2);
			} else {
				madapter.setFootType(0);
				list.addAll(res);
			}
			madapter.setListData(list);
		}
		
		currt = ListLoadType.Nomal;
		madapter.notifyDataSetChanged();
	}

	@Override
	public void getNetWorkMsgError(String msg, String param) throws JSONException {

		new ToastUtils(mActivity, "网络异常");
		mRecyclerView.refreshOver(null);
		madapter.setFootType(0);
	}

}
