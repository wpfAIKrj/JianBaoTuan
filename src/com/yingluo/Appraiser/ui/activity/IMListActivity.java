package com.yingluo.Appraiser.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.ui.adapter.ArticleAdapter;
import com.yingluo.Appraiser.ui.adapter.IMAdapter;
import com.yingluo.Appraiser.ui.adapter.SystemInfoAdapter;
import com.yingluo.Appraiser.ui.base.BaseActivity;
import com.yingluo.Appraiser.utils.DialogUtil;
import com.yingluo.Appraiser.utils.ListLoadType;
import com.yingluo.Appraiser.view.listview.XListView;
import com.yingluo.Appraiser.view.listview.XListView.IXListViewListener;
/**
 * 聊天页面
 * @author Administrator
 *
 */
public class IMListActivity extends BaseActivity implements OnClickListener,IXListViewListener,OnItemClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritearticles);
		ViewUtils.inject(this);
		initView();
		initData();
	}
	@ViewInject(R.id.home_title)
	private TextView title;
	
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

	private HashMap<String, Integer> deleteInfos;
	
	private ListLoadType currt=ListLoadType.Nomal;


	private Dialog dialogLoad;
	
	
	@ViewInject(R.id.layout_delet)
	private RelativeLayout layout_delet;
	
	@ViewInject(R.id.all_checkbox)
	private CheckBox allcheckbox;
	
	private SystemInfoAdapter madapter;


	protected void initView() {
		// TODO Auto-generated method stub

		title.setText("聊天");
		list=new ArrayList<ContentInfo>();
		LayoutManager layoutManager=new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(layoutManager);
		mSwipeRefreshWidget.setDistanceToTriggerSync(200);
		mSwipeRefreshWidget.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {//刷新
				// TODO Auto-generated method stub
				if(isRefreshing){
				
				}else{
					Log.d("helper","正在刷新中!");
					mSwipeRefreshWidget.setRefreshing(false);
				}
			}
		});

		mRecyclerView.setHasFixedSize(true);
		
//		madapter=new MyArticleAdapter(this,list,listener,FavoriteArticlesActivity.this,deleteItemlistener);
//		madapter.setMode(Attributes.Mode.Single);
//		madapter.setScorll(true);
		
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//		mRecyclerView.setAdapter(madapter);
		dialogLoad=DialogUtil.createLoadingDialog(this, "正在删除");
		
	}

	protected void initData() {
		// TODO Auto-generated method stub
		madapter=new SystemInfoAdapter(LayoutInflater.from(this));
		deleteInfos=new HashMap<String, Integer>();
	}



	@Override
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

	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}
}
