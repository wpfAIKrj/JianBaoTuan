package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;
import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;
import com.yingluo.Appraiser.app.ItApplication;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.MainEvent;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.inter.deleteItemlistener;
import com.yingluo.Appraiser.inter.onBasicView;
import com.yingluo.Appraiser.inter.onListView;
import com.yingluo.Appraiser.presenter.deleteInfoPresenter;
import com.yingluo.Appraiser.presenter.myCollectArticlePresenter;
import com.yingluo.Appraiser.refresh.PullRefreshRecyclerView;
import com.yingluo.Appraiser.refresh.RefreshLayout;
import com.yingluo.Appraiser.ui.adapter.MyArticleAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ListLoadType;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.ToastUtils;

import de.greenrobot.event.EventBus;

/**
 * 文章加载页面
 * 
 * @author Administrator
 *
 */
public class FavoriteArticlesActivity extends BaseActivity implements onListView<ContentInfo>,ListviewLoadListener {

	@ViewInject(R.id.home_title)
	private TextView title;

	private MyArticleAdapter madapter;
	private ArrayList<ContentInfo> list;

	@ViewInject(R.id.recyclerview1)
	private PullRefreshRecyclerView mRecyclerView;

	private int length = 30;

	protected boolean isLoadMore = false;
	private boolean isRefreshing = true;

	private boolean isFiset = true;

	protected int lastVisableView;
	protected int totalItemCount;
	protected int nextShow = 1;

	private HashMap<String, Integer> deleteInfos;

	private ListLoadType currt = ListLoadType.Nomal;

	private myCollectArticlePresenter myPresenter;

	private Dialog dialogLoad;

	private deleteInfoPresenter deletePresenter;

	@ViewInject(R.id.layout_delet)
	private RelativeLayout layout_delet;

	@ViewInject(R.id.all_checkbox)
	private CheckBox allcheckbox;

	private boolean isFirest = true;
	private boolean ismodel = false;// false为滑动删除，true为选择删除

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (ItApplication.getcurrnUser() == null) {
			EventBus.getDefault().post(new MainEvent(0, null));
			setResult(RESULT_CANCELED, getIntent());
			finish();
		}
		setContentView(R.layout.activity_favoritearticles);
		ViewUtils.inject(this);
		initView();
		initData();
		if (isFirest) {
			onRefresh();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}

	protected void initView() {
		title.setText(R.string.collect_info_title);
		list = new ArrayList<ContentInfo>();
		myPresenter = new myCollectArticlePresenter(this);
		deletePresenter = new deleteInfoPresenter(netlistener);
		
		RecyclerView recyclerView = (RecyclerView) mRecyclerView.getRefreshView();
		LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		
		mRecyclerView.setToRefreshing();
		mRecyclerView.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDown(float y) {

            }

            @Override
            public void onRefresh() {
            	if (isRefreshing) {
					FavoriteArticlesActivity.this.onRefresh();
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

		madapter = new MyArticleAdapter(this, list, listener, deleteItemlistener,this);
		madapter.setMode(Attributes.Mode.Single);
		madapter.setScorll(true);

		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(madapter);
		dialogLoad = DialogUtil.createLoadingDialog(this, "正在删除");
	}

	protected void initData() {
		deleteInfos = new HashMap<String, Integer>();
	}

	@OnClick({ R.id.button_delect, R.id.button_category, R.id.delete_all_bt, R.id.cancle_all_bt })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_category:// 跳转到
			setResult(RESULT_CANCELED, getIntent());
			finish();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.button_delect:// 删除模式
			ismodel = true;
			madapter.setScorll(false);
			madapter.notifyDataSetChanged();
			layout_delet.setVisibility(View.VISIBLE);
			break;
		case R.id.delete_all_bt:// 删除
			deleteInfos = madapter.getSelectForMap();
			if (deleteInfos.keySet().size() > 0) {
				dialogLoad.show();
				StringBuffer sb = new StringBuffer();
				Set<String> ids = deleteInfos.keySet();
				for (String string : ids) {
					sb.append(string).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				Long uid = SharedPreferencesUtils.getInstance().getLoginUserID();
				deletePresenter.deleteInfo(uid,String.valueOf(sb.toString()));
				ismodel = false;
			} else {
				new ToastUtils(this, "请选择后在点击删除按钮");
			}
			break;
		case R.id.cancle_all_bt:// 退出选择模式
			layout_delet.setVisibility(View.GONE);
			allcheckbox.setChecked(false);
			madapter.setScorll(true);
			madapter.exitSelectMode();
			ismodel = false;
			break;
		default:
			break;
		}
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (!ismodel) {
				ContentInfo info = (ContentInfo) v.getTag();
				info.setIsCollected(1);
				Intent intent = new Intent(FavoriteArticlesActivity.this, InformationDetailsActivity.class);
				intent.putExtra(Const.ArticleId, info);
				startActivity(intent);
				overridePendingTransition(R.anim.left_in, R.anim.left_out);
			}
		}
	};

	@Override
	public void onSucess(ArrayList<ContentInfo> data) {
		if (currt == ListLoadType.LoadMore) {// 加载更多
			addList(data);
		}
		if (currt == ListLoadType.Refresh) {// 刷新
			if (!data.isEmpty()) {
				list.clear();
				addList(data);
			}
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
		new ToastUtils(this, errorMsg);
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

	public void onRefresh() {
		currt = ListLoadType.Refresh;
		isRefreshing = false;
		isLoadMore = false;
		madapter.setFootType(2);
		madapter.notifyItemChanged(list.size());
		int foot = mRecyclerView.getChildCount();
		Long uid = SharedPreferencesUtils.getInstance().getLoginUserID();
		myPresenter.getArticleList(uid, length);
	}

	public void onLoadMore() {
		currt = ListLoadType.LoadMore;
		isLoadMore = false;
		isRefreshing = false;
		mRecyclerView.refreshOver(null);
		madapter.setFootType(1);
		madapter.notifyDataSetChanged();
		Long uid = SharedPreferencesUtils.getInstance().getLoginUserID();
		myPresenter.getArticleList(uid, (length + 1));
	}

	private deleteItemlistener<ContentInfo> deleteItemlistener = new deleteItemlistener<ContentInfo>() {

		@Override
		public void ondeleteItem(ContentInfo item, int id) {
			dialogLoad.show();
			deleteInfos.put(String.valueOf(item.getId()), id);
			Long uid = SharedPreferencesUtils.getInstance().getLoginUserID();
			deletePresenter.deleteInfo(uid,String.valueOf(item.getId()));

		}
	};
	
	/**
	 * 删除结果
	 */
	private onBasicView<String> netlistener = new onBasicView<String>() {

		@Override
		public void onSucess(String data) {

			try {
				StringBuffer sb = new StringBuffer(data);
				sb.deleteCharAt(0);
				sb.deleteCharAt(sb.length() - 1);
				String[] strs = sb.toString().split(",");
				for (int i = 0; i < strs.length; i++) {
					sb = new StringBuffer(strs[i]);
					sb.deleteCharAt(0);
					sb.deleteCharAt(sb.length() - 1);
					int id = deleteInfos.get(sb.toString());
					madapter.deleteItem(id);
				}
				deleteInfos.clear();
			} catch (Exception e) {
				e.printStackTrace();
				madapter.closeAllItems();
			}
			deleteInfos.clear();
			if (dialogLoad != null) {
				dialogLoad.dismiss();
			}
			if (madapter.getItemCount() == 1) {
				layout_delet.setVisibility(View.GONE);
				allcheckbox.setChecked(false);
				madapter.setScorll(true);
				madapter.exitSelectMode();
			}

		}

		@Override
		public void onFail(String errorCode, String errorMsg) {

			if (dialogLoad != null) {
				dialogLoad.dismiss();
			}
			new ToastUtils(FavoriteArticlesActivity.this, errorMsg);
		}
	};

	@OnCompoundButtonCheckedChange(R.id.all_checkbox)
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			madapter.setSelectDelete(true);
		} else {
			madapter.setSelectDelete(false);
		}
	}

}
