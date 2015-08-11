package com.yingluo.Appraiser.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.http.AskNetWork;
import com.yingluo.Appraiser.http.ResponseNewHome;
import com.yingluo.Appraiser.http.AskNetWork.AskNetWorkCallBack;
import com.yingluo.Appraiser.http.ResponseMyIdentify;
import com.yingluo.Appraiser.http.ResponseMyIdentify.userIdentify;
import com.yingluo.Appraiser.http.ResponseNewHome.HomeItem;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.model.getUserByIdModel;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.refresh.RefreshLayout;
import com.yingluo.Appraiser.ui.adapter.OtherTreasureAdapter;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter.ClickTabListener;
import com.yingluo.Appraiser.ui.adapter.OtherIdentifyAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.CircleImageView;

/**
 * 个人详情页面
 * 
 * @author xy418
 *
 */
public class ActivityHotIdentiy extends BaseActivity implements OnClickListener, ClickTabListener, AskNetWorkCallBack {

	BitmapsUtils bitmapUtils;
	@ViewInject(R.id.btn_back)
	private View btn_back;

	@ViewInject(R.id.button_text_title)
	private Button tv_text_title;

	@ViewInject(R.id.context)
	private WebView context;

	// 两个不同的切换
	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_identifing;
	@ViewInject(R.id.btn_identified)
	ViewGroup btn_identified;

	CollectionTreasure entity;

	UserInfo userinfo;

	@ViewInject(R.id.btn_msg)
	Button btn_msg;

	//两个不同的view，用于显示不同的分类
	@ViewInject(R.id.recyclerview)
	PullRefreshRecyclerView mRecyclerview;
	@ViewInject(R.id.recyclerview_identify)
	PullRefreshRecyclerView mRecyclerviewIdentify;
	
	// 专家和平常人不同的布局
	@ViewInject(R.id.rl_zhuanjia)
	private RelativeLayout zhuanjia;
	@ViewInject(R.id.iv_zj_icon)
	private CircleImageView iv_zj_icon;
	@ViewInject(R.id.tv_zj_name)
	private TextView tv_zj_name;
	@ViewInject(R.id.tv_zj_grade_name)
	private TextView tv_zj_grade_name;
	@ViewInject(R.id.iv_zj_grade)
	private ImageView iv_zj_grade;

	@ViewInject(R.id.rl_person)
	private RelativeLayout person;
	@ViewInject(R.id.iv_icon)
	private CircleImageView iv_icon;
	@ViewInject(R.id.tv_name)
	private TextView tv_name;
	@ViewInject(R.id.tv_grade_name)
	private TextView tv_grade_name;
	@ViewInject(R.id.iv_grade)
	private ImageView iv_grade;

	OtherTreasureAdapter mAdapter = null;
	OtherIdentifyAdapter mIdentifyAdapter = null;
	
	getUserByIdModel userinfoModel = null;

	private int chooseType;
	private boolean isRefresh;
	
	private int treasurePage, identifyPage;

	private AskNetWork askNetWork;

	private List<HomeItem> treasureList;
	private List<userIdentify> identifyList;
	
	private RecyclerView recyclerview;
	private RecyclerView recyclerviewIdentify;
	
	@OnClick(R.id.btn_msg)
	public void doClick(View view) {
		if (ItApplication.getcurrnUser() != null) {
			String userid = String.valueOf(entity.user_id);
			RongImUtils.getInstance().startPrivateChat(this, userid, entity.authName);
		} else {
			Intent intent = new Intent(ActivityHotIdentiy.this, LoginAcitivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.toast_in, R.anim.hold);
		}

	}

	OnClickListener identifyListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(isRefresh) {
				return ;
			}
			// 先设置背景
			setIdentifyBackground(v.getId());
			chooseType = v.getId();
			switch (v.getId()) {
				case R.id.btn_identifing: {
					treasurePage = 1;
					mRecyclerviewIdentify.setVisibility(View.GONE);
					mRecyclerview.setVisibility(View.VISIBLE);
					if (userinfo != null) {
						if (userinfo.getIs_system() == 1) {
							context.setVisibility(View.GONE);
						} else {
							mRecyclerview.setToRefreshing();
						}
					}
				}
				break;
	
				case R.id.btn_identified: {
					identifyPage = 1;
					mRecyclerviewIdentify.setVisibility(View.VISIBLE);
					mRecyclerview.setVisibility(View.GONE);
					if(userinfo != null) {
						context.setVisibility(View.GONE);
						mRecyclerviewIdentify.setToRefreshing();
					}
				}
				break;
			}

		}
	};

	private Dialog loaddialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_first_page_user);
		ViewUtils.inject(this);
		
		treasureList = new ArrayList<ResponseNewHome.HomeItem>();
		identifyList = new ArrayList<userIdentify>();
		
		isRefresh = false;
		
		bitmapUtils = BitmapsUtils.getInstance();
		entity = (CollectionTreasure) getIntent().getSerializableExtra(Const.ENTITY);

		askNetWork = new AskNetWork(this);
		btn_back.setOnClickListener(this);
		btn_identifing.setOnClickListener(identifyListener);
		btn_identified.setOnClickListener(identifyListener);

		userinfoModel = new getUserByIdModel();
		userinfoModel.getUserInfoForId(entity.user_id, listener);
		initViews();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	private void initViews() {
		if (entity == null) {
			return;
		}

		recyclerview = (RecyclerView) mRecyclerview.getRefreshView();
		recyclerviewIdentify = (RecyclerView) mRecyclerviewIdentify.getRefreshView();
		
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(layoutManager);
		recyclerview.setHasFixedSize(true);
		LinearLayoutManager layoutManagerIdentify = new LinearLayoutManager(this);
		layoutManagerIdentify.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerviewIdentify.setLayoutManager(layoutManagerIdentify);
		recyclerviewIdentify.setHasFixedSize(true);
		
		mRecyclerview.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
			@Override
			public void onPullDown(float y) {

			}

			@Override
			public void onRefresh() {
				isRefresh = true;
				askNet();
			}

			@Override
			public void onRefreshOver(Object obj) {

			}
		});

		mRecyclerviewIdentify.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
			@Override
			public void onPullDown(float y) {

			}

			@Override
			public void onRefresh() {
				isRefresh = true;
				askNet();
			}

			@Override
			public void onRefreshOver(Object obj) {

			}
		});
		
		mAdapter = new OtherTreasureAdapter(this, this);
		mIdentifyAdapter = new OtherIdentifyAdapter(this);
		
		recyclerview.setAdapter(mAdapter);
		recyclerviewIdentify.setAdapter(mIdentifyAdapter);
		
		context.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		context.getSettings().setDomStorageEnabled(true);
		context.getSettings().setDatabaseEnabled(true);
		context.getSettings().setDatabasePath(FileUtils.getInstance().getUpImage());
		context.getSettings().setAppCachePath(FileUtils.getInstance().getUpImage());
		context.getSettings().setAppCacheEnabled(true);

		if (loaddialog == null)
			loaddialog = DialogUtil.createLoadingDialog(this, "加载数据中...");
		loaddialog.show();

	}

	public void askNet() {
		if(chooseType == R.id.btn_identifing) {
			//第一个tab，他的宝物
			askNetWork.setParam(NetConst.HES_BABY);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(NetConst.SID, NetConst.SESSIONID);
			map.put("page", treasurePage);
			map.put("user_id", entity.user_id);
			askNetWork.ask(HttpRequest.HttpMethod.GET, map);
		} else {
			//第二个tab,他的鉴定
			askNetWork.setParam(NetConst.HES_IDENTIFY);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(NetConst.SID, NetConst.SESSIONID);
			map.put("page", identifyPage);
			map.put("user_id", entity.user_id);
			askNetWork.ask(HttpRequest.HttpMethod.GET, map);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;

		default:
			break;
		}
	}

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_identifing: {
			// 先设置背景
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_identifing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_identified.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_identified: {
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_identifing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_identified.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
		}
			break;
		}
	}

	private onBasicView<UserInfo> listener = new onBasicView<UserInfo>() {

		@SuppressLint("NewApi")
		@Override
		public void onSucess(UserInfo data) {

			if (data != null) {
				userinfo = data;
				SqlDataUtil.getInstance().saveUserInfo(userinfo);
				if (userinfo.getIs_system() == 1) {
					tv_text_title.setText(R.string.other_info_text);
					zhuanjia.setVisibility(View.VISIBLE);
					bitmapUtils.display(iv_zj_icon, userinfo.getAvatar());
					tv_zj_name.setText(userinfo.getNickname());
					// TODO 缺一个名字
					if (loaddialog != null && loaddialog.isShowing()) {
						loaddialog.dismiss();
					}
					if (userinfo.getDescription() != null) {
						context.getSettings().setJavaScriptEnabled(true);
						context.loadData("<html>" + userinfo.getDescription() + "</html>", "text/html; charset=UTF-8",
								null);
					} else {
						new ToastUtils(ActivityHotIdentiy.this, "暂无介绍");
					}
				} else {
					tv_text_title.setText(R.string.other_identitycollectinfo_text);
					person.setVisibility(View.VISIBLE);
					context.setVisibility(View.GONE);
					bitmapUtils.display(iv_icon, userinfo.getAvatar());
					tv_name.setText(userinfo.getNickname());
					btn_identifing.callOnClick();
				}

			} else {
				if (loaddialog != null && loaddialog.isShowing()) {
					loaddialog.dismiss();
				}
				new ToastUtils(ActivityHotIdentiy.this, "数据异常");
				ActivityHotIdentiy.this.finish();
			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			if (loaddialog != null && loaddialog.isShowing()) {
				loaddialog.dismiss();
			}
			new ToastUtils(ActivityHotIdentiy.this, errorMsg);
			ActivityHotIdentiy.this.finish();
		}
	};

	@Override
	public void click(HomeItem item, int type) {

	}

	/**
	 * 接口访问成功
	 */
	@Override
	public void getNetWorkMsg(String msg, String param) throws JSONException {
		if (loaddialog != null && loaddialog.isShowing()) {
			loaddialog.dismiss();
		}
		isRefresh = false;
		if (param.equals(NetConst.HES_BABY)) {
			//他的宝物
			mRecyclerview.refreshOver(null);
			ResponseNewHome rg = new Gson().fromJson(msg, ResponseNewHome.class);
			if (rg.getCode() == 100000) {
				treasurePage = rg.getData().getNextPage();
				List<HomeItem> res = rg.getData().getList();
				if(res.size() == 0) {
					return ;
				} else {
					treasureList.addAll(res);
				}
				mAdapter.setData(treasureList);
			} 
			if(rg.getCode() == 100004) {
				new ToastUtils(ActivityHotIdentiy.this, "他还没有宝物");
			}
		} else {
			//他的鉴定
			mRecyclerviewIdentify.refreshOver(null);
			ResponseMyIdentify rg = new Gson().fromJson(msg, ResponseMyIdentify.class);
			if (rg.getCode() == 100000) {
				identifyPage = rg.getData().getNextPage();
				List<userIdentify> res = rg.getData().getList();
				if(res.size() == 0) {
					return ;
				} else {
					identifyList.addAll(res);
				}
				mIdentifyAdapter.setData(identifyList);
			}
			if(rg.getCode() == 100004) {
				new ToastUtils(ActivityHotIdentiy.this, "他还没有鉴定宝物");
			}
		}
	}

	/**
	 * 接口访问失败
	 */
	@Override
	public void getNetWorkMsgError(String msg, String param) throws JSONException {
		isRefresh = false;
		if (param.equals(NetConst.HES_BABY)) {
			mRecyclerview.refreshOver(null);
		}
	}

}
