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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.MainEvent;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.http.AskNetWork;
import com.yingluo.Appraiser.http.ResponseGood;
import com.yingluo.Appraiser.http.ResponseNewHome;
import com.yingluo.Appraiser.http.AskNetWork.AskNetWorkCallBack;
import com.yingluo.Appraiser.http.ResponseBanner;
import com.yingluo.Appraiser.http.ResponseNewHome.HomeItem;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.HomeModel;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.ui.activity.ActivityIdentifyByMe;
import com.yingluo.Appraiser.ui.activity.ActivitySearch;
import com.yingluo.Appraiser.ui.activity.ActivityUserDelails;
import com.yingluo.Appraiser.ui.activity.KindOfPreciousActivity;
import com.yingluo.Appraiser.ui.activity.LoginAcitivity;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter.ClickTabListener;
import com.yingluo.Appraiser.ui.adapter.WellKnowPeopleAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.InputMessageDialog;
import com.yingluo.Appraiser.view.InputMessageDialog.SendMessageCallback;
import com.yingluo.Appraiser.view.SlideShowView;
import com.yingluo.Appraiser.view.home.ViewArticles;
import com.yingluo.Appraiser.view.home.ViewChoices;
import com.yingluo.Appraiser.view.home.ViewHomeWhoWellKnow;
import com.yingluo.Appraiser.view.home.ViewHots;
import com.yingluo.Appraiser.view.listview.HorizontalListView;

import de.greenrobot.event.EventBus;

public class NewHomeFragment extends BaseFragment implements AskNetWorkCallBack,ClickTabListener,SendMessageCallback  {

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
	
	private ScrollView scrollView;
	
	private Activity mActivity;
	private boolean isRefresh;
	private boolean isLoadMore;
	
	private List<HomeItem> list;
	
	//用于记录现在是哪一个tab
	private int RadioType;
	private TextView tvLeft;
	private TextView tvRight;
	
	private final int KNOWLEDGE = 2;
	private final int NEWSTYPE = 1;
	
	private ImageView search;
	
	private int page;
	private AskNetWork askNewWork;
	private AskNetWork askNewWorkBanner;
	private InputMessageDialog inputMessage;
	private HomeItem item;
	
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
	public void onResume() {
		super.onResume();
		final int[] location = new int[2];   
		mScrollView.getLocationOnScreen(location);  
		int y = location[1];
	}

	@Override
	protected void initViews(View view) {
		isRefresh = isLoadMore = false;
		mScrollView = (PullToRefreshScrollView)view.findViewById(R.id.scrollview);
		inputMessage = new InputMessageDialog(mActivity, this);
		scrollView = mScrollView.getRefreshableView();
		
		mScrollView.setOnRefreshListener(new OnRefreshListener2<ScrollView>(){

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
				if(!isRefresh) {
					scrollView.fullScroll(ScrollView.FOCUS_UP);
					isRefresh = true;
					page = 1;
					askNet();
				}
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {	
				if(!isLoadMore) {
					isLoadMore = true;
					askNet();
				}
			}

		});
		
		page = 1;
		askNewWork = new AskNetWork(this);
		askNewWork.setParam(NetConst.NEW_NEW_HOME_IDENTIFYED);
		
		search = (ImageView) view.findViewById(R.id.iv_search);
		search.setVisibility(View.GONE);
		// 顶部轮播图
		head = (SlideShowView) view.findViewById(R.id.imageViewpage);
		
		rlSearch = (RelativeLayout) view.findViewById(R.id.rl_search);
		rlSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent(mActivity, ActivitySearch.class);
				startActivity(mIntent);
			}
		});
		
		ivCategory = (ImageView) view.findViewById(R.id.iv_category);
		lvHome = (ListView) view.findViewById(R.id.comment_listview);
		
		RadioType = KNOWLEDGE;
		tvLeft = (TextView) view.findViewById(R.id.tv_info_left);
		tvRight = (TextView) view.findViewById(R.id.iv_info_right);
		tvLeft.setOnClickListener(listenerTab);
		tvRight.setOnClickListener(listenerTab);
		tvLeft.setText("已鉴定");
		tvRight.setText("鉴定中");
		
		list=new ArrayList<HomeItem>();
		
		mAdapter = new NewHomeListAdapter(RadioType,list,mActivity,this);
		lvHome.setAdapter(mAdapter);
		
		NewHomeListAdapter.setListViewHeightBasedOnChildren(lvHome);
		askNet();
		askNewWorkBanner = new AskNetWork(this);
		askNewWorkBanner.setParam(NetConst.NEW_HOMW_BANNER);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(NetConst.SID, NetConst.SESSIONID);
		askNewWorkBanner.ask(HttpRequest.HttpMethod.GET, map);
	}
	
	private void askNet() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(NetConst.SID, NetConst.SESSIONID);
		map.put("page", page);
		askNewWork.ask(HttpRequest.HttpMethod.GET, map);
	}
	
	OnClickListener listenerTab = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.tv_info_left) {
				//点击了左边的按钮
				if(RadioType != KNOWLEDGE) {
					RadioType = KNOWLEDGE;
					tvLeft.setBackgroundResource(R.drawable.knowledge_press);
					tvLeft.setTextColor(getResources().getColor(R.color.home_head_color));
					tvRight.setBackgroundResource(R.drawable.news_normal);
					tvRight.setTextColor(getResources().getColor(R.color.wite));
					askNewWork.setParam(NetConst.NEW_NEW_HOME_IDENTIFYED);
					mScrollView.setRefreshing();
				}
			} else {
				//点击了右边的按钮
				if(RadioType != NEWSTYPE) {
					RadioType = NEWSTYPE;
					tvRight.setBackgroundResource(R.drawable.news_press);
					tvRight.setTextColor(getResources().getColor(R.color.home_head_color));
					tvLeft.setBackgroundResource(R.drawable.knowledge_normal);
					tvLeft.setTextColor(getResources().getColor(R.color.wite));
					askNewWork.setParam(NetConst.NEW_NEW_HOME_IDENTIFYING);
					mScrollView.setRefreshing();
				}
			}
		}
	};
	
	@Override
	protected void initDisplay() {
	}

	@Override
	public void lazyLoad() {
	}

	private OnClickListener user_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CollectionTreasure entity = (CollectionTreasure) v.getTag();
			Intent mIntent = new Intent(mActivity, ActivityHotIdentiy.class);
			mIntent.putExtra(Const.ENTITY, entity);
			mActivity.startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
	};

	@Override
	public void getNetWorkMsg(String msg, String param) throws JSONException {
		if(param.equals(NetConst.NEW_HOMW_BANNER)) {		
			ResponseBanner rg = new Gson().fromJson(msg, ResponseBanner.class);
			if(rg.getCode() == 100000) {
				head.prepareData(rg.getData());
			}	
		} else if(param.equals(NetConst.NEW_SEND_MESSAGE)){
			
		} else {
			if(isRefresh) {
				//下拉刷新了
				isRefresh = false;
				list.clear();
			}
			if(isLoadMore) {
				//上拉加载更多了
				isLoadMore = false;
				
			}
			mScrollView.onRefreshComplete();
			ResponseNewHome rg = new Gson().fromJson(msg, ResponseNewHome.class);
			if (rg.getCode() == 100000) {
				page = rg.getData().getNextPage();
				List<HomeItem> res = rg.getData().getList();
				if(res.size() == 0) {
					return ;
				} else {
					list.addAll(res);
				}
				mAdapter.setData(RadioType,list);
				NewHomeListAdapter.setListViewHeightBasedOnChildren(lvHome);
			}
		}
	}

	@Override
	public void getNetWorkMsgError(String msg, String param) throws JSONException {
		new ToastUtils(mActivity, "网络异常");
		if(isRefresh) {
			//下拉刷新了
			isRefresh = false;
		}
		if(isLoadMore) {
			//上拉加载更多了
			isLoadMore = false;
		}
		mScrollView.onRefreshComplete();
	}

	//点击了不同的按钮的响应事件
	@Override
	public void click(HomeItem item, int type) {
		switch(type) {
		case NewHomeListAdapter.TYPE_HEAD:
			//点击了头像
			CollectionTreasure entity = new CollectionTreasure();
			Intent mIntent = new Intent(mActivity, ActivityHotIdentiy.class);
			entity.setUser_id(Long.valueOf(item.getUser_id()));
			entity.setAuthName(item.getUser_name());
			entity.setAuthImage(item.getUser_portrait());
			entity.setCompany("");
			mIntent.putExtra(Const.ENTITY, entity);
			mActivity.startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case NewHomeListAdapter.TYPE_IDENTIFY:
			//点击了鉴定
			if (ItApplication.getcurrnUser() != null) {
				CollectionTreasure entitys = new CollectionTreasure();
				Intent intent = new Intent(mActivity, ActivityIdentifyByMe.class);
				entitys.setTreasure_id(Long.valueOf(item.getTreasure_id()));
				intent.putExtra(Const.ENTITY, entitys);
				startActivityForResult(intent, Const.TO_MY_INDENTITY);
				mActivity.overridePendingTransition(R.anim.toast_in, R.anim.hold);
			} else {
				Intent intent = new Intent(mActivity, LoginAcitivity.class);
				startActivity(intent);
				mActivity.overridePendingTransition(R.anim.toast_in, R.anim.hold);
			}
			break;
		case NewHomeListAdapter.TYPE_COMMIT:
			//点击了评论
			this.item = item;
			inputMessage.show();
			break;
		case NewHomeListAdapter.TYPE_SHARE:
			//点击了分享
			Toast.makeText(mActivity, "点击了分享"+item, Toast.LENGTH_SHORT).show();
			break;
		}
	}

	@Override
	public void sendMessage(String message) {
		if (ItApplication.getcurrnUser() != null) {
//			String content = message.trim();
			if (message != null && message.length() != 0 && this.item != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("treasure_id", item.getTreasure_id());
				map.put("to_user_id", item.getUser_id());
				map.put("comment", message);
				AskNetWork askCommit = new AskNetWork(map, NetConst.NEW_SEND_MESSAGE, this);
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put(NetConst.SID, NetConst.SESSIONID);
				askCommit.ask(maps);
//				sendCommentModel.sendTreasureComment(entity.treasure_id, to_user_id, message);
			} else {
				new ToastUtils(mActivity, "请输入评论内容！");
			}
		} else {
			Intent intent = new Intent(mActivity, LoginAcitivity.class);
			startActivity(intent);
			mActivity.overridePendingTransition(R.anim.toast_in, R.anim.hold);
		}
		
	}
}
