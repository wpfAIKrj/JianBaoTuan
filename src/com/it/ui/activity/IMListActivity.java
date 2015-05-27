package com.it.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.it.R;
import com.it.ui.adapter.ArticleAdapter;
import com.it.ui.adapter.IMAdapter;
import com.it.ui.base.BaseActivity;
import com.it.view.listview.XListView;
import com.it.view.listview.XListView.IXListViewListener;
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
//		initView();
//		initData();
	}
	private XListView mlistview;
	private IMAdapter madapter;


	protected void initView() {
		// TODO Auto-generated method stub
		ImageView bt=(ImageView)findViewById(R.id.button_category);
		bt.setOnClickListener(this);
		bt=(ImageView)findViewById(R.id.button_delect);
		bt.setOnClickListener(this);

		TextView tv=(TextView)findViewById(R.id.home_title);
		tv.setText("聊天");
		
	}

	protected void initData() {
		// TODO Auto-generated method stub
		madapter=new IMAdapter(LayoutInflater.from(this));
		mlistview.setAdapter(madapter);
		mlistview.setXListViewListener(this);
		mlistview.setOnItemClickListener(this);
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
