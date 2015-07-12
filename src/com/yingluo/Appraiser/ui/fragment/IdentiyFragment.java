package com.yingluo.Appraiser.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingluo.Appraiser.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.model.CommonCallBack;
import com.yingluo.Appraiser.model.IdentifyModel;
import com.yingluo.Appraiser.presenter.IdentifyPresenter;
import com.yingluo.Appraiser.ui.activity.KindOfPreciousActivity;
import com.yingluo.Appraiser.ui.adapter.IdentiyAdapter;
import com.yingluo.Appraiser.ui.base.BaseFragment;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.ToastUtils;
import com.yingluo.Appraiser.view.PullRefreshRecyclerView;
import com.yingluo.Appraiser.view.PullRefreshRecyclerView.RefreshLoadMoreListener;

public class IdentiyFragment extends BaseFragment implements
		RefreshLoadMoreListener {

	@ViewInject(R.id.button_category)
	ImageView button_category;

	@ViewInject(R.id.btn_identifing)
	ViewGroup btn_identifing;
	@ViewInject(R.id.btn_identifed)
	ViewGroup btn_identified;
	@ViewInject(R.id.prrv)
	PullRefreshRecyclerView prrv;

	IdentiyAdapter mAdapter = null;

	int type = 2;

	int current_page = 0;

	private List<CollectionTreasure> list = null;

	ItApplication app;

	private int kindId = 0;

	private IdentifyPresenter identifyPresenter;

	private Dialog dialog;
	
	
	@ViewInject(R.id.tv_show_no_data)
	private TextView tv_show_no_data;

	@OnClick({ R.id.btn_identifing, R.id.btn_identifed, R.id.button_category })
	public void doClick(View v) {
		// TODO Auto-generated method stub
		setIdentifyBackground(v.getId());
		switch (v.getId()) {
		case R.id.button_category: {
			Intent mIntent = new Intent(getActivity(),
					KindOfPreciousActivity.class);
			mActivity.startActivityForResult(mIntent, Const.TO_INDENTIFY);
		}
			break;
		case R.id.btn_identifing: {
			type = 1;
			prrv.stopRefresh();
			prrv.setLoadMoreCompleted();
			// 获取正在鉴定数据
			Log.i("ytmfdw", "get identifing");
			showImageData(type,kindId);
			if (app != null) {
				if (kindId == 0) {
					if (app.getHasIdentify() != null) {
						list = app.getHasIdentify();
						mAdapter.setData(list);
					} else {
						// 联网下载
						getIndentity();
					}
				} else {
					getIndentity();
				}
			}

		}

			break;

		case R.id.btn_identifed: {
			// 获取已经鉴定数据
			type = 2;
			prrv.stopRefresh();
			showImageData(type,kindId);
			prrv.setLoadMoreCompleted();
			Log.i("ytmfdw", "get identifed");
			if (app != null) {
				if (kindId == 0) {
					if (app.getUnIdentify() != null) {
						list = app.getUnIdentify();
						mAdapter.setData(list);
					} else {
						// 联网下载
						getIndentity();
					}
				} else {
					getIndentity();
				}
			}
		}

			break;
		}
	}

	private void showImageData(int type, long kindId) {
		// TODO Auto-generated method stub
		String str=FileUtils.getInstance().getFileForKindJson(kindId, type);
		if(str!=null){
			try {
				Gson gson = new Gson();
				List<CollectionTreasure> arrlist = gson.fromJson(str, new TypeToken<List<CollectionTreasure>>() {
				}.getType());
				if(arrlist!=null&&arrlist.size()>0){
					tv_show_no_data.setVisibility(View.GONE);
					mAdapter.setData(arrlist);
				}else{
					tv_show_no_data.setVisibility(View.VISIBLE);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.layout_identiy, container, false);
	}

	@Override
	protected void initViews(View view) {
		ViewUtils.inject(this, view);
		identifyPresenter = new IdentifyPresenter(netlistener1);
		mAdapter = new IdentiyAdapter();
		app = (ItApplication) getActivity().getApplication();
		list = app.getHasIdentify();
		if (list != null && list.size() != 0) {

			mAdapter.setData(list);
		}
		prrv.setRefreshLoadMoreListener(this);
		prrv.setVertical();
		prrv.setAdapter(mAdapter);
		// prrv.refresh();

	}

	@Override
	protected void initDisplay() {
		// TODO Auto-generated method stub
		getIndentity();
	}

	@Override
	public void lazyLoad() {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName() + "正在加载数据");
	}

	public void setIdentifyBackground(int id) {
		switch (id) {
		case R.id.btn_identifing: {
			// 先设置背景
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.dialog_title_color));
			btn_identifing.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.dialog_title_color));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_identified.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));
		}

			break;

		case R.id.btn_identifed: {
			((Button) btn_identifing.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.black_2));
			btn_identifing.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.wite));

			((Button) btn_identified.getChildAt(0)).setTextColor(getResources()
					.getColor(R.color.dialog_title_color));
			btn_identified.getChildAt(1).setBackgroundColor(
					getResources().getColor(R.color.dialog_title_color));
		}
			break;
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		// current_page++;

		getIndentity();

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Const.TO_INDENTIFY
				|| resultCode == Activity.RESULT_OK) {
			kindId =  data.getIntExtra(Const.KIND_ID, 0);
			showImageData(type, kindId);
			getIndentity();
		}
	}

	private onListView<CollectionTreasure> netlistener1 = new onListView<CollectionTreasure>() {

		@Override
		public void onSucess(ArrayList<CollectionTreasure> data) {
			// TODO Auto-generated method stub
			prrv.stopRefresh();
			if (data.size() == 0) {
				tv_show_no_data.setVisibility(View.VISIBLE);
				new ToastUtils(mActivity, "没有更多数据");
			}else{
				tv_show_no_data.setVisibility(View.GONE);
			}
			mAdapter.setData(data);
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
		}

		@Override
		public void onFail(String errorCode, String errorMsg) {
			// TODO Auto-generated method stub
			prrv.stopRefresh();
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
			new ToastUtils(mActivity, errorMsg);
		}
	};

	private void getIndentity() {
		// TODO Auto-generated method stub
		if (dialog == null) {
			dialog = DialogUtil.createLoadingDialog(mActivity, "获取鉴定宝物中....");
		}
		dialog.show();
		identifyPresenter.getIdentity(kindId, type);
	}

}
