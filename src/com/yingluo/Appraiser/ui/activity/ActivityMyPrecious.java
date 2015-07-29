package com.yingluo.Appraiser.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yingluo.Appraiser.R;

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
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.http.AskNetWork;
import com.yingluo.Appraiser.http.AskNetWork.AskNetWorkCallBack;
import com.yingluo.Appraiser.http.ResponseIsDel;
import com.yingluo.Appraiser.http.ResponseMyBaby;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.MyTreasureModel;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.refresh.RefreshLayout;
import com.yingluo.Appraiser.ui.adapter.MyTreasureAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.ToastUtils;

public class ActivityMyPrecious extends BaseActivity implements AskNetWorkCallBack, ListviewLoadListener {

	@ViewInject(R.id.tv_title)
	TextView title;

	@ViewInject(R.id.btn_all)
	ViewGroup btn_all;
	@ViewInject(R.id.btn_no)
	ViewGroup btn_no;
	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_ing;
	@ViewInject(R.id.btn_identified)
	ViewGroup btn_ed;

	View iv_01, iv_02, iv_03, iv_04;
	@ViewInject(R.id.btn_delete)
	View btn_delete;
	@ViewInject(R.id.btn_back)
	View btn_back;
	@ViewInject(R.id.recyclerview)
	PullRefreshRecyclerView mRecyclerview;

	@ViewInject(R.id.layout_delet)
	private RelativeLayout layout_delet;
	@ViewInject(R.id.all_checkbox)
	private CheckBox allcheckbox;

	private List<String> dels;
	private AskNetWork askNewWork;

	int type = Const.PRECIOUS;

	MyTreasureModel model;

	MyTreasureAdapter mAdapter;

	private int Treadsure_type;

	private List<TreasureEntity> lists;
	//用于访问接口
	private Long uid;
	
	@OnClick({R.id.btn_back, R.id.btn_delete,R.id.all_checkbox,R.id.delete_all_bt,R.id.cancle_all_bt})
	public void back_click(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			onBackPressed();
			return;
		case R.id.btn_delete:// 删除模式
			mAdapter.setDel(true);
			mAdapter.notifyDataSetChanged();
			layout_delet.setVisibility(View.VISIBLE);
			break;
		case R.id.all_checkbox:
			// 全选
			mAdapter.selectAll(allcheckbox.isChecked());
			mAdapter.notifyDataSetChanged();
			break;
		case R.id.delete_all_bt:// 删除
			List<TreasureEntity> deleteInfos = mAdapter.getDels();
			if (deleteInfos.size() > 0) {
				StringBuffer sb = new StringBuffer();
				for (TreasureEntity each : deleteInfos) {
					sb.append(each.treasure_id).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				askNewWork = new AskNetWork(NetConst.DEL_MY_LOVE,ActivityMyPrecious.this);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", uid);
				map.put("ids", sb);
				askNewWork.ask(HttpRequest.HttpMethod.GET,map);
			} else {
				new ToastUtils(this, "请选择后在点击删除按钮");
			}
			break;
		case R.id.cancle_all_bt:// 退出选择模式
			layout_delet.setVisibility(View.GONE);
			allcheckbox.setChecked(false);
			mAdapter.setDel(false);
			mAdapter.exitSelectMode();
			break;
		}
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_precious);
		ViewUtils.inject(this);

		lists = new ArrayList<TreasureEntity>();
		model = new MyTreasureModel();
		uid = SharedPreferencesUtils.getInstance().getLoginUserID();
		type = getIntent().getIntExtra(Const.GOTO_MY_PRECIOUS, Const.PRECIOUS);

		dels = new ArrayList<String>();
		model.setType(type);
		Treadsure_type = MyTreasureModel.TYPE_ALL;
		if (type == Const.PRECIOUS) {
			title.setText("我的宝物");
			btn_delete.setVisibility(View.GONE);
			btn_delete.setClickable(false);
		} else if (type == Const.COLLECT) {
			title.setText("收藏宝物");
			btn_delete.setVisibility(View.VISIBLE);
			btn_delete.setClickable(true);
			btn_no.setVisibility(View.GONE);
			btn_no.setClickable(false);
		} else if (type == Const.IDENTIFY) {
			title.setText("我的鉴定");
			btn_delete.setVisibility(View.GONE);
			btn_delete.setClickable(false);
			btn_no.setVisibility(View.GONE);
			btn_no.setClickable(false);
		}
		btn_all.setOnClickListener(listener);
		btn_no.setOnClickListener(listener);
		btn_ing.setOnClickListener(listener);
		btn_ed.setOnClickListener(listener);

		initViews();
		btn_all.callOnClick();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	private void initViews() {
		RecyclerView recyclerview = (RecyclerView) mRecyclerview.getRefreshView();
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(layoutManager);
		recyclerview.setHasFixedSize(true);

		mAdapter = new MyTreasureAdapter(this, lists, lis, this);
		mAdapter.setType(type);

		recyclerview.setAdapter(mAdapter);
		mRecyclerview.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
			@Override
			public void onPullDown(float y) {

			}

			@Override
			public void onRefresh() {
				ActivityMyPrecious.this.onRefresh();
			}

			@Override
			public void onRefreshOver(Object obj) {

			}
		});

	}

	@Override
	public void onRefresh() {
		if (type == Const.PRECIOUS) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", uid);
			if (Treadsure_type == MyTreasureModel.TYPE_ALL) {
				map.put("status", -1);
			} else if (Treadsure_type == MyTreasureModel.TYPE_NO) {
				map.put("status", 0);
			} else {
				map.put("status", Treadsure_type);
			}
			map.put("page", 0);
			askNewWork = new AskNetWork(map, NetConst.SAVE_BAOBEI, ActivityMyPrecious.this);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(NetConst.SID, NetConst.SESSIONID);
			askNewWork.ask(param);
		} else {
			model.setType(type);
			model.sendHttp(new CommonCallBack() {

				@Override
				public void onSuccess() {
					mRecyclerview.refreshOver(null);
					lists.clear();
					lists.addAll(model.getResult());
					mAdapter.setData(lists);
				}

				@Override
				public void onError() {
					mRecyclerview.refreshOver(null);
				}
			}, Treadsure_type, uid);
		}

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(type == Const.COLLECT) {
				if(mAdapter.isDel()) {
					layout_delet.setVisibility(View.GONE);
					allcheckbox.setChecked(false);
					mAdapter.setDel(false);
					mAdapter.exitSelectMode();
				}
			}
			
			mRecyclerview.setToRefreshing();
			setIdentifyBackground(v.getId());
			switch (v.getId()) {
			case R.id.btn_all: {
				Treadsure_type = MyTreasureModel.TYPE_ALL;
			}
				break;
			case R.id.btn_no: {
				Treadsure_type = MyTreasureModel.TYPE_NO;
			}
				break;
			case R.id.btn_identifing: {
				Treadsure_type = MyTreasureModel.TYPE_IDENTIFIED;
			}
				break;
			case R.id.btn_identified: {
				Treadsure_type = MyTreasureModel.TYPE_IDENTIFYING;
			}
				break;

			default:
				break;
			}

		}
	};

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_all: {
			// 先设置背景
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));

			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_no: {
			// 先设置背景
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));

			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_identifing: {
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));

			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
		}
			break;
		case R.id.btn_identified: {
			((Button) btn_all.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_all.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
			((Button) btn_no.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_no.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));

			((Button) btn_ed.getChildAt(0)).setTextColor(getResources().getColor(R.color.dialog_title_color));
			btn_ed.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.dialog_title_color));
			((Button) btn_ing.getChildAt(0)).setTextColor(getResources().getColor(R.color.black_2));
			btn_ing.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.wite));
		}
			break;
		}
	}

	private OnClickListener lis = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 跳转到宝贝详情页
			TreasureEntity entity = (TreasureEntity) v.getTag();
			CollectionTreasure id = new CollectionTreasure();
			id.treasure_id = entity.treasure_id;
			
			Intent mIntent = new Intent(ActivityMyPrecious.this, ActivityUserDelails.class);
			mIntent.putExtra(Const.ENTITY, id);
			startActivity(mIntent);
			overridePendingTransition(R.anim.left_in, R.anim.left_out);
		}
	};

	@Override
	public void getNetWorkMsg(String msg, String param) throws JSONException {
		if (param.equals(NetConst.SAVE_BAOBEI)) {
			// 获得我的收藏里面的数据
			mRecyclerview.refreshOver(null);
			ResponseMyBaby rt = new Gson().fromJson(msg, ResponseMyBaby.class);
			if (rt.getCode() == 100000) {
				lists = rt.getData().getList();
				mAdapter.setData(lists);
			}
		} else if (param.equals(NetConst.DEL_MY_LOVE)) {
			// 删除我的收藏里面的数据
			ResponseIsDel rt = new Gson().fromJson(msg, ResponseIsDel.class);
			if (rt.getCode() == 100000) {
				Toast.makeText(ActivityMyPrecious.this, "删除成功", Toast.LENGTH_SHORT).show();
				mAdapter.delOk();
			} else {
				Toast.makeText(ActivityMyPrecious.this, "删除失败", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void getNetWorkMsgError(String msg, String param) throws JSONException {
		if (param.equals(NetConst.SAVE_BAOBEI)) {
			mRecyclerview.refreshOver(null);
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

}
