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

	@ViewInject(R.id.home_title)
	private TextView title;
	
	@ViewInject(R.id.button_delect)
	private ImageView iv_delete;
	


	


	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritearticles);
		ViewUtils.inject(this);
		initView();

	}
	
	protected void initView() {
		// TODO Auto-generated method stub

		title.setText("聊天");
		iv_delete.setVisibility(View.GONE);
		
	}





	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_category://跳转到
			setResult(RESULT_CANCELED, getIntent());
			finish();
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
